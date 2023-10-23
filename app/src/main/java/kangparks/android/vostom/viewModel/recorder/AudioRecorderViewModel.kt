package kangparks.android.vostom.viewModel.recorder

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import kangparks.android.vostom.utils.constants.getCurrentDate
import kangparks.android.vostom.utils.media.recorder.AndroidAudioRecorder
import java.io.File

class AudioRecorderViewModel constructor(
    context: Context, private val filesDir : File
) : ViewModel() {
    private val audioRecorder = AndroidAudioRecorder(context)
    private var audioFile : File? = null

    fun start(fileName : String) {
//        val fileName = getCurrentDate() + ".m4a"
        val file = File(filesDir.absolutePath, fileName)
//        Log.d("AudioRecorderViewModel", "file path: ${file.absolutePath}")
//        val file = getOutputFile()
        audioRecorder.start(file)
        audioFile = file
    }

    fun stop(){
        audioRecorder.stop()
    }

    fun getAudioFile() : File? {
        return audioFile
    }
}