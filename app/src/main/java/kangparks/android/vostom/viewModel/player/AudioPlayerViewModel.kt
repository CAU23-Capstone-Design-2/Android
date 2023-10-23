package kangparks.android.vostom.viewModel.player

import android.content.Context
import androidx.lifecycle.ViewModel
import kangparks.android.vostom.utils.media.player.AndroidAudioPlayer
import java.io.File

class AudioPlayerViewModel constructor(
    context: Context,
    audioFile : File?
) : ViewModel() {
    private val audioPlayer = AndroidAudioPlayer(context)
    private var audioFile = audioFile


    fun start(){
        audioPlayer.playFile(audioFile?: return)
    }

    fun stop(){
        audioPlayer.stop()
    }

    fun setAudioFile(audioFile : File){
        this.audioFile = audioFile
    }

    fun isExistAudioFile() : Boolean {
        return audioFile != null
    }
}