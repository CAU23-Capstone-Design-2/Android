package kangparks.android.vostom.viewModel.group

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kangparks.android.vostom.models.content.Group
import kangparks.android.vostom.utils.networks.group.createGroup
import kangparks.android.vostom.utils.networks.group.updateGroup
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GroupInfoVIewModel : ViewModel() {
    private val coroutineScope = CoroutineScope(viewModelScope.coroutineContext)

    private val _currentGroup : MutableLiveData<Group?> = MutableLiveData(null)
    private val _currentImgUri : MutableLiveData<Uri?> = MutableLiveData(null)

    val currentGroup : MutableLiveData<Group?> = _currentGroup
    val currentImgUri : MutableLiveData<Uri?> = _currentImgUri

    fun setCurrentImgUri(uri : Uri?){
        _currentImgUri.postValue(uri)
    }

    fun selectGroup(group : Group){
        _currentGroup.postValue(group)
    }

    fun createGroupWithInfo(
        context : Context,
        name : String,
        description : String,
    ){
        coroutineScope.launch {
            createGroup(
                context = context,
                teamName = name,
                teamDescription = description,
                teamImgUri = currentImgUri.value.toString()
            )
        }
        CoroutineScope(Dispatchers.Main).launch{
            Toast.makeText(context, "그룹 생성 완료", Toast.LENGTH_SHORT).show()
        }
    }

    fun updateGroupWithInfo(
        context : Context,
        id : String,
        name : String,
        description : String,
    ){
        coroutineScope.launch {
            updateGroup(
                context = context,
                teamId = id,
                teamName = name,
                teamDescription = description,
                teamImgUri = currentImgUri.value.toString()
            )
        }
    }
}