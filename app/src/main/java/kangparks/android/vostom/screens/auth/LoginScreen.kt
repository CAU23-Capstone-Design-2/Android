package kangparks.android.vostom.screens.auth

import android.R
import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout.RESIZE_MODE_ZOOM
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.common.reflect.Reflection.getPackageName
import kangparks.android.vostom.components.background.VideoBackground


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(){

    val context = LocalContext.current

    val packageName = context.packageName
    val resId = context.resources.getIdentifier("login_background", "raw", context.packageName)
//    Log.d("LoginScreen",uri.toString())
    val uriPath = "android.resource://$packageName/$resId"

//    val uriPath = "www.youtube.com/watch?v=2Vv-BfVoq4g"
//    val exoPlayer = remember {
//        context.buildExoPlayer(Uri.parse(uriPath))
//    }

//    DisposableEffect(){
//        onDispose {
//            exoPlayer.release()
//        }
//    }

//    val systemUiController = rememberSystemUiController()
//    val useDarkIcons = !isSystemInDarkTheme()
//    if(darkTheme){
//        systemUiController.setSystemBarsColor(
//            color = Color.Transparent
//        )
//    }else{
//        systemUiController.setSystemBarsColor(
//            color = Color.White
//        )
//    }



    SideEffect {
//        systemUiController.setSystemBarsColor(
//            color = Color.Transparent
//        )
//        systemUiController.setSystemBarsColor(
//            color = Color.Transparent,
//            darkIcons = useDarkIcons,
//            isNavigationBarContrastEnforced = false
//        )
    }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
    )
    {
        Box {
//            AndroidView(
//                factory = { it.buildPlayerView(exoPlayer) },
//                modifier = Modifier.fillMaxWidth()
//            )
            VideoBackground()
            Column(
                modifier = Modifier
//                    .padding(20.dp)
                    .fillMaxWidth()
                    .windowInsetsPadding(WindowInsets.statusBars)
                ,
                horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center,
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Vostom 시작하기")
                }
            }
        }

    }
}

//private fun Context.buildExoPlayer(uri: Uri):ExoPlayer {
//    return ExoPlayer.Builder(this).build().apply {
//        setMediaItem(MediaItem.fromUri(uri))
//        repeatMode = Player.REPEAT_MODE_ALL
//        playWhenReady = true
//        prepare()
//    }
//}

//private fun Context.buildPlayerView(exoPlayer: ExoPlayer): StyledPlayerView{
//    return StyledPlayerView(this).apply {
//        player = exoPlayer
//        layoutParams =
//            FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
//        useController = false
//        resizeMode = RESIZE_MODE_ZOOM
//    }
//}

//private fun getVideoUri(): Uri {
//
//
//    val rawId = resources.getIdentifier("login_background", "raw", packageName)
//    return
//}