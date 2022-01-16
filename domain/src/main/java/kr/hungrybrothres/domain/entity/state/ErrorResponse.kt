package kr.hungrybrothres.domain.entity.state

import java.lang.Exception

data class ErrorResponse(
    val message: String,
    val status: Int,
//    val errors: List<Errors>
    val code: String
) {
//    companion object {
//        fun convertToObject(errorResponse: String?): ErrorResponse {
//            val error = ErrorResponse("", 0, "")
//            try{
//                val jsonError = JSON
//            }catch (e: Exception) {
//
//            }
//        }
//    }
}
