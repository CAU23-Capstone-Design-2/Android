package kangparks.android.vostom.viewModel.bottomsheet

import androidx.compose.runtime.State
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kangparks.android.vostom.models.content.Celebrity
import kangparks.android.vostom.models.content.Music
import kangparks.android.vostom.utils.dummy.dummyOthersItemList
import kangparks.android.vostom.utils.dummy.dummyStarCoverItemList

sealed class OthersContentViewType(val viewType : String){
    object OthersContentList : OthersContentViewType("OthersContentList"),
        State<OthersContentViewType> {
        override val value: OthersContentViewType
            get() = this
    }

    object OthersContentDetail : OthersContentViewType("OthersContentDetail"),
        State<OthersContentViewType> {
        override val value: OthersContentViewType
            get() = this
    }
}

class CelebrityContentViewModel : ViewModel() {
    private val _currentView = MutableLiveData<OthersContentViewType>()
    private val _singerList : MutableLiveData<List<Celebrity>> = MutableLiveData<List<Celebrity>>(listOf())
    private val _currentSinger = MutableLiveData<Celebrity?>()
    private val _currentSingerMusicList : MutableLiveData<List<Music>> = MutableLiveData<List<Music>>(listOf())

    val currentView : LiveData<OthersContentViewType> = _currentView
    val singerList: LiveData<List<Celebrity>> = _singerList
    val currentSigner : LiveData<Celebrity?> = _currentSinger
    val currentSingerMusicList : LiveData<List<Music>> = _currentSingerMusicList

    init {
        _currentView.value = OthersContentViewType.OthersContentList
        _currentSinger.value = null

        _singerList.postValue(dummyOthersItemList)
        _currentSingerMusicList.postValue(dummyStarCoverItemList)
    }

    fun changeView(viewType: OthersContentViewType){
        _currentView.value = viewType
    }

    fun setSinger(celebrity: Celebrity){
        _currentSinger.value = celebrity
    }


}