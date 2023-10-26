package kangparks.android.vostom.components.player

import android.media.browse.MediaBrowser
import android.util.Log
import android.util.SparseArray
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.google.accompanist.web.AccompanistWebChromeClient
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState
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
    val webViewClient = AccompanistWebViewClient()
    val webChromeClient = AccompanistWebChromeClient()

    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    val webViewState =
        rememberWebViewState(
            url = "youtube.com$contentId",
            additionalHttpHeaders = emptyMap()
        )
    Box(
        modifier = Modifier.fillMaxWidth()
            .height(600.dp),
        contentAlignment =  Alignment.TopCenter,
    ){

        WebView(
            state = webViewState,
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp),
            client = webViewClient,
            chromeClient = webChromeClient,
            onCreated = { webView ->
                with(webView) {
                    settings.run {
                        javaScriptEnabled = true
                        domStorageEnabled = true
                        javaScriptCanOpenWindowsAutomatically = false
                    }
                }
            }
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(color = Color(0xFF2F2F2F))
        ){

        }
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment =  Alignment.BottomCenter,
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((600.dp-(screenWidth/10*7))+10.dp)
                    .background(color = Color(0xFF2F2F2F))
            ){

            }
        }
    }


    Log.d("YoutubePlayer", "contentId : ${"https://www.youtube.com$contentId"}")
//    val context = LocalContext.current
//
//    val exoPlayer = remember(context) {
//        ExoPlayer.Builder(context).build()
//    }


    // youtube player 방식
//    AndroidView(factory = { context ->
//        YouTubePlayerView(context).apply {
////            lifecycleOwner = lifecycleOwner
//            addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
//                override fun onReady(youTubePlayer: YouTubePlayer) {
////                    youTubePlayer.
//                    youTubePlayer.loadVideo(contentId, 0f)
//                }
//            })
//        }
//    })


    // exoplayer with youtube extractor 방식
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