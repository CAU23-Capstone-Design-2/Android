package kangparks.android.vostom.viewModel.content

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kangparks.android.vostom.models.content.Celebrity
import kangparks.android.vostom.models.content.Group
import kangparks.android.vostom.models.content.Music
import kangparks.android.vostom.utils.dummy.dummyGroupList
import kangparks.android.vostom.utils.dummy.dummyMyCoverItemList
import kangparks.android.vostom.utils.dummy.dummyMyGroupCoverItemList
import kangparks.android.vostom.utils.dummy.dummyMyGroupList
import kangparks.android.vostom.utils.dummy.dummyOthersItemList
import kangparks.android.vostom.utils.store.getAccessToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ContentStoreViewModel(
    private val applicationContext: Context
) : ViewModel(){
    private val coroutineScope = CoroutineScope(viewModelScope.coroutineContext)

    private val _isInitProfileContent : MutableLiveData<Boolean> = MutableLiveData(false)
    private val _userImgUrl : MutableLiveData<String> = MutableLiveData("")
    private val _userName : MutableLiveData<String> = MutableLiveData("")
    private val _likeItemList : MutableLiveData<List<Music>> = MutableLiveData(listOf())

    private val _isInitHomeContent : MutableLiveData<Boolean> = MutableLiveData(false)
    private val _myCoverItemList : MutableLiveData<List<Music>> = MutableLiveData(listOf())
    private val _myGroupCoverItemList : MutableLiveData<List<Music>> = MutableLiveData(listOf())
    private val _othersItemList : MutableLiveData<List<Celebrity>> = MutableLiveData(listOf())

    private val _isInitGroupContent : MutableLiveData<Boolean> = MutableLiveData(false)
    private val _allGroupList : MutableLiveData<List<Group>> = MutableLiveData(listOf())
    private val _myGroupList : MutableLiveData<List<Group>> = MutableLiveData(listOf())


    val userImgUrl : LiveData<String> = _userImgUrl
    val userName : LiveData<String> = _userName

    val myCoverItemList : LiveData<List<Music>> = _myCoverItemList
    val myGroupCoverItemList : LiveData<List<Music>> = _myGroupCoverItemList
    val othersItemList : LiveData<List<Celebrity>> = _othersItemList
    val likeItemList : LiveData<List<Music>> = _likeItemList

    val allGroupList : LiveData<List<Group>> = _allGroupList
    val myGroupList : LiveData<List<Group>> = _myGroupList

    fun initHomeContent(){
//        val token =getAccessToken()
        if(_isInitHomeContent.value == true) return
        else{
            coroutineScope.launch {
//            _myCoverItemList.postValue(getUserCoverItems(accessToken))
//            _myGroupCoverItemList.postValue(getUserGroupCoverItems(accessToken))
//            _othersItemList.postValue(getStarList(accessToken))
                delay(500)
                _myCoverItemList.postValue(dummyMyCoverItemList)
                delay(500)
                _myGroupCoverItemList.postValue(dummyMyGroupCoverItemList)
                delay(500)
                _othersItemList.postValue(dummyOthersItemList)
            }
        }
    }

    fun initProfileContent(){
        if(_isInitProfileContent.value == true) return
        else{
            coroutineScope.launch{
                delay(500)
                _userImgUrl.postValue("https://avatars.githubusercontent.com/u/29995267?s=70&v=4")
                _userName.postValue("박상현")
                delay(500)
                _likeItemList.postValue(dummyMyGroupCoverItemList)
            }
        }
    }

    fun initGroupContent(){
        if(_isInitGroupContent.value == true) return
        else{
            coroutineScope.launch {
                delay(500)
                _allGroupList.postValue(dummyGroupList)
                delay(500)
                _myGroupList.postValue(dummyMyGroupList)
            }
        }
    }

    fun updateUserImgUrl(imgUrl: String){
        _userImgUrl.postValue(imgUrl)
    }

    fun updateUserName(name: String){
        _userName.postValue(name)
    }

    fun updateMyCoverItemList(itemList: List<Music>){
        _myCoverItemList.postValue(itemList)
    }

    fun updateMyGroupCoverItemList(itemList: List<Music>){
        _myGroupCoverItemList.postValue(itemList)
    }

    fun updateOthersItemList(itemList: List<Celebrity>){
        _othersItemList.postValue(itemList)
    }

    fun updateLikeItemList(itemList: List<Music>){
        _likeItemList.postValue(itemList)
    }

    fun updateAllGroupList(itemList: List<Group>){
        _allGroupList.postValue(itemList)
    }

    fun updateMyGroupList(itemList: List<Group>){
        _myGroupList.postValue(itemList)
    }
}