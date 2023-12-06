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
import kotlinx.coroutines.delay

@Composable
fun ContentPlayerSlider(
    contentColor : Color,
    exoPlayer : ExoPlayer?,
) {
    val currentProgress = remember {
        mutableFloatStateOf(
            if(exoPlayer != null) exoPlayer.currentPosition.toFloat() else 0f
        )
    }

    if(exoPlayer != null){
        UpdateProgress(
            isPlaying = exoPlayer.isPlaying,
            currentProgress = currentProgress,
            totalDuration = exoPlayer.duration,
            exoPlayer = exoPlayer
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

@Composable
private fun UpdateProgress(
    isPlaying: Boolean,
    currentProgress : MutableFloatState,
    totalDuration: Long,
    exoPlayer: ExoPlayer?
) {

    if (exoPlayer != null) {
        // 3분 동안 1초 간격으로 타이머 실행
//        val totalDuration = 3 * 60 * 1000L // 3 minutes in milliseconds
        val interval = 1000L // 1 second interval

        // CoroutineScope를 사용하여 타이머 실행
        LaunchedEffect(exoPlayer) {
//            var elapsedTime = (currentProgress.value.toLong() * totalDuration).toLong()

            while (currentProgress.value < totalDuration) {
                delay(interval)
                currentProgress.value += interval
//                val newProgress = elapsedTime.toFloat() / totalDuration.toFloat()
//                currentProgress.value = newProgress
            }
        }

        Slider(
            value = currentProgress.value,
            onValueChange = {
                // Slider 값 변경 시 실행되는 코드
                Log.d("Slider", "onValueChange: $it")
                currentProgress.value = it
//                exoPlayer?.seekTo((currentProgress.value).toLong())
            },
            onValueChangeFinished = {
                // Slider 값 변경이 완료되었을 때 실행되는 코드
//                currentProgress.value = it
                exoPlayer?.seekTo((currentProgress.value).toLong())
            },
            enabled = true,
            valueRange = 0f..totalDuration.toFloat(),
            colors = SliderDefaults.colors(
                thumbColor = Color(0xFF894FA8),
                activeTrackColor = Color(0x8C894FA8)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
        )

//        LinearProgressIndicator(
//            progress = currentProgress.value/totalDuration.toFloat(),
//            trackColor = Color(0x8C5C5C5C),
//            color = Color(0x8CE2E2E2),
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 10.dp)
//                .height(5.dp)
//                .clip(RoundedCornerShape(5.dp))
//        )
    }
}