package kangparks.android.vostom.utils.helper.file

import android.os.Environment
import android.util.Log
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getOutputFile() : File {
//    val fileName = getCurrentDate() + ".m4a"
    Log.d("GET_OUTPUT_FILE", "${Environment.getExternalStorageDirectory()}")
    val mediaStorageDir = File(Environment.DIRECTORY_DOWNLOADS, "/Vostom")
    if (!mediaStorageDir.exists()) {
        mediaStorageDir.mkdirs()
    }
    val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())

    return File("${mediaStorageDir.path}${File.separator}recording_$timestamp.m4a")

//    val dir = File("/sdcard/Vostom")
//    if (!dir.exists()) {
//        dir.mkdir()
//    }
//    return File(dir, fileName)
}