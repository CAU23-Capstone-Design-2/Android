package kangparks.android.vostom.viewModel.learning

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kangparks.android.vostom.models.learning.LearningState

class LearningStateViewModel : ViewModel() {
    private val _currentLearningState : MutableLiveData<LearningState> = MutableLiveData(LearningState.Learning)

    val currentLearningState : LiveData<LearningState> = _currentLearningState

    fun setCurrentLearningState(state : LearningState){
        _currentLearningState.postValue(state)
    }
}