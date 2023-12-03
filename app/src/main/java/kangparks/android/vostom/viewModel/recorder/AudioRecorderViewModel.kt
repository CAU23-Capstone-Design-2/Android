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
//    private var currentFileName = ""

    fun start(fileName : String) {
//        currentFileName = fileName
//        val fileName = getCurrentDate() + ".m4a"
        val file = File(filesDir.absolutePath, fileName)
        Log.d("AudioRecorderViewModel", "file path: ${file.absolutePath}")
//        val file = getOutputFile()
        audioRecorder.start(file)
        audioFile = file
    }

    fun reset(){
        audioRecorder.clear()
    }

    fun pause(){
        audioRecorder.pause()
    }

    fun stop(){
        audioRecorder.stop()
    }

    fun getAudioFile() : File? {
        return audioFile
    }

    fun getOutputFile(fileName: String) : File? {
//        val fileName = currentFileName
        return File(filesDir.absolutePath, fileName)?:null
    }
}