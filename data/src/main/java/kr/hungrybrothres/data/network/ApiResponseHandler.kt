package kr.hungrybrothres.data.network

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.orhanobut.logger.Logger
import kr.hungrybrothres.domain.entity.state.ApiResponse
import kr.hungrybrothres.domain.entity.state.ErrorResponse
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
                val type = object : TypeToken<ErrorResponse>(){}.type
                val err : ErrorResponse = Gson().fromJson(response.errorBody()!!.charStream(), type)
                Logger.d("isNotSuccessful :: ${response.errorBody()?.string()}\nerr code :: ${err.code}\nerr message :: ${err.message}\nerr status${err.status}")
                return ApiResponse.Error(ErrorResponse(message = err.message, status = err.status, code = err.code))
            }
            return ApiResponse.Error(ErrorResponse(message = "another", status = 999, code = "another?"))
        }catch (e: Exception) {
            return ApiResponse.Error(ErrorResponse(message = e.message!!, status = 999, code = "another?"))
        }
    }

}