package kangparks.android.vostom.viewModel.videobackground

import androidx.lifecycle.ViewModel
import com.google.android.exoplayer2.ExoPlayer

class VideoBackgroundViewModel constructor(private val player: ExoPlayer) : ViewModel() {
    init {
        player.prepare()
    }

    fun playVideo() {
        player.play()
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }
}