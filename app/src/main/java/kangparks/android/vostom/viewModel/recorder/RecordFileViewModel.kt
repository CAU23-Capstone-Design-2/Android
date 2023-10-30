package kangparks.android.vostom.viewModel.recorder

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import kangparks.android.vostom.utils.constants.getCurrentDate
import java.io.File

class RecordFileViewModel : ViewModel() {
    private val _recordFileList = mutableListOf<File>()
    private val recordFileList: List<File> = _recordFileList

    private val _currentDate = getCurrentDate()

//    private val _currentSize = mutableIntStateOf(0)
    private var _currentRecordFileName: String =
        _currentDate + "-" + recordFileList.size.toString() + ".m4a"

    fun addRecordFile(file: File) {
        _recordFileList.add(file)
    }

    fun getCurrentRecordFileName(): String {
        return _currentDate + "-" + recordFileList.size.toString() + ".m4a"
    }

//    fun getCurrentSize(): Int {
//        return _currentSize.value
//    }

    fun getRecordFileList(): List<File> {
        return recordFileList
    }

    fun sendRecordFileList() {
        // TODO("서버로 녹음 파일 전송하기")
    }

    fun reset() {
        _recordFileList.clear()
//        _currentSize.value = 0
        _currentRecordFileName = getCurrentDate() + "-" + recordFileList.size.toString() + ".m4a"
    }

}