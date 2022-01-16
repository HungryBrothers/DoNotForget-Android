package kr.hungrybrothres.domain.repository

import kotlinx.coroutines.flow.Flow
import kr.hungrybrothres.domain.entity.auth.login.LoginRequest
import kr.hungrybrothres.domain.entity.auth.join.Join
import kr.hungrybrothres.domain.entity.auth.join.JoinRequest
import kr.hungrybrothres.domain.entity.state.ApiResponse

interface AuthRepository {

    fun postUserLogin(loginRequest: LoginRequest) : Flow<ApiResponse<String>>

    fun postUserJoin(joinRequest: JoinRequest) : Flow<ApiResponse<Join>>
}