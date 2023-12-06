package kangparks.android.vostom.components.player

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableFloatState
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
    val currentProgress = remember {
        mutableFloatStateOf(
            if(exoPlayer != null) exoPlayer.currentPosition.toFloat() else 0f
        )
    }

    if(exoPlayer != null){
//        UpdateProgress(
//            isPlaying = exoPlayer.isPlaying,
//            currentProgress = currentProgress,
//            totalDuration = exoPlayer.duration,
//            exoPlayer = exoPlayer
//        )

//        if (exoPlayer != null) {
//
//        }
        val interval = 1000L

        LaunchedEffect(key1 = exoPlayer, key2 = isPlaying) {
            if(isPlaying){
                while (currentProgress.value < exoPlayer.duration) {
                    delay(interval)
                    currentProgress.value += interval
                }
            }
        }

        Slider(
            value = currentProgress.value,
            onValueChange = {
                currentProgress.value = it
            },
            onValueChangeFinished = {
                contentPlayerViewModel.resumeMusic()
                exoPlayer?.seekTo((currentProgress.value).toLong())
            },
            enabled = true,
            valueRange = 0f..exoPlayer.duration.toFloat(),
            colors = SliderDefaults.colors(
                thumbColor = Color(0xFF894FA8),
                activeTrackColor = Color(0x8C894FA8)
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
            text = formatTime(currentProgress.value.toLong()),
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            textAlign = TextAlign.Start,
            color = contentColor
        )
        Text(
            text = formatTime(exoPlayer?.duration) ,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            textAlign = TextAlign.Start,
            color = contentColor
        )
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