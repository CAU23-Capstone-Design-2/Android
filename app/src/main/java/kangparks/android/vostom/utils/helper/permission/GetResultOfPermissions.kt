package kangparks.android.vostom.utils.helper.permission

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

val permissions = arrayOf(
    Manifest.permission.RECORD_AUDIO,
    Manifest.permission.FOREGROUND_SERVICE,
    Manifest.permission.POST_NOTIFICATIONS
//    Manifest.permission.BLUETOOTH_CONNECT
//    Manifest.permission.READ_EXTERNAL_STORAGE,
)

fun getResultOfCurrentPermissions(context: Context): Boolean{
    return (ContextCompat.checkSelfPermission(
        context,
        permissions[0]
    ) == PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(
        context,
        permissions[1]
    ) == PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(
        context,
        permissions[2]
    ) == PackageManager.PERMISSION_GRANTED)
//            && ContextCompat.checkSelfPermission(
//        context,
//        permissions[1]
//    ) == PackageManager.PERMISSION_GRANTED)
}