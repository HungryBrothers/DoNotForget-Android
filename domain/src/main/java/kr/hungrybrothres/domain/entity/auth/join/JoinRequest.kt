package kr.hungrybrothres.domain.entity.auth.join

data class JoinRequest (
    val userId: String,
    val username: String,
    val password: String,
    val accountRole: String
)