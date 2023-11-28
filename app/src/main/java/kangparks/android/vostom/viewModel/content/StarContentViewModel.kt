package kangparks.android.vostom.viewModel.content

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kangparks.android.vostom.models.content.Singer
import kangparks.android.vostom.models.content.Song
import kangparks.android.vostom.utils.dummy.othersContentItemList

class StarContentViewModel : ViewModel(){
    private val _currentSinger : MutableLiveData<Singer> = MutableLiveData(Singer())
    private val _currentSingerContent : MutableLiveData<List<Song>> = MutableLiveData(listOf())

    val currentSinger : LiveData<Singer> = _currentSinger
    val currentSingerContent : LiveData<List<Song>> = _currentSingerContent
    fun updateCurrentSinger(
        accessToken : String,
        singer: Singer
    ){
        _currentSinger.postValue(singer)
        // TODO(서버에서 가수의 컨텐츠 받아오기)
        // getStarItems(token, singer.id)
        _currentSingerContent.postValue(othersContentItemList)
    }
}