package kangparks.android.vostom.viewModel.player

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.exoplayer2.ExoPlayer
import kangparks.android.vostom.models.content.CoverSong

class ContentPlayerViewModel : ViewModel(){
    private var _exoPlayer : ExoPlayer? = null

    private val _isPlaying : MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    private val _isPaused : MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    private val _currentSong : MutableLiveData<CoverSong?> = MutableLiveData<CoverSong?>(null)
    private val _isShowPlayer : MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)

    val isPlaying : MutableLiveData<Boolean> = _isPlaying
    val isPaused : MutableLiveData<Boolean> = _isPaused
    val currentSong : MutableLiveData<CoverSong?> = _currentSong
    val isShowPlayer : MutableLiveData<Boolean> = _isShowPlayer

    fun setPlayer(player: ExoPlayer){
        _exoPlayer = player
    }

    fun getPlayer() : ExoPlayer? {
        return _exoPlayer
    }

    fun playMusic(song: CoverSong){
        _isPlaying.value = true
        _isPaused.value = false
        _currentSong.value = song
    }

    fun showPlayer(){
        _isShowPlayer.value = true
    }

    fun hidePlayer(){
        _isShowPlayer.value = false
    }

    fun resumeMusic(){
        _exoPlayer?.play()
        _isPaused.value = false
    }

    fun pauseMusic(){
        _exoPlayer?.pause()
        _isPaused.value = true
    }

    fun stopMusic(){
        _exoPlayer?.stop()
        _isPlaying.value = false
        _isPaused.value = false
        _currentSong.value = null
    }
}