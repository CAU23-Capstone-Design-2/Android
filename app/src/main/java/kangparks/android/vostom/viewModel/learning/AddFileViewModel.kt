package kangparks.android.vostom.viewModel.learning

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.io.File

class AddFileViewModel : ViewModel() {
    private val _recordFileFromDeviceList = mutableListOf<File>()
    private val recordFileFromDeviceList: List<File> = _recordFileFromDeviceList



    private val _sizeOfAddedRecordFiles : MutableLiveData<Int> = MutableLiveData<Int>(_recordFileFromDeviceList.size)
    val sizeOfAddedRecordFiles : MutableLiveData<Int> = _sizeOfAddedRecordFiles

    fun addRecordFileFromDevice(file: File) {
        _recordFileFromDeviceList.add(file)
        _sizeOfAddedRecordFiles.value = _recordFileFromDeviceList.size
    }

    fun getRecordFileFromDeviceList(): List<File> {
        return recordFileFromDeviceList
    }
}