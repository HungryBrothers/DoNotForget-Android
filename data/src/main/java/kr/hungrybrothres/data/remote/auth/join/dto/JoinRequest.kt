package kr.hungrybrothres.data.remote.auth.join.dto

import com.google.gson.annotations.SerializedName

data class JoinRequest(
    @SerializedName("userId") val userId: String,
    @SerializedName("username") val userName: String,
    @SerializedName("password") val password: String,
    @SerializedName("accountRole") var accountRole: String
)
