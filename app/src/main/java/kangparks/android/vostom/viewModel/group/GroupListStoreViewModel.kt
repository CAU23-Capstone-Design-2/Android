package kangparks.android.vostom.viewModel.group

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kangparks.android.vostom.models.content.Group

class GroupListStoreViewModel : ViewModel(){
    private val _allGroupList : MutableLiveData<List<Group>> = MutableLiveData(null)
    private val _myGroupList : MutableLiveData<List<Group>> = MutableLiveData(null)

    val allGroupList : LiveData<List<Group>> = _allGroupList
    val myGroupList : LiveData<List<Group>> = _myGroupList

    fun updateAllGroupList(itemList: List<Group>){
        _allGroupList.postValue(itemList)
    }

    fun updateMyGroupList(itemList: List<Group>){
        _myGroupList.postValue(itemList)
    }

}