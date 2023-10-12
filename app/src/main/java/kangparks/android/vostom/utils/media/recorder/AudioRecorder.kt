package kangparks.android.vostom.utils.media.recorder

import java.io.File

interface AudioRecorder {
    fun start(outputFile : File)
    fun stop()
}