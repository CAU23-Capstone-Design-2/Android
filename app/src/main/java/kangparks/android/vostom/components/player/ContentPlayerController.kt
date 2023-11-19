package kangparks.android.vostom.components.player

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kangparks.android.vostom.R
import kangparks.android.vostom.viewModel.player.ContentPlayerViewModel

@SuppressLint("UnusedCrossfadeTargetStateParameter", "UnusedContentLambdaTargetStateParameter")
@Composable
fun ContentPlayerController(
    contentPlayerViewModel: ContentPlayerViewModel,
    contentColor : Color,
) {

    val isPaused = contentPlayerViewModel.isPaused.observeAsState(false)

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center

    ) {
        Row(
            modifier = Modifier.width(200.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painterResource(id = R.drawable.ion_play_back),
                contentDescription = null,
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .clickable {
                        contentPlayerViewModel.prevMusic()
                    }
                ,
                tint = contentColor
            )
            Crossfade(
                targetState = isPaused.value,
                animationSpec = tween(1000),
                label = "play/stop music"
            ) {
                when (isPaused.value) {
                    true -> {
                        Icon(
                            painterResource(id = R.drawable.teenyicons_play),
                            contentDescription = null,
                            modifier = Modifier
                                .size(42.dp)
                                .clip(RoundedCornerShape(5.dp))
                                .clickable {
                                    contentPlayerViewModel.resumeMusic()
                                },
                            tint = contentColor

                        )
                    }

                    false -> {
                        Icon(
                            painterResource(id = R.drawable.ion_pause),
                            contentDescription = null,
                            modifier = Modifier
                                .size(42.dp)
                                .clip(RoundedCornerShape(5.dp))
                                .clickable {
                                    contentPlayerViewModel.pauseMusic()
                                },
                            tint = contentColor
                        )
                    }
                }
            }
            Icon(
                painterResource(id = R.drawable.ion_play_next),
                contentDescription = null,
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .clickable {
                        contentPlayerViewModel.nextMusic()
                    }
                ,
                tint = contentColor
            )
        }
    }
}