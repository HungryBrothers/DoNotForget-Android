package kr.hungrybrothres.donotforget.features.ui.auth.join

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kr.hungrybrothres.domain.entity.auth.join.Join
import kr.hungrybrothres.domain.entity.auth.join.JoinRequest
import kr.hungrybrothres.domain.entity.state.ApiResponse
import kr.hungrybrothres.domain.usecase.auth.JoinUseCase
import kr.hungrybrothres.donotforget.features.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class JoinViewModel @Inject constructor(private val joinUseCase: JoinUseCase) : BaseViewModel() {
    private val _signUpResult: MutableLiveData<ApiResponse<Join>> = MutableLiveData()
    val signUpResult: LiveData<ApiResponse<Join>>
        get() = _signUpResult
    
    fun doJoin(userId: String, userName: String, password: String, accountRole: String) {
        viewModelScope.launch { 
            joinUseCase.execute(JoinRequest(userId, userName, password, accountRole))
                .onStart { handleLoading(true) }
                .catch { handleLoading(false) }
                .collect { 
                    handleLoading(false)
                    _signUpResult.value = it
                }
        }
    }
}