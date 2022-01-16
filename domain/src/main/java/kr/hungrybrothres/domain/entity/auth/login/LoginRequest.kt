package kr.hungrybrothres.domain.entity.auth.login

data class LoginRequest(
    val userId: String,
    val password: String
)
