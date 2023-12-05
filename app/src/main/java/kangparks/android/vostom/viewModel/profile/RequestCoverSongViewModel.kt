package kangparks.android.vostom.viewModel.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kangparks.android.vostom.models.content.Music
import kangparks.android.vostom.utils.dummy.dummyMyCoverItemList
import kangparks.android.vostom.utils.networks.content.getRequestMusicList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class RequestCoverSongViewModel(token : String) : ViewModel() {
    private val coroutineScope = CoroutineScope(viewModelScope.coroutineContext)
    private val _requestCoverSongList : MutableLiveData<List<Music>> = MutableLiveData(listOf<Music>())

    val requestCoverSongList : LiveData<List<Music>> = _requestCoverSongList

    init {
        coroutineScope.launch {
            Log.d("RequestCoverSongViewModel", "init 시작")
            val result = getRequestMusicList(token)

            _requestCoverSongList.postValue(result)
        }
    }

    fun update(token : String){
        coroutineScope.launch {
            Log.d("RequestCoverSongViewModel", "업데이트")
            val result = getRequestMusicList(token)

            _requestCoverSongList.postValue(result)
        }
    }
}