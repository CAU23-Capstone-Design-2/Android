package kangparks.android.vostom.viewModel.content

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kangparks.android.vostom.models.content.CoverSong
import kangparks.android.vostom.models.content.Group
import kangparks.android.vostom.models.content.Singer

class ContentStoreViewModel : ViewModel(){
    private val _userImgUrl : MutableLiveData<String> = MutableLiveData("")
    private val _userName : MutableLiveData<String> = MutableLiveData("")

    private val _myCoverItemList : MutableLiveData<List<CoverSong>> = MutableLiveData(listOf())
    private val _myGroupCoverItemList : MutableLiveData<List<CoverSong>> = MutableLiveData(listOf())
    private val _othersItemList : MutableLiveData<List<Singer>> = MutableLiveData(listOf())
    private val _likeItemList : MutableLiveData<List<CoverSong>> = MutableLiveData(listOf())

    private val _allGroupList : MutableLiveData<List<Group>> = MutableLiveData(listOf())
    private val _myGroupList : MutableLiveData<List<Group>> = MutableLiveData(listOf())

    val userImgUrl : LiveData<String> = _userImgUrl
    val userName : LiveData<String> = _userName

    val myCoverItemList : LiveData<List<CoverSong>> = _myCoverItemList
    val myGroupCoverItemList : LiveData<List<CoverSong>> = _myGroupCoverItemList
    val othersItemList : LiveData<List<Singer>> = _othersItemList
    val likeItemList : LiveData<List<CoverSong>> = _likeItemList

    val allGroupList : LiveData<List<Group>> = _allGroupList
    val myGroupList : LiveData<List<Group>> = _myGroupList

    fun updateUserImgUrl(imgUrl: String){
        _userImgUrl.postValue(imgUrl)
    }

    fun updateUserName(name: String){
        _userName.postValue(name)
    }

    fun updateMyCoverItemList(itemList: List<CoverSong>){
        _myCoverItemList.postValue(itemList)
    }

    fun updateMyGroupCoverItemList(itemList: List<CoverSong>){
        _myGroupCoverItemList.postValue(itemList)
    }

    fun updateOthersItemList(itemList: List<Singer>){
        _othersItemList.postValue(itemList)
    }

    fun updateLikeItemList(itemList: List<CoverSong>){
        _likeItemList.postValue(itemList)
    }

    fun updateAllGroupList(itemList: List<Group>){
        _allGroupList.postValue(itemList)
    }

    fun updateMyGroupList(itemList: List<Group>){
        _myGroupList.postValue(itemList)
    }
}