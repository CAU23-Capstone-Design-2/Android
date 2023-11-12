package kangparks.android.vostom.utils.helper.file

import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream

fun copyFileToAppDir(
    uri: Uri,
    fileName: String,
    context: Context,
): File {
    val inputStream: InputStream? = context.contentResolver.openInputStream(uri)

    val outputFile = File(context.filesDir, fileName)
    val outputStream: OutputStream = FileOutputStream(outputFile)
    inputStream?.use {
        outputStream.use { output ->
            it.copyTo(output)
        }
    }
    return outputFile
}