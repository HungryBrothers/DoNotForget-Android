package kr.hungrybrothres.donotforget.features.ui.auth.login

import androidx.lifecycle.viewModelScope
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kr.hungrybrothres.domain.entity.auth.login.LoginRequest
import kr.hungrybrothres.domain.usecase.auth.LoginUseCase
import kr.hungrybrothres.donotforget.features.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : BaseViewModel() {

    fun doLogin(userName: String, password: String) {
        viewModelScope.launch {
            loginUseCase.execute(LoginRequest(userId = userName, password = password))
                .onStart {
                    handleLoading(true)
                }
                .catch {
                    handleLoading(false)
                    Logger.d("catch :: ${it.message}")
                }
                .collect {
                    handleLoading(false)
                    Logger.d("it :: ${it.data}")
                }
        }

    }
}