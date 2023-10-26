package kangparks.android.vostom.screens.learning

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout.RESIZE_MODE_ZOOM
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kangparks.android.vostom.components.player.YoutubePlayer
import kangparks.android.vostom.components.template.LearningLayoutTemplate
import kangparks.android.vostom.navigations.Content
import kangparks.android.vostom.viewModel.learning.SingingViewModel

@Composable
fun LearningSingingScreen(
    navController : NavHostController,
    singingViewModel: SingingViewModel
){
    val songItem = singingViewModel.songItem.observeAsState(initial = null)
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF2F2F2F)
    ){
        LearningLayoutTemplate(
            backButtonContent = "녹음 다시 하기",
            backButtonAction = {  },
            mainContent = "사용자의 노래를 녹음 중 입니다.",
            nextButtonContent = "임시 버튼",
            nextButtonAction = { navController.navigate(Content.FinishLearningSinging.route) },
            nextButtonContainerColor = Color(0xFFFC803B),
            color = Color.White
        ){
            songItem.value?.let {

                Text(
                    text = it.contentUri ,
                    color = Color.White,
                    )

                Box {
                    YoutubePlayer(
                        contentId = it.contentUri,
                        lifecycleOwner = lifecycleOwner
                    )
                }
            }
        }
    }
}