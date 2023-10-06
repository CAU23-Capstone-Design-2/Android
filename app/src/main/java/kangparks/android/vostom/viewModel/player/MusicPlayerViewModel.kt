package kangparks.android.vostom.viewModel.player

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.exoplayer2.SimpleExoPlayer

class MusicPlayerViewModel constructor(private val player: SimpleExoPlayer) : ViewModel() {

    private val _isPlaying = MutableLiveData<Boolean>(player.isPlaying)
    val isPlaying: LiveData<Boolean> = _isPlaying

    fun playMusic() {
        player.prepare()
        player.play()
        _isPlaying.value = true
    }

    fun pauseMusic() {
        player.pause()
        _isPlaying.value = false
    }

    fun stopMusic() {
        player.stop()
        player.seekTo(0)
        _isPlaying.value = false
    }

    override fun onCleared() {
        super.onCleared()
        _isPlaying.value = false
        player.release()
    }
}