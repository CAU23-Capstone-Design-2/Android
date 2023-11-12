package kangparks.android.vostom.viewModel.learning

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.io.File

class AddFileViewModel : ViewModel() {
    private val _recordFileFromDeviceList = mutableListOf<File>()
    private val recordFileFromDeviceList: List<File> = _recordFileFromDeviceList

    private val _filesNameofAddedList : MutableLiveData<Set<String>> = MutableLiveData<Set<String>>(setOf())
    val filesNameofAddedList : MutableLiveData<Set<String>> = _filesNameofAddedList

    private val _sizeOfAddedRecordFiles : MutableLiveData<Int> = MutableLiveData<Int>(_recordFileFromDeviceList.size)
    val sizeOfAddedRecordFiles : MutableLiveData<Int> = _sizeOfAddedRecordFiles

    fun addRecordFileFromDevice(file: File, context: Context) {
        if(_recordFileFromDeviceList.contains(file)) {
            Toast.makeText(context, "이미 추가된 파일입니다.", Toast.LENGTH_SHORT).show()
            return
        }
        _recordFileFromDeviceList.add(file)
        _sizeOfAddedRecordFiles.value = _recordFileFromDeviceList.size
        _filesNameofAddedList.value = _filesNameofAddedList.value?.plus(file.name)
    }

    fun getRecordFileFromDeviceList(): List<File> {
        return recordFileFromDeviceList
    }
}