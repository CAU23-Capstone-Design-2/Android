package kangparks.android.vostom.viewModel.group

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kangparks.android.vostom.models.content.Group
import kangparks.android.vostom.models.content.Music
import kangparks.android.vostom.utils.dummy.dummyOthersItemList
import kangparks.android.vostom.utils.dummy.dummyStarCoverItemList
import kangparks.android.vostom.utils.networks.group.deleteGroup
import kangparks.android.vostom.utils.networks.group.getGroupMusicList
import kangparks.android.vostom.utils.networks.group.joinGroup
import kangparks.android.vostom.utils.networks.group.leaveGroup
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CurrentGroupViewModel : ViewModel() {
    private val coroutineScope = CoroutineScope(viewModelScope.coroutineContext)

    private val _currentGroup : MutableLiveData<Group?> = MutableLiveData(null)
    private val _currentGroupCoverItemList : MutableLiveData<List<Music>?> = MutableLiveData(listOf())
    private val _currentGroupCoverMyItemList : MutableLiveData<List<Music>?> = MutableLiveData(listOf())
    private val _participate : MutableLiveData<Boolean> = MutableLiveData(false)

    val currentGroup : LiveData<Group?> = _currentGroup
    val currentGroupCoverItemList : LiveData<List<Music>?> = _currentGroupCoverItemList
    val currentGroupCoverMyItemList : LiveData<List<Music>?> = _currentGroupCoverMyItemList
    val participate : LiveData<Boolean> = _participate

    fun selectGroup(
        userId : Int,
        context: Context,
        group : Group
    ){
        if(group.isMember || group.isLeader) {
            _participate.postValue(true)
        }
        _currentGroup.postValue(group)
        coroutineScope.launch {
            delay(500)
            val result = getGroupMusicList(
                context = context,
                groupId = group.teamId
            )
            _currentGroupCoverItemList.postValue(result)

            _currentGroupCoverMyItemList.postValue(result.filter { it.userId == userId })
            Log.d("network", "selectGroup : ${_currentGroupCoverMyItemList.value}")
//            _currentGroupCoverItemList.postValue(dummyStarCoverItemList)
        }
    }

    fun setParticipate(participate : Boolean){
        _participate.postValue(participate)
    }

    fun releaseGroup(){
        _currentGroup.postValue(null)
        _currentGroupCoverItemList.postValue(null)
    }

    fun joinCurrentGroup(context: Context){
        coroutineScope.launch {
            joinGroup(context, currentGroup.value!!.teamId)
            _currentGroup.postValue(Group(
                teamId = currentGroup.value!!.teamId,
                title = currentGroup.value!!.title,
                description = currentGroup.value!!.description,
                groupImgUri = currentGroup.value!!.groupImgUri,
                userId = currentGroup.value!!.userId,
                userName = currentGroup.value!!.userName,
                userImgUri  = currentGroup.value!!.userImgUri,
                groupMemberCount = currentGroup.value!!.groupMemberCount+1,
                isMember = true,
                isLeader = currentGroup.value!!.isLeader
            ))
        }
    }

    fun leaveCurrentGroup(context: Context){
        coroutineScope.launch {
            leaveGroup(context, currentGroup.value!!.teamId)
            _currentGroup.postValue(Group(
                teamId = currentGroup.value!!.teamId,
                title = currentGroup.value!!.title,
                description = currentGroup.value!!.description,
                groupImgUri = currentGroup.value!!.groupImgUri,
                userId = currentGroup.value!!.userId,
                userName = currentGroup.value!!.userName,
                userImgUri  = currentGroup.value!!.userImgUri,
                groupMemberCount = currentGroup.value!!.groupMemberCount-1,
                isMember = false,
                isLeader = currentGroup.value!!.isLeader
            ))
        }
    }

    fun deleteCurrentGroup(context: Context){
        coroutineScope.launch {
            deleteGroup(context, currentGroup.value!!.teamId)
        }
    }

}