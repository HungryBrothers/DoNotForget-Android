package kr.hungrybrothres.data.remote.auth.join.dto

import com.google.gson.annotations.SerializedName

data class JoinResponse(
    @SerializedName("id") var id: String? = null,
    @SerializedName("userId") var userId: String? = null,
    @SerializedName("username") var userName: String? = null
)
