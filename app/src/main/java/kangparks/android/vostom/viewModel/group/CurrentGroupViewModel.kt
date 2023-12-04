package kangparks.android.vostom.viewModel.group

import android.content.Context
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
    private val _currentGroupCoverItemList : MutableLiveData<List<Music>?> = MutableLiveData(null)

    val currentGroup : LiveData<Group?> = _currentGroup
    val currentGroupCoverItemList : LiveData<List<Music>?> = _currentGroupCoverItemList

    fun selectGroup(
        context: Context,
        group : Group
    ){
        _currentGroup.postValue(group)
        coroutineScope.launch {
            delay(500)
//            val result = getGroupMusicList(
//                context = context,
//                groupId = group.teamId
//            )
//            _currentGroupCoverItemList.postValue(result)

            _currentGroupCoverItemList.postValue(dummyStarCoverItemList)
        }
    }

    fun releaseGroup(){
        _currentGroup.postValue(null)
        _currentGroupCoverItemList.postValue(null)
    }

    fun joinCurrentGroup(context: Context){
        coroutineScope.launch {
            joinGroup(context, currentGroup.value!!.teamId)
        }
    }

    fun leaveCurrentGroup(context: Context){
        coroutineScope.launch {
            leaveGroup(context, currentGroup.value!!.teamId)
        }
    }

    fun deleteCurrentGroup(context: Context){
        coroutineScope.launch {
            deleteGroup(context, currentGroup.value!!.teamId)
        }
    }

}