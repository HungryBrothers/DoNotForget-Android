package kr.hungrybrothres.domain.usecase.auth

import kotlinx.coroutines.flow.Flow
import kr.hungrybrothres.domain.entity.auth.join.Join
import kr.hungrybrothres.domain.entity.auth.join.JoinRequest
import kr.hungrybrothres.domain.repository.AuthRepository
import kr.hungrybrothres.domain.entity.state.ApiResponse
import javax.inject.Inject

class JoinUseCase @Inject constructor(private val authRepository: AuthRepository) {
    fun execute(joinRequest: JoinRequest) : Flow<ApiResponse<Join>> {
        return authRepository.postUserJoin(joinRequest)
    }
}