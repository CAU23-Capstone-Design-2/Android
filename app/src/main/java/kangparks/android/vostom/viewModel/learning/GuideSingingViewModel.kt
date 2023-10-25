package kangparks.android.vostom.viewModel.learning

import android.util.Log
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
    private val _selectedSong = MutableLiveData<Int>(-1)
    private val _isSearching = MutableLiveData<Boolean>(false)
    private val _isNothingResult = MutableLiveData<Boolean>(false)

    val listOfSong : LiveData<List<YoutubePlayItem>?> = _listOfSong
    val selectedSong : LiveData<Int> = _selectedSong
    val isSearching : LiveData<Boolean> = _isSearching
    val isNothingResult : LiveData<Boolean> = _isNothingResult

    fun searchSongWithKeyword(keyword : String) {
        _isSearching.value = true
        _isNothingResult.value = false
        _listOfSong.value = listOf()
        GlobalScope.launch(Dispatchers.IO){
            val result = getSongList(keyword = keyword)
            if(result != null) {
                _listOfSong.postValue(result)
            }
            else if (result == null) {
                _isNothingResult.postValue(true)
            }
            _isSearching.postValue(false)
        }
    }

    fun selectSong(id : Int){
        _selectedSong.value = id
    }
}