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
import kr.hungrybrothres.donotforget.features.extensions.snackbar

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
                    joinSuccess(message = "회원가입에 성공하였습니다.")
                    finish()
                }
                is ApiResponse.Error -> {
                    it.error?.message?.let { message -> showError(message = message) }
                }
            }
        }
    }

    private fun joinSuccess(message: String) {
        binding.topCl.snackbar(message)
    }

    private fun showError(message: String) {
        binding.topCl.snackbar(message)
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