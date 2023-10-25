package kangparks.android.vostom.viewModel.learning

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kangparks.android.vostom.models.item.YoutubePlayItem
import kangparks.android.vostom.utils.helper.search.getSongList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GuideSingingViewModel : ViewModel(){
    private val _listOfSong = MutableLiveData<List<YoutubePlayItem>?>(listOf())
    private val _selectedSong = MutableLiveData<YoutubePlayItem?>(null)

    val listOfSong : LiveData<List<YoutubePlayItem>?> = _listOfSong
    val selectedSong : LiveData<YoutubePlayItem?> = _selectedSong

    fun searchSongWithKeyword(keyword : String) {
        GlobalScope.launch(Dispatchers.IO){
            val result = getSongList(keyword = keyword)
            if(!result.isNullOrEmpty())
                _listOfSong.postValue(result)
        }

    }

    fun selectSong(song : String){
//        _selectedSong.value = song
    }
}