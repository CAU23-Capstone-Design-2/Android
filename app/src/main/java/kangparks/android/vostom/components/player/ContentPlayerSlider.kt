package kangparks.android.vostom.components.player

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.exoplayer2.ExoPlayer
import kangparks.android.vostom.utils.helper.media.formatTime
import kangparks.android.vostom.viewModel.player.ContentPlayerViewModel
import kotlinx.coroutines.delay

@Composable
fun ContentPlayerSlider(
    contentPlayerViewModel: ContentPlayerViewModel,
    contentColor : Color,
    exoPlayer : ExoPlayer?,
    isPlaying : Boolean,
) {
    val currentSongDuration = contentPlayerViewModel.currentSongDuration.observeAsState(0L)
    val currentSongCurrentProgress = contentPlayerViewModel.currentSongCurrentProgress.observeAsState(0L)


//    val currentSongCurrentProgress = remember {
//        mutableFloatStateOf(
//            0f
//        )
//    }
    val currentProgress = remember {
        mutableFloatStateOf(
            currentSongCurrentProgress.value.toFloat()
        )
    }

//    val interval = 1000L

//    LaunchedEffect(key1 = currentSongDuration.value, key2 = isPlaying) {
//        val interval = 1000L
//        if(isPlaying && exoPlayer != null){
//
//            while (currentSongCurrentProgress.value < currentSongDuration.value) {
//                delay(interval)
//                currentSongCurrentProgress.value += interval
//            }
//        }
//    }

    if(exoPlayer != null){


        Slider(
            value = currentSongCurrentProgress.value.toFloat(),
            onValueChange = {
//                contentPlayerViewModel.setCurrentSongCurrentProgress(it.toLong())
//                currentSongCurrentProgress.value = it
                currentProgress.value = it
            },
            onValueChangeFinished = {
                if(!exoPlayer.isPlaying){
                    contentPlayerViewModel.resumeMusic()
                }

                exoPlayer?.seekTo(currentProgress.value.toLong())
            },
            enabled = true,
            valueRange = 0f..if(currentSongDuration.value > 0 )currentSongDuration.value.toFloat() else 0f,
            colors = SliderDefaults.colors(
                thumbColor = Color(0xFFE0E0E0),
                activeTrackColor = Color(0xF0D1D1D1),
                inactiveTrackColor = Color(0xA8979797),
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = formatTime(currentSongCurrentProgress.value.toLong()),
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            textAlign = TextAlign.Start,
            color = contentColor
        )
        if (exoPlayer != null) {
            Text(
                text = formatTime(if(currentSongDuration.value > 0 )currentSongDuration.value else 0f.toLong() ) ,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                textAlign = TextAlign.Start,
                color = contentColor
            )
        }
    }

    Spacer(modifier = Modifier.height(10.dp))
}

//@Composable
//private fun UpdateProgress(
//    isPlaying: Boolean,
//    currentProgress : MutableFloatState,
//    totalDuration: Long,
//    exoPlayer: ExoPlayer?
//) {
//
//
//}