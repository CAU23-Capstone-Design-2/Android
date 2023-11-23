package kangparks.android.vostom.viewModel.group

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kangparks.android.vostom.models.content.Group
import kangparks.android.vostom.utils.dummy.dummyGroupList
import kangparks.android.vostom.utils.dummy.dummyMyGroupCoverItemList
import kangparks.android.vostom.utils.dummy.dummyMyGroupList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GroupListViewModel : ViewModel(){
    private val coroutineScope = CoroutineScope(viewModelScope.coroutineContext)

    private val _allGroupList : MutableLiveData<List<Group>> = MutableLiveData(null)
    private val _myGroupList : MutableLiveData<List<Group>> = MutableLiveData(null)

    val allGroupList : LiveData<List<Group>> = _allGroupList
    val myGroupList : LiveData<List<Group>> = _myGroupList

    init {
        coroutineScope.launch {
            delay(500)
            _allGroupList.postValue(dummyGroupList)
            delay(1000)
            _myGroupList.postValue(dummyMyGroupList)
        }
    }




}