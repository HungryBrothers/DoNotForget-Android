package kr.hungrybrothres.donotforget.features.ui.auth.login

import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.hungrybrothres.donotforget.R
import kr.hungrybrothres.donotforget.databinding.ActivityLoginBinding
import kr.hungrybrothres.donotforget.features.base.BaseActivity
import kr.hungrybrothres.donotforget.features.extensions.openActivity
import kr.hungrybrothres.donotforget.features.ui.auth.join.JoinActivity
import kr.hungrybrothres.donotforget.features.ui.home.MainActivity

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private val viewModel: LoginViewModel by viewModels()

    override fun initView(savedInstanceState: Bundle?) {
        binding {
            activity = this@LoginActivity
            vm = viewModel
        }
    }

    override fun onProcess() {
    }

    fun moveJoinActivity() {
        openActivity(JoinActivity::class.java)
    }



    fun doLogin() {
        viewModel.doLogin(userName = binding.edtUserName.text?.trim().toString(), password = binding.edtUserPassword.text.toString())
//        openActivity(MainActivity::class.java)
//        finish()
    }
}