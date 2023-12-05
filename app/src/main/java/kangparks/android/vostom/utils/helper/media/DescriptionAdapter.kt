package kangparks.android.vostom.utils.helper.media

import android.app.PendingIntent
import android.graphics.Bitmap
import android.media.session.MediaSession
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerNotificationManager

class DescriptionAdapter(private val mediaSession: MediaSession) : PlayerNotificationManager.MediaDescriptionAdapter {
    override fun getCurrentContentTitle(player: Player): CharSequence {
        TODO("Not yet implemented")

        return "title"
    }

    override fun createCurrentContentIntent(player: Player): PendingIntent? {
        TODO("Not yet implemented")

        return null
    }

    override fun getCurrentContentText(player: Player): CharSequence? {
        TODO("Not yet implemented")

        return null
    }

    override fun getCurrentLargeIcon(
        player: Player,
        callback: PlayerNotificationManager.BitmapCallback
    ): Bitmap? {
        TODO("Not yet implemented")

        return null
    }

}