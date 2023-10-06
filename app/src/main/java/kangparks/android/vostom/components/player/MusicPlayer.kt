package kangparks.android.vostom.components.player

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.StyledPlayerView
import kangparks.android.vostom.utils.builder.media.getMediaItem
import kangparks.android.vostom.viewModel.player.MusicPlayerViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MusicPlayer(
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current

    val mediaItem = getMediaItem(context, "example_sound", "raw")
    val exoPlayer = remember(context){
        SimpleExoPlayer.Builder(context).build().apply {
            setMediaItem(mediaItem)
            playWhenReady = false
            prepare()
            volume = 1.0f
            repeatMode = Player.REPEAT_MODE_OFF
        }
    }

    val viewModel = remember {
        MusicPlayerViewModel(exoPlayer)
    }

    val isPlaying = viewModel.isPlaying.observeAsState(initial = false)

    var lifecycle by remember {
        mutableStateOf(Lifecycle.Event.ON_CREATE)
    }

    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            lifecycle = event

            when (lifecycle) {
                Lifecycle.Event.ON_PAUSE -> {
                    viewModel.stopMusic()
                }
                Lifecycle.Event.ON_STOP -> {
                    viewModel.stopMusic()
                }
                else -> Unit
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            exoPlayer.release()
        }
    }

    Row(modifier = modifier.fillMaxSize()){
        Button(onClick = {
            if(isPlaying.value) viewModel.stopMusic()
            else viewModel.playMusic()}
        ) {
            Text(text = isPlaying.value.toString(), fontSize = 12.sp)

        }
    }
}