package kr.hungrybrothres.donotforget.features.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.Window
import android.view.WindowInsetsController
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onStart

fun <T> Context.openActivity(it: Class<T>, extras: Bundle.() -> Unit = {}) {
    val intent = Intent(this, it)
    intent.putExtras(Bundle().apply(extras))
    startActivity(intent)
}

fun Context.toast(message: String?) {
    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
}

fun removeFocusAndHideKeyboard(activity: Activity?) {
    if (activity == null) return
    val view = activity.currentFocus
    if (view != null) {
        view.clearFocus()
        hideKeyboard(activity, view.windowToken)
    }
}

private fun hideKeyboard(activity: Activity?, windowToken: IBinder?) {
    if (activity == null) return
    val inputManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
}

fun EditText.textChange(): Flow<CharSequence?> {
    return callbackFlow<CharSequence?> {

        val listener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun afterTextChanged(s: Editable?) = Unit
            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                trySend(text)
            }
        }

        addTextChangedListener(listener)

        awaitClose {
            removeTextChangedListener(listener)
        }
    }.onStart {  emit(text) }
}

fun View.snackbar(message: String = "", actionMessage: String = "", anchorView: View? = null,
                  length: Int = Snackbar.LENGTH_LONG, action: (() -> Unit)? = null): Snackbar {
    val snackbar = Snackbar.make(this, message, length)
    if (action != null) snackbar.setAction(actionMessage) { action.invoke() }
    if (anchorView != null) snackbar.anchorView = anchorView
    snackbar.show()
    return snackbar
}

private fun Activity.isNightModeActive() = resources?.configuration
    ?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES

fun Fragment.isNightModeActive() = resources.configuration
    ?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES

private fun Window.setDarkStatusBarIcons() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        insetsController?.setSystemBarsAppearance(
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
        )
    } else {
        var flags = decorView.systemUiVisibility
        flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        decorView.systemUiVisibility = flags
    }
}

private fun Window.setLightStatusBarIcons() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        insetsController?.setSystemBarsAppearance(
            0,
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
        )
    } else {
        var flags = decorView.systemUiVisibility
        flags = flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        decorView.systemUiVisibility = flags
    }
}


fun Activity.setTranslucentStatusBar(translucent: Boolean) {
    val flag = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
    val flags = window.attributes.flags

    // guard to prevent multiple calls when flag is already set or cleared
    if (((flags and flag) != 0 && translucent) ||
        ((flags and flag) == 0 && !translucent)
    ) return

    if (translucent) {
        window.setFlags(flag, flag)
        window.setLightStatusBarIcons()
    } else {
        if (isNightModeActive()) {
            window.setLightStatusBarIcons()
        } else {
            window.setDarkStatusBarIcons()
        }
        window.clearFlags(flag)
    }
}