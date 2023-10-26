package kangparks.android.vostom.components.player

import android.media.browse.MediaBrowser
import android.util.Log
import android.util.SparseArray
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.MergingMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout.RESIZE_MODE_ZOOM
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.util.Util
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun YoutubePlayer(
    contentId : String,
    lifecycleOwner: LifecycleOwner
){

    Log.d("YoutubePlayer", "contentId : ${"https://www.youtube.com/$contentId"}")
    val context = LocalContext.current

    val exoPlayer = remember(context) {
        ExoPlayer.Builder(context).build()
    }

    AndroidView(factory = { context ->
        YouTubePlayerView(context).apply {
//            lifecycleOwner = lifecycleOwner
            addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
//                    youTubePlayer.
                    youTubePlayer.loadVideo(contentId, 0f)
                }
            })
        }
    })



//    AndroidView(factory = { context ->
//        StyledPlayerView(context).apply {
//            player = exoPlayer
//            useController = false
//            resizeMode = RESIZE_MODE_ZOOM

//            object : YouTubeExtractor(context) {
//                override fun onExtractionComplete(ytFiles: SparseArray<YtFile>, vMeta: VideoMeta) {
//                    val itag = 22
//                    val videoPath = ytFiles[itag].url
//                    Log.e("fdgdfgdfgdfgdfgdfg", "downloadUrl : $ytFiles")
//                    val itag = 22
//                    val videoPath = ytFiles[itag].url
////            Log.e(TAG, "downloadUrl : $videoPath")
//
//                    // 경로를 가져온 후에 Exoplayer 설정 진행
//                    if (Util.SDK_INT >= 24) {
////                initializePlayer()
//                    }

//                    if(ytFiles != null){
//                        val videoTag = 137
//                        val audioTag = 140
//                        val audioSource = ProgressiveMediaSource.Factory(
//                            DefaultHttpDataSource.Factory()
//                        ).createMediaSource(MediaItem.fromUri(ytFiles[audioTag].url))
//                        val videoSource = ProgressiveMediaSource.Factory(
//                            DefaultHttpDataSource.Factory()
//                        ).createMediaSource(MediaItem.fromUri(ytFiles[videoTag].url))
//
//                        exoPlayer.setMediaSource(
//                            MergingMediaSource(true, videoSource, audioSource), true
//                        )
//
//                        exoPlayer.prepare()
//                        exoPlayer.playWhenReady = true
//                        exoPlayer.seekTo(0, 0)
//                    }
//                }
//            }.extract("https://www.youtube.com/watch?v=$contentId", false, true)
//        }
//    })
}