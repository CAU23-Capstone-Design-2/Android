package kangparks.android.vostom.viewModel.group

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kangparks.android.vostom.models.content.CoverSong
import kangparks.android.vostom.models.content.Group
import kangparks.android.vostom.utils.dummy.dummyOthersItemList
import kangparks.android.vostom.utils.dummy.dummyStarCoverItemList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CurrentGroupViewModel : ViewModel() {
    private val coroutineScope = CoroutineScope(viewModelScope.coroutineContext)

    private val _currentGroup : MutableLiveData<Group?> = MutableLiveData(null)
    private val _currentGroupCoverItemList : MutableLiveData<List<CoverSong>?> = MutableLiveData(null)

    val currentGroup : LiveData<Group?> = _currentGroup
    val currentGroupCoverItemList : LiveData<List<CoverSong>?> = _currentGroupCoverItemList

    fun selectGroup(group : Group){
        _currentGroup.postValue(group)
        coroutineScope.launch {
            delay(500)
            _currentGroupCoverItemList.postValue(dummyStarCoverItemList)
        }
    }

    fun releaseGroup(){
        _currentGroup.postValue(null)
        _currentGroupCoverItemList.postValue(null)
    }

}