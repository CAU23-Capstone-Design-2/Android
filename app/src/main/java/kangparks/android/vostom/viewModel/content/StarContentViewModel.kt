package kangparks.android.vostom.viewModel.content

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kangparks.android.vostom.models.content.Celebrity
import kangparks.android.vostom.models.content.Music
import kangparks.android.vostom.utils.dummy.dummyStarCoverItemList
import kangparks.android.vostom.utils.networks.content.getCelebrityMusicList
import kangparks.android.vostom.utils.store.getAccessToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class StarContentViewModel : ViewModel(){
    private val coroutineScope = CoroutineScope(viewModelScope.coroutineContext)

    private val _currentSinger : MutableLiveData<Celebrity> = MutableLiveData(null)
    private val _currentSingerContent : MutableLiveData<List<Music>> = MutableLiveData(listOf())

    val currentSinger : LiveData<Celebrity> = _currentSinger
    val currentSingerContent : LiveData<List<Music>> = _currentSingerContent
    fun updateCurrentSinger(
        context: Context,
        singer: Celebrity
    ){
        val accessToken = getAccessToken(context) ?: ""
        _currentSinger.postValue(singer)
        coroutineScope.launch {
            val result = getCelebrityMusicList(
                accessToken = accessToken,
                celebrityId = singer.id,
                context = context
            )

            if(result != null){
                _currentSingerContent.postValue(result!!)
            }
        }
//        _currentSinger.postValue(singer)
//        // TODO(서버에서 가수의 컨텐츠 받아오기)
//        // getStarItems(token, singer.id)
//        _currentSingerContent.postValue(dummyStarCoverItemList)
    }
}