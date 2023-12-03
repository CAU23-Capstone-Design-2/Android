package kangparks.android.vostom.viewModel.bottomsheet

import android.content.Context
import androidx.compose.runtime.State
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kangparks.android.vostom.models.content.Celebrity
import kangparks.android.vostom.models.content.Music
import kangparks.android.vostom.utils.dummy.dummyOthersItemList
import kangparks.android.vostom.utils.dummy.dummyStarCoverItemList
import kangparks.android.vostom.utils.networks.content.getCelebrityList
import kangparks.android.vostom.utils.networks.content.getCelebrityMusicList
import kangparks.android.vostom.utils.store.getAccessToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

sealed class CelebrityContentViewType(val viewType : String){
    object CelebrityContentList : CelebrityContentViewType("CelebrityContentList"),
        State<CelebrityContentViewType> {
        override val value: CelebrityContentViewType
            get() = this
    }

    object CelebrityContentDetail : CelebrityContentViewType("CelebrityContentDetail"),
        State<CelebrityContentViewType> {
        override val value: CelebrityContentViewType
            get() = this
    }
}

class CelebrityContentViewModel : ViewModel() {
    private val coroutineScope = CoroutineScope(viewModelScope.coroutineContext)

    private val _currentView = MutableLiveData<CelebrityContentViewType>()
    private val _celebrityList : MutableLiveData<List<Celebrity>> = MutableLiveData<List<Celebrity>>(listOf())
    private val _currentCelebrity = MutableLiveData<Celebrity?>()
    private val _currentCelebrityMusicList : MutableLiveData<List<Music>> = MutableLiveData<List<Music>>(listOf())

    val currentView : LiveData<CelebrityContentViewType> = _currentView
    val celebrityList: LiveData<List<Celebrity>> = _celebrityList
    val currentCelebrity : LiveData<Celebrity?> = _currentCelebrity
    val currentCelebrityMusicList : LiveData<List<Music>> = _currentCelebrityMusicList

    init {
        _currentView.value = CelebrityContentViewType.CelebrityContentList
        _currentCelebrity.value = null
    }

    fun updateCelebrityList(
        context : Context
    ){
        val token = getAccessToken(context)
        if(_celebrityList.value!!.isEmpty()) {
//            _celebrityList.postValue(dummyOthersItemList)

            coroutineScope.launch {
                 val result = token?.let {
                     getCelebrityList(
                         accessToken = it,
                         context = context
                     )
                 }

                result?.let {
                    _celebrityList.postValue(it)
                }
            }
        }
    }

    fun setAndUpdateCelebrityMusicList(
        celebrity: Celebrity,
        context : Context
    ){
        if(celebrity.celebrityName == _currentCelebrity.value?.celebrityName) return
        _currentCelebrity.postValue(celebrity)

        val token = getAccessToken(context)
        coroutineScope.launch {
            val result = token?.let {
                getCelebrityMusicList(
                    accessToken = it,
                    celebrityId = celebrity.id,
                    context = context
                )
            }
            if(result != null){
                _currentCelebrityMusicList.postValue(result)
            }
        }

//        _currentCelebrityMusicList.postValue(dummyStarCoverItemList)
    }

    fun changeView(viewType: CelebrityContentViewType){
        _currentView.value = viewType
    }

    fun resetToCurrentView(){
        _currentView.value = CelebrityContentViewType.CelebrityContentList
    }
}