package kangparks.android.vostom.utils.helper.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.compose.runtime.Composable
import kangparks.android.vostom.services.LearningStateCheckerService

fun<T> startService(
    context: Context,
    service : Class<T>
){
    val intent = Intent(context, service)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        context.startForegroundService(intent)
    } else {
        context.startService(intent)
    }
}