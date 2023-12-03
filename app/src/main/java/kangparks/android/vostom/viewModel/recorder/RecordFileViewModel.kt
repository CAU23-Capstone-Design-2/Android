package kangparks.android.vostom.viewModel.recorder

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kangparks.android.vostom.utils.constants.getCurrentDate
import kangparks.android.vostom.utils.networks.learning.uploadLearningData
import kangparks.android.vostom.utils.store.getAccessToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File

class RecordFileViewModel : ViewModel() {
    private val coroutineScope = CoroutineScope(viewModelScope.coroutineContext)

    private val _recordFileList = mutableListOf<File>()
    private val recordFileList: List<File> = _recordFileList

    private val _currentDate = getCurrentDate()

    private var _currentRecordFileName: String =
        _currentDate + "-" + recordFileList.size.toString() + ".m4a"

    private val _isAfterUploadFiles : MutableLiveData<Boolean> = MutableLiveData(false)
    private val _isProceedingDialogOpen : MutableLiveData<Boolean> = MutableLiveData(false)

    val isAfterUploadFiles : LiveData<Boolean> = _isAfterUploadFiles
    val isProceedingDialogOpen : LiveData<Boolean> = _isProceedingDialogOpen

    fun addRecordFile(file: File) {
        Log.d("RecordFileViewModel", "addRecordFile: 파일 추가  $file")
        Log.d("RecordFileViewModel", "addRecordFile: 현재 파일 리스트 $recordFileList")
        _recordFileList.add(file)
    }

    fun getCurrentRecordFileName(): String {
        return _currentDate + "-" + recordFileList.size.toString() + ".m4a"
    }

    fun uploadRecordFileToServer(
        context : Context
    ) {
        _isProceedingDialogOpen.value = true
        val accessToken = getAccessToken(context)
        coroutineScope.launch {
            if (accessToken != null) {
                Log.d("RecordFileViewModel", "uploadRecordFileToServer")
                val result = uploadLearningData(accessToken, recordFileList, context)

                if(result){
                    _isProceedingDialogOpen.value = false
                    _isAfterUploadFiles.value = true
                }
                else{
                    _isProceedingDialogOpen.value = false
                    _isAfterUploadFiles.value = false
                }

            }
        }
    }

    fun getRecordFileList(): List<File> {
        Log.d("RecordFileViewModel", "getRecordFileList: 파일 리스트 추출 $recordFileList")
        return recordFileList
    }

    fun testForCurrentList(){
        Log.d("RecordFileViewModel", "currentList: $recordFileList")
    }

    fun reset() {
        _recordFileList.clear()
        _currentRecordFileName = getCurrentDate() + "-" + recordFileList.size.toString() + ".m4a"
    }

}