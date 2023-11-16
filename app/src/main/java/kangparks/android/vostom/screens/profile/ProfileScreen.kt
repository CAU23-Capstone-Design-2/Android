package kangparks.android.vostom.screens.profile

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kangparks.android.vostom.R
import kangparks.android.vostom.components.appbar.ContentAppBar
import kangparks.android.vostom.components.player.BottomContentPlayer
import kangparks.android.vostom.navigations.HomeContent
import kangparks.android.vostom.viewModel.player.ContentPlayerViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    navController: NavHostController,
    contentPlayerViewModel : ContentPlayerViewModel
) {

    val isPlaying = contentPlayerViewModel.isPlaying.observeAsState(initial = false)

    Scaffold(
//        contentWindowInsets =
    ){
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.statusBars)
                .navigationBarsPadding()
                .padding(bottom = 40.dp)
        ){
            Box{
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(WindowInsets.statusBars)
                        .padding(horizontal = 20.dp)
                        .padding(bottom = 48.dp)
                ) {
                    ContentAppBar(
                        sideButtonAction = {
                            navController.navigate(HomeContent.RequestCoverSongList.route)
                        },
                        sideButtonContent = "편집",
                        contentTitle = "프로필",
                    )
                }
            }
            AnimatedVisibility(
                visible = isPlaying.value,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                BottomContentPlayer(
                    contentPlayerViewModel = contentPlayerViewModel,
                    bottomPaddingValue = 30
                )
            }
        }

    }

}