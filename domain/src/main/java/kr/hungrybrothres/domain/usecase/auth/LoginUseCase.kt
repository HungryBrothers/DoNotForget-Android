package kr.hungrybrothres.domain.usecase.auth

import kotlinx.coroutines.flow.Flow
import kr.hungrybrothres.domain.entity.auth.login.LoginRequest
import kr.hungrybrothres.domain.repository.AuthRepository
import kr.hungrybrothres.domain.entity.state.ApiResponse
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authRepository: AuthRepository) {
    fun execute(loginRequest: LoginRequest) : Flow<ApiResponse<String>> {
        return authRepository.postUserLogin(loginRequest)
    }
}