package kangparks.android.vostom.viewModel.splash

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kangparks.android.vostom.models.learning.LearningState
import kangparks.android.vostom.utils.networks.learning.getLearningState
import kangparks.android.vostom.utils.store.getAccessToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

sealed class RequestState(val state : Int){
    object BeforeRequest : RequestState(0)
    object Requesting : RequestState(1)
    object AfterRequest : RequestState(2)
}

class SplashViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application.applicationContext
    private val coroutineScope = CoroutineScope(viewModelScope.coroutineContext)
    private var _accessToken: MutableLiveData<String?> = MutableLiveData<String?>(null)
    private val _isReceivedRequestLearningState: MutableLiveData<RequestState> = MutableLiveData(RequestState.BeforeRequest)

    private val _currentLearningState: MutableLiveData<LearningState> = MutableLiveData(
        LearningState.AfterLearning
    )

    private val _complete = MutableLiveData<Boolean>()

    val accessToken: LiveData<String?> get() = _accessToken
    val isReceivedRequestLearningState: LiveData<RequestState> get() = _isReceivedRequestLearningState
    val currentLearningState: LiveData<LearningState> get() = _currentLearningState
    val complete: LiveData<Boolean> get() = _complete

    init {
        val token = getAccessToken(application.applicationContext)
        Log.d("Test-SplashViewModel", "token : $token")
        _accessToken.postValue(token)
        _complete.value = false
    }

    fun getUserTokenFromDevice(context: Context) {
        val token = getAccessToken(context)
        _accessToken.postValue(token)
    }

    fun setToken(token : String){
        Log.d("Test-SplashViewModel", "setToken : $token")
        _accessToken.postValue(token)
    }

    fun getCurrentLearningState(
        token : String,
        context: Context
    ) {
        Log.d("Test-SplashViewModel", "getCurrentLearningState")
//        val token = getAccessToken(context)
        if (token == null) {
            Log.d("Test-SplashViewModel", "token is null")
            _isReceivedRequestLearningState.postValue(RequestState.Requesting)
            coroutineScope.launch {
                delay(1000)
                _isReceivedRequestLearningState.postValue(RequestState.AfterRequest)
            }
            return
        }

        _accessToken.postValue(token)
        _isReceivedRequestLearningState.postValue(RequestState.Requesting)

        coroutineScope.launch {
            val learningState = getLearningState(token)
            Log.d("Test-SplashViewModel", "learningState : $learningState")

            if(learningState == null){
                CoroutineScope(Dispatchers.Main).launch{
                    Toast.makeText(context, "네트워크 오류가 발생했습니다.\n잠시 후 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
                }
                _isReceivedRequestLearningState.postValue(RequestState.AfterRequest)
                return@launch
            }
            else{
                _currentLearningState.postValue(learningState!!)
//            _currentLearningState.postValue(LearningState.AfterLearning)
                _isReceivedRequestLearningState.postValue(RequestState.AfterRequest)
            }
        }
    }

    fun updateComplete() {
        _complete.value = true
    }

}