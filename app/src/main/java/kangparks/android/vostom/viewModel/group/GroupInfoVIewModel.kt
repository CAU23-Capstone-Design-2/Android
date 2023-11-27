package kangparks.android.vostom.viewModel.group

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GroupInfoVIewModel : ViewModel() {
    private val _currentImgUri : MutableLiveData<Uri?> = MutableLiveData(null)
    val currentImgUri : MutableLiveData<Uri?> = _currentImgUri

    fun setCurrentImgUri(uri : Uri?){
        _currentImgUri.postValue(uri)
    }
}