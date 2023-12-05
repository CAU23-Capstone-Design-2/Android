package kangparks.android.vostom.services

import android.app.NotificationChannel.DEFAULT_CHANNEL_ID
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerNotificationManager
import kangparks.android.vostom.R
import kangparks.android.vostom.utils.helper.media.DescriptionAdapter

class MusicService : Service() {
//    private val TAG = "MediaPlayerService"
//    val context = this
//
//    private lateinit var player: SimpleExoPlayer
//    private lateinit var notificationManager: PlayerNotificationManager
//    private val notificationId = 1
//
//    private val playlist = mutableListOf<String>() // URLs of songs
//    private var currentSongIndex = 0
//
//    private val binder = LocalBinder()
//
//    inner class LocalBinder : Binder() {
//        fun getService(): MusicService = this@MusicService
//    }
    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
//
//    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        initializePlayer()
//        return START_STICKY
//    }
//
//    private fun initializePlayer() {
//        player = SimpleExoPlayer.Builder(this).build()
//        player.setMediaItem(MediaItem.fromUri("YOUR_AUDIO_URL_HERE"))
//        player.prepare()
//        player.play()
//
//        createNotification()
//
//        val mediaSession = player. createMediaSession()
//        notificationManager = PlayerNotificationManager.Builder(
//            this,
//            notificationId,
//            DEFAULT_CHANNEL_ID,
//            DescriptionAdapter(mediaSession)
//        ).build()
//
//        notificationManager.setPlayer(player)
//    }
//
//    private fun createNotification() {
//        val notification = NotificationCompat.Builder(this, DEFAULT_CHANNEL_ID)
//            .setContentTitle("Music Player")
//            .setSmallIcon(R.drawable.notification)
//            .build()
//
//        startForeground(notificationId, notification)
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        player.release()
//        notificationManager.setPlayer(null)
//    }


}