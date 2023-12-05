package kangparks.android.vostom.viewModel.content

import android.content.Context
import android.util.Log
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
import kangparks.android.vostom.utils.networks.content.getCelebrityList
import kangparks.android.vostom.utils.networks.content.getUserCoverItems
import kangparks.android.vostom.utils.networks.content.getUserGroupCoverItems
import kangparks.android.vostom.utils.networks.content.getUserLikedCoverItems
import kangparks.android.vostom.utils.networks.group.getGroupList
import kangparks.android.vostom.utils.networks.group.getMyGroupList
import kangparks.android.vostom.utils.networks.user.getUserInfo
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

    fun initHomeContent(
        token : String,
        context : Context
    ){
        if(_isInitHomeContent.value == true) return
        else{
            coroutineScope.launch {
                val userMusicList = getUserCoverItems(accessToken = token)
                Log.d("ContentStoreViewModel", "userMusicList : $userMusicList")
                _myCoverItemList.postValue(userMusicList)

                val groupMusicList = getUserGroupCoverItems(accessToken = token)
                _myGroupCoverItemList.postValue(groupMusicList)

                Log.d("ContentStoreViewModel", "token : $token")

                val celebrityList = getCelebrityList(
                    accessToken = token,
                    context =  context
                )

                if (celebrityList != null) {
                    if(celebrityList.isNotEmpty()){
                        _othersItemList.postValue(celebrityList!!)
                    }
                }

                _isInitHomeContent.postValue(true)

            }
        }
    }

    fun updateHomeContent(
//        token : String,
        context : Context
    ){
        val token= getAccessToken(context)
        coroutineScope.launch {
            val userMusicList = getUserCoverItems(accessToken = token!!)
            Log.d("ContentStoreViewModel", "userMusicList : $userMusicList")
            _myCoverItemList.postValue(userMusicList)

            val groupMusicList = getUserGroupCoverItems(accessToken = token!!)
            _myGroupCoverItemList.postValue(groupMusicList)

            Log.d("ContentStoreViewModel", "token : $token")

            val celebrityList = getCelebrityList(
                accessToken = token!!,
                context =  context
            )

            if (celebrityList != null) {
                if(celebrityList.isNotEmpty()){
                    _othersItemList.postValue(celebrityList!!)
                }
            }

            _isInitHomeContent.postValue(true)

        }
    }

    fun initProfileContent(context : Context){
        val token = getAccessToken(context)
        if(_isInitProfileContent.value == true) return
        else{
            coroutineScope.launch{
                val result = getUserInfo(token!!)
                _userImgUrl.postValue(result.profileImage)
                _userName.postValue(result.nickname)
                delay(500)

                val likedMusicList = getUserLikedCoverItems(token!!)
                _likeItemList.postValue(likedMusicList)
            }
        }
    }

    fun updateProfileContent(context : Context){
        val token = getAccessToken(context)
        coroutineScope.launch{
            val result = getUserInfo(token!!)
//            Log.d()
            _userImgUrl.postValue(result.profileImage)
            _userName.postValue(result.nickname)
            delay(500)

            val likedMusicList = getUserLikedCoverItems(token!!)
            _likeItemList.postValue(likedMusicList)
        }
    }

    fun initGroupContent(context: Context){
        if(_isInitGroupContent.value == true) return
        else{
            coroutineScope.launch {
                delay(500)
                val allGroup = getGroupList(context)
                _allGroupList.postValue(allGroup)

//                _allGroupList.postValue(dummyGroupList)
                delay(500)

                val myGroup = getMyGroupList(context)
                _myGroupList.postValue(myGroup)
//                _myGroupList.postValue(dummyMyGroupList)
            }
        }
    }

    fun updateGroupContent(context: Context) {
        coroutineScope.launch {
            delay(500)
            val allGroup = getGroupList(context)
            _allGroupList.postValue(allGroup)

//                _allGroupList.postValue(dummyGroupList)
            delay(500)

            val myGroup = getMyGroupList(context)
            _myGroupList.postValue(myGroup)
//                _myGroupList.postValue(dummyMyGroupList)
        }
    }

//
//    fun updatelikeInfoOfMusic(music : Music){
//
//    }

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