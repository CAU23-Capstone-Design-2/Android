package kangparks.android.vostom.viewModel.learning

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import kangparks.android.vostom.R

class ScriptProviderViewModel() : ViewModel() {
    private val _scriptIDList = mutableListOf<Int>(
        R.string.learning_script1,
        R.string.learning_script2,
        R.string.learning_script3,
        R.string.learning_script4,
    )

    private val _currentProvideIdx = mutableIntStateOf(0)

    fun getScriptID() : Int {
        return _scriptIDList[_currentProvideIdx.value]
    }

    fun hasNext() : Boolean {
        return if (_currentProvideIdx.value < _scriptIDList.size -1) {
            _currentProvideIdx.value += 1
            _currentProvideIdx.value < _scriptIDList.size
        } else
            _currentProvideIdx.value < _scriptIDList.size -1
    }

    fun reset() {
        _currentProvideIdx.value = 0
    }

}