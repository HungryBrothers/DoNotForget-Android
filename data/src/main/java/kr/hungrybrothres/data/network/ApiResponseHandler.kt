package kr.hungrybrothres.data.network

import com.orhanobut.logger.Logger
import kr.hungrybrothres.domain.entity.state.ApiResponse
import retrofit2.Response

abstract class ApiResponseHandler {

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): ApiResponse<T> {
        try {
            ApiResponse.Loading(null)
            val response = apiCall()
            if(response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return ApiResponse.Success(body)
                }
            }else {
                Logger.d("isNotSuccessful :: ${response.errorBody()?.string()}")

                ApiResponse.Error(response.errorBody()?.string()!!, response.errorBody())
            }
            return ApiResponse.Error("Fail")
        }catch (e: Exception) {
            return ApiResponse.Error("Fail")
        }
    }

}