package kangparks.android.vostom.viewModel.recorder

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kangparks.android.vostom.utils.constants.getCurrentDate
import kangparks.android.vostom.utils.networks.learning.uploadLearningData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.File

class RecordFileViewModel : ViewModel() {
    private val coroutineScope = CoroutineScope(viewModelScope.coroutineContext)

    private val _recordFileList = mutableListOf<File>()
    private val recordFileList: List<File> = _recordFileList

    private val _currentDate = getCurrentDate()

    private var _currentRecordFileName: String =
        _currentDate + "-" + recordFileList.size.toString() + ".m4a"

    fun addRecordFile(file: File) {
        _recordFileList.add(file)
    }

    fun getCurrentRecordFileName(): String {
        return _currentDate + "-" + recordFileList.size.toString() + ".m4a"
    }

    fun uploadRecordFileToServer(
        accessToken : String,
    ) {
        coroutineScope.launch {
            uploadLearningData(accessToken, recordFileList)
        }
    }

    fun getRecordFileList(): List<File> {
        return recordFileList
    }

    fun testForCurrentList(){
        Log.d("RecordFileViewModel", "currentList: ${recordFileList}")
    }

    fun reset() {
        _recordFileList.clear()
        _currentRecordFileName = getCurrentDate() + "-" + recordFileList.size.toString() + ".m4a"
    }

}