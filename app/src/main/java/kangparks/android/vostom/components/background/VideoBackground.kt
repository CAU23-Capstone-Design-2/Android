package kangparks.android.vostom.components.background

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout.RESIZE_MODE_ZOOM
import com.google.android.exoplayer2.ui.StyledPlayerView
import kangparks.android.vostom.viewModel.videobackground.VideoBackgroundViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun VideoBackground(modifier : Modifier = Modifier){
    val context = LocalContext.current
    val packageName = context.packageName
    val resId = context.resources.getIdentifier("login_background", "raw", context.packageName)
    val uriPath = "android.resource://$packageName/$resId"
    val mediaItem = MediaItem.Builder().setUri(Uri.parse(uriPath)).build()

    val exoPlayer = remember(context){
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(mediaItem)
            playWhenReady = true
            prepare()
            volume = 0f
            repeatMode = Player.REPEAT_MODE_ALL
        }
    }

    val viewModel = remember {
        VideoBackgroundViewModel(exoPlayer)
    }

    var lifecycle by remember {
        mutableStateOf(Lifecycle.Event.ON_CREATE)
    }

    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            lifecycle = event
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Box(modifier = modifier.fillMaxSize()){
        AndroidView(factory = { context ->
            StyledPlayerView(context).apply {
                player = exoPlayer
                useController = false
                resizeMode = RESIZE_MODE_ZOOM
            }
        },
            update = {
                when (lifecycle) {
                    Lifecycle.Event.ON_PAUSE -> {
                        it.onPause()
                        it.player?.pause()
                    }
                    Lifecycle.Event.ON_RESUME -> {
                        it.onResume()
                        it.player?.play()
                    }
                    else -> Unit
                }
            },
            onReset = {})
        Box (modifier = Modifier.matchParentSize().alpha(0.5f).background(Color.Black)){

        }
    }


}