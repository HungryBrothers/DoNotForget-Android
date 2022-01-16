package kr.hungrybrothres.data.remote.auth


import kr.hungrybrothres.data.remote.auth.join.dto.JoinResponse
import kr.hungrybrothres.domain.entity.auth.join.Join
import kr.hungrybrothres.domain.entity.auth.join.JoinRequest
import kr.hungrybrothres.domain.entity.auth.login.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/sign/in")
    suspend fun login(@Body loginRequest: LoginRequest) : Response<String>

    @POST("api/sign/up")
    suspend fun join(@Body joinRequest: JoinRequest) : Response<Join>
}