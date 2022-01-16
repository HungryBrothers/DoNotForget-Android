package kr.hungrybrothres.data.repository

import com.orhanobut.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kr.hungrybrothres.data.network.ApiResponseHandler
import kr.hungrybrothres.data.remote.auth.AuthService
import kr.hungrybrothres.domain.entity.auth.login.LoginRequest
import kr.hungrybrothres.domain.entity.auth.join.Join
import kr.hungrybrothres.domain.entity.auth.join.JoinRequest
import kr.hungrybrothres.domain.repository.AuthRepository
import kr.hungrybrothres.domain.entity.state.ApiResponse
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authService: AuthService) : AuthRepository, ApiResponseHandler() {

    override fun postUserLogin(loginRequest: LoginRequest): Flow<ApiResponse<String>> = flow {
        val response = authService.login(loginRequest = loginRequest)
        emit( safeApiCall { response } )
    }.flowOn(Dispatchers.IO)

    override fun postUserJoin(joinRequest: JoinRequest): Flow<ApiResponse<Join>> = flow {
        val response = authService.join(joinRequest = joinRequest)
        emit( safeApiCall { response } )
    }.flowOn(Dispatchers.IO)


}