package kangparks.android.vostom.services

import android.app.NotificationChannel.DEFAULT_CHANNEL_ID
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.core.app.NotificationCompat
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerNotificationManager
import kangparks.android.vostom.R
import kangparks.android.vostom.utils.helper.media.DescriptionAdapter

class MusicService : Service() {
    private val TAG = "MediaPlayerService"
    val context = this

    private lateinit var player: SimpleExoPlayer
    private lateinit var notificationManager: PlayerNotificationManager
    private val notificationId = 1

    private val playlist = mutableListOf<String>() // URLs of songs
    private var currentSongIndex = 0

    private val binder = LocalBinder()

    inner class LocalBinder : Binder() {
        fun getService(): MusicService = this@MusicService
    }
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
//
//class MusicService : Service() {
//
//    private lateinit var exoPlayer: ExoPlayer
//    private lateinit var mediaSession: MediaSessionCompat
//
//    override fun onCreate() {
//        super.onCreate()
//
//        exoPlayer = ExoPlayerFactory.newSimpleInstance(this)
//        mediaSession = MediaSessionCompat(this, "MusicService")
//        mediaSession.setActive(true)
//
//        mediaSession.setPlaybackState(
//            PlaybackStateCompat.Builder()
//            .setState(PlaybackStateCompat.STATE_PLAYING, 0, 1.0f)
//            .build())
//
//        mediaSession.setMetadata(
//            MediaMetadataCompat.Builder()
//            .putString(MediaMetadataCompat.METADATA_KEY_TITLE, "곡 제목")
//            .putString(MediaMetadataCompat.METADATA_KEY_ARTIST, "가수 이름")
//            .build())
//
//        startForeground(1, mediaSession.getNotification())
//    }
//
//    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        if (intent != null) {
//            val action = intent.action
//
//            if (action == ACTION_PLAY) {
//                exoPlayer.play()
//            } else if (action == ACTION_PAUSE) {
//                exoPlayer.pause()
//            }
//        }
//
//        return START_NOT_STICKY
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//
//        exoPlayer.release()
//        mediaSession.release()
//    }
//
//    companion object {
//        const val ACTION_PLAY = "ACTION_PLAY"
//        const val ACTION_PAUSE = "ACTION_PAUSE"
//    }
//}