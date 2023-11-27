package kangparks.android.vostom.viewModel.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kangparks.android.vostom.models.content.CoverSong
import kangparks.android.vostom.models.content.Singer
import kangparks.android.vostom.utils.dummy.dummyMyCoverItemList
import kangparks.android.vostom.utils.dummy.dummyMyGroupCoverItemList
import kangparks.android.vostom.utils.dummy.dummyOthersItemList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel(token : String) : ViewModel() {
    private val coroutineScope = CoroutineScope(viewModelScope.coroutineContext)

    private val _myCoverItemList : MutableLiveData<List<CoverSong>> = MutableLiveData(listOf())
    private val _myGroupCoverItemList : MutableLiveData<List<CoverSong>> = MutableLiveData(listOf())
    private val _othersItemList : MutableLiveData<List<Singer>> = MutableLiveData(listOf())

    val myCoverItemList : LiveData<List<CoverSong>> = _myCoverItemList
    val myGroupCoverItemList : LiveData<List<CoverSong>> = _myGroupCoverItemList
    val othersItemList : LiveData<List<Singer>> = _othersItemList

    init {
        coroutineScope.launch {
//            _myCoverItemList.postValue(getUserCoverItems(accessToken))
//            _myGroupCoverItemList.postValue(getUserGroupCoverItems(accessToken))
//            _othersItemList.postValue(getStarList(accessToken))
            delay(1000)
            _myCoverItemList.postValue(dummyMyCoverItemList)
            delay(500)
            _myGroupCoverItemList.postValue(dummyMyGroupCoverItemList)
            delay(500)
            _othersItemList.postValue(dummyOthersItemList)
        }
    }

    fun updateContent(){
        // TODO("서버 연동")
    }
}