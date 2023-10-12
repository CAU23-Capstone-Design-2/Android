package kangparks.android.vostom.utils.media.player

import java.io.File

interface AudioPlayer {
    fun playFile(file : File)
    fun stop()
}