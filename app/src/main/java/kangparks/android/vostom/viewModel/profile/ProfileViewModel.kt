package kangparks.android.vostom.viewModel.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kangparks.android.vostom.models.content.CoverSong
import kangparks.android.vostom.utils.dummy.dummyGroupList
import kangparks.android.vostom.utils.dummy.dummyMyGroupCoverItemList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProfileViewModel(
    token : String,
    userImgUrlFromStore : String,
    userNameFromStore : String,
    likedCoverItemListFromStore : List<CoverSong>
) : ViewModel() {
    private val coroutineScope = CoroutineScope(viewModelScope.coroutineContext)

    private val _userImgUrl : MutableLiveData<String> = MutableLiveData("")
    private val _userName : MutableLiveData<String> = MutableLiveData("")

    private val _likedCoverItemList : MutableLiveData<List<CoverSong>> = MutableLiveData(listOf())

    val userImgUrl : LiveData<String> = _userImgUrl
    val userName : LiveData<String> = _userName
    val likedCoverItemList : LiveData<List<CoverSong>> = _likedCoverItemList

    init {
        if(userNameFromStore.isNotEmpty()){
            Log.d("ProfileViewModel", "init: $userNameFromStore, $likedCoverItemListFromStore")
            _userImgUrl.postValue(userImgUrlFromStore)
            _userName.postValue(userNameFromStore)
            _likedCoverItemList.postValue(likedCoverItemListFromStore)
        }
        else{
            Log.d("ProfileViewModel", "init: $userNameFromStore $likedCoverItemListFromStore")
            coroutineScope.launch{
                delay(500)
                _userImgUrl.postValue("https://avatars.githubusercontent.com/u/29995267?s=70&v=4")
                _userName.postValue("박상현")
                delay(500)
                _likedCoverItemList.postValue(dummyMyGroupCoverItemList)
            }
        }

    }
}