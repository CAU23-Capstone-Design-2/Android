package kangparks.android.vostom.components.template

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kangparks.android.vostom.components.navigationBar.BottomNavigationBar
import kangparks.android.vostom.components.player.BottomContentPlayer
import kangparks.android.vostom.viewModel.player.ContentPlayerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeContentLayoutTemplate(
    contentPlayerViewModel : ContentPlayerViewModel,
    navController : NavHostController,
    surfaceModifier : Modifier = Modifier,
    surfaceBottomPadding : Int = 40,
    playerBottomPadding : Int = 30,
    isPlaying : State<Boolean>,
    content : @Composable () -> Unit,
) {

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ){
        Surface(
            modifier = surfaceModifier
                .fillMaxSize()
                .navigationBarsPadding()
                .padding(bottom = surfaceBottomPadding.dp),
            color = MaterialTheme.colorScheme.background
        ){
            Box {
                content()
                AnimatedVisibility(
                    visible = isPlaying.value,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    BottomContentPlayer(
                        navController = navController,
                        contentPlayerViewModel = contentPlayerViewModel,
                        bottomPaddingValue = playerBottomPadding,
                    )
                }
            }
        }
    }
}