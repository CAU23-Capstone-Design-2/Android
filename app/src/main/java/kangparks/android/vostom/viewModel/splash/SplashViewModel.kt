package kangparks.android.vostom.viewModel.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplashViewModel : ViewModel() {
    private val _complete = MutableLiveData<Boolean>()
    val complete: LiveData<Boolean> get() = _complete

    init {
        _complete.value = false
    }

    fun updateComplete(){
        _complete.value = true
    }

}