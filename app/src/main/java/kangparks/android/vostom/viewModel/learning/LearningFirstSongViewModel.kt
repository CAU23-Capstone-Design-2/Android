package kangparks.android.vostom.viewModel.learning

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LearningFirstSongViewModel : ViewModel(){
    private val _listOfSong = MutableLiveData<List<String>>(listOf(
        "곰 세 마리", "작은 별", "산토끼", "퐁당퐁당", "나비야"
    ))
    private val _selectedSong = MutableLiveData<String?>(null)

    val listOfSong : LiveData<List<String>> = _listOfSong
    val selectedSong : LiveData<String?> = _selectedSong

    fun selectSong(song : String){
        _selectedSong.value = song
    }
}