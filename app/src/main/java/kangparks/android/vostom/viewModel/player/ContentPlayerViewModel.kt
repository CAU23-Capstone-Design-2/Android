package kangparks.android.vostom.viewModel.player

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kangparks.android.vostom.models.content.CoverSong

class ContentPlayerViewModel : ViewModel(){
    private val _isPlaying : MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    private val _isPaused : MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    private val _currentSong : MutableLiveData<CoverSong?> = MutableLiveData<CoverSong?>(null)

    val isPlaying : MutableLiveData<Boolean> = _isPlaying
    val isPaused : MutableLiveData<Boolean> = _isPaused
    val currentSong : MutableLiveData<CoverSong?> = _currentSong

    fun playMusic(song: CoverSong){
        _isPlaying.value = true
        _isPaused.value = false
        _currentSong.value = song
    }

    fun resumeMusic(){
        _isPaused.value = false
    }

    fun pauseMusic(){
        _isPaused.value = true
    }

    fun stopMusic(){
        _isPlaying.value = false
        _isPaused.value = false
        _currentSong.value = null
    }
}