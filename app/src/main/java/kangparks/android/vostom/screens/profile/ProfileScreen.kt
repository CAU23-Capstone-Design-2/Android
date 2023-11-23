package kangparks.android.vostom.screens.profile

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kangparks.android.vostom.components.appbar.ContentAppBar
import kangparks.android.vostom.components.template.HomeContentLayoutTemplate
import kangparks.android.vostom.navigations.HomeContent
import kangparks.android.vostom.viewModel.player.ContentPlayerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    navController: NavHostController,
    contentPlayerViewModel : ContentPlayerViewModel
) {

    val isPlaying = contentPlayerViewModel.isPlaying.observeAsState(initial = false)

    val isDarkTheme = isSystemInDarkTheme()
    val systemUiController = rememberSystemUiController()

    BackHandler(enabled = true) {
        if(contentPlayerViewModel.isShowPlayer.value == true){
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = !isDarkTheme
            )
            contentPlayerViewModel.hidePlayer()
            return@BackHandler
        }
        else{
            navController.popBackStack()
        }
    }

    HomeContentLayoutTemplate(
        contentPlayerViewModel = contentPlayerViewModel,
        surfaceModifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
        navController = navController,
        isPlaying = isPlaying
    ) {
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

//    Scaffold(
////        contentWindowInsets =
//    ){
//        Surface(
//            modifier = Modifier
//                .fillMaxSize()
//                .windowInsetsPadding(WindowInsets.statusBars)
//                .navigationBarsPadding()
//                .padding(bottom = 40.dp)
//        ){
//            Box{
//
//
//            }
//            AnimatedVisibility(
//                visible = isPlaying.value,
//                enter = fadeIn(),
//                exit = fadeOut()
//            ) {
//                BottomContentPlayer(
//                    navController = navController,
//                    contentPlayerViewModel = contentPlayerViewModel,
//                    bottomPaddingValue = 30
//                )
//            }
//        }
//
//    }

}