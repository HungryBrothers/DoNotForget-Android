package kr.hungrybrothres.donotforget.features.ui.auth.join

import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint
import kr.hungrybrothres.domain.entity.state.ApiResponse
import kr.hungrybrothres.donotforget.R
import kr.hungrybrothres.donotforget.databinding.ActivityJoinBinding
import kr.hungrybrothres.donotforget.features.base.BaseActivity

@AndroidEntryPoint
class JoinActivity : BaseActivity<ActivityJoinBinding>(R.layout.activity_join) {
    private val viewModel:JoinViewModel by viewModels()

    override fun initView(savedInstanceState: Bundle?) {
        binding {
            activity = this@JoinActivity
            vm = viewModel
        }
    }

    override fun onProcess() {
        initViewSetup()
        subscribeObservers()
    }

    private fun initViewSetup() {

    }

    private fun subscribeObservers() {
        viewModel.signUpResult.observe(this) {
            when(it) {
                is ApiResponse.Success -> {
                    Logger.d("SUCCESS :: ${it.data}")
                }
                is ApiResponse.Loading -> {
                    Logger.d("LOADING :: ${it.data}")
                }
                is ApiResponse.Error -> {
                    Logger.d("ERROR :: ${it.data}")
                }
            }
        }
    }

    private fun showError(message: String?) {
//        val bottomNavView = (requireActivity() as? MainActivity)
//            ?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
//
//        view?.snackbar(
//            message = message ?: getString(R.string.generic_error),
//            anchorView = bottomNavView
//        )
    }

    fun doJoin() {
        binding.apply {
            viewModel.doJoin(
                userId = edtUserId.text.toString(),
                userName = edtUserName.text.toString(),
                password = edtUserPassword.text.toString(),
                accountRole = "CLIENT"
            )
        }
    }
}