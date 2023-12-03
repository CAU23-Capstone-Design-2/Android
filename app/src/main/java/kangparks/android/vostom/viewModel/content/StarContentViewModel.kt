package kangparks.android.vostom.viewModel.content

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kangparks.android.vostom.models.content.Celebrity
import kangparks.android.vostom.models.content.Music
import kangparks.android.vostom.utils.dummy.dummyStarCoverItemList

class StarContentViewModel : ViewModel(){
    private val _currentSinger : MutableLiveData<Celebrity> = MutableLiveData(null)
    private val _currentSingerContent : MutableLiveData<List<Music>> = MutableLiveData(listOf())

    val currentSinger : LiveData<Celebrity> = _currentSinger
    val currentSingerContent : LiveData<List<Music>> = _currentSingerContent
    fun updateCurrentSinger(
        accessToken : String,
        singer: Celebrity
    ){
        _currentSinger.postValue(singer)
        // TODO(서버에서 가수의 컨텐츠 받아오기)
        // getStarItems(token, singer.id)
        _currentSingerContent.postValue(dummyStarCoverItemList)
    }
}