package kangparks.android.vostom.screens.player

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kangparks.android.vostom.R
import kangparks.android.vostom.components.bottomsheet.PlayerCommentBottomSheet
import kangparks.android.vostom.components.player.ContentPlayerBackground
import kangparks.android.vostom.components.player.ContentPlayerController
import kangparks.android.vostom.components.player.ContentPlayerHideButton
import kangparks.android.vostom.components.player.ContentPlayerInfoSection
import kangparks.android.vostom.components.player.ContentPlayerSlider
import kangparks.android.vostom.viewModel.player.ContentPlayerViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedCrossfadeTargetStateParameter")
@Composable
fun TestScreenForMusicPlayer(
    navController: NavHostController,
    contentPlayerViewModel: ContentPlayerViewModel,
    contentColor: Color = Color(0xFFEBEBEB)
) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    var sliderIsChanging by remember { mutableStateOf(false) }
    var localSliderValue by remember { mutableFloatStateOf(0f) }

    val exoPlayer = contentPlayerViewModel.getPlayer()

    val currentSong = contentPlayerViewModel.currentSong.observeAsState(null)
    val isPaused = contentPlayerViewModel.isPaused.observeAsState(false)
    val bottomSheetScaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState()

    val isShowPlayer = contentPlayerViewModel.isShowPlayer.observeAsState(false)

    val isDarkTheme = isSystemInDarkTheme()
    val systemUiController = rememberSystemUiController()
    val keyboardController = LocalSoftwareKeyboardController.current

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = false
        )
    }

    AnimatedVisibility(
        visible = isShowPlayer.value == true,
        enter = slideInVertically(
            initialOffsetY = { it }
        ),
        exit = slideOutVertically(
            targetOffsetY = { it }
        )
    ) {
        Scaffold(
            bottomBar = {}
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
//                        detectTapGestures {
////                            keyboardController?.hide()
//                        }
                        detectDragGestures { change, dragAmount ->
                            val (x, y) = dragAmount
                            if (y > 50) {
                                systemUiController.setSystemBarsColor(
                                    color = Color.Transparent,
                                    darkIcons = !isDarkTheme
                                )
                                contentPlayerViewModel.hidePlayer()
                            }
                            else if(x > 50){
                                contentPlayerViewModel.nextMusic()
                            }
                            else if(x < -50){
                                contentPlayerViewModel.prevMusic()
                            }
                        }
                    },

                color = MaterialTheme.colorScheme.background
            ) {
                Box {
                    ContentPlayerBackground(currentSong = currentSong)

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .navigationBarsPadding()
                            .windowInsetsPadding(WindowInsets.statusBars)
                            .padding(20.dp)
                            .padding(bottom = 50.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ContentPlayerHideButton(
                            contentPlayerViewModel = contentPlayerViewModel,
                            systemUiController = systemUiController,
                            contentColor = contentColor,
                            isDarkTheme = isDarkTheme
                        )

                        ContentPlayerInfoSection(
                            currentSong = currentSong,
                            screenWidth = screenWidth,
                            contentColor = contentColor
                        )

                        ContentPlayerSlider(
                            contentColor = contentColor
                        )

                        ContentPlayerController(
                            contentPlayerViewModel = contentPlayerViewModel,
                            contentColor = contentColor
                        )
                    }
                    PlayerCommentBottomSheet(bottomSheetScaffoldState = bottomSheetScaffoldState)
                }
            }
        }
    }

}