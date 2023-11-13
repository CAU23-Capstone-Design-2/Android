package kangparks.android.vostom.viewModel.content

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kangparks.android.vostom.models.content.CoverSong
import kangparks.android.vostom.models.content.Singer

class ContentStoreViewModel : ViewModel(){
    private val _myCoverItemList : MutableLiveData<List<CoverSong>> = MutableLiveData(listOf())
    private val _myGroupCoverItemList : MutableLiveData<List<CoverSong>> = MutableLiveData(listOf())
    private val _othersItemList : MutableLiveData<List<Singer>> = MutableLiveData(listOf())

    val myCoverItemList : LiveData<List<CoverSong>> = _myCoverItemList
    val myGroupCoverItemList : LiveData<List<CoverSong>> = _myGroupCoverItemList
    val othersItemList : LiveData<List<Singer>> = _othersItemList

    fun updateMyCoverItemList(itemList: List<CoverSong>){
        _myCoverItemList.postValue(itemList)
    }

    fun updateMyGroupCoverItemList(itemList: List<CoverSong>){
        _myGroupCoverItemList.postValue(itemList)
    }

    fun updateOthersItemList(itemList: List<Singer>){
        _othersItemList.postValue(itemList)
    }
}