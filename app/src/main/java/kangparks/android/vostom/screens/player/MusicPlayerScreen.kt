package kangparks.android.vostom.screens.player

import android.annotation.SuppressLint
import android.support.v4.media.MediaBrowserCompat
import android.widget.Space
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.ui.StyledPlayerControlView
import kangparks.android.vostom.R
import kangparks.android.vostom.components.bottomsheet.PlayerCommentBottomSheet
import kangparks.android.vostom.utils.helper.transform.BlurTransformation
import kangparks.android.vostom.viewModel.player.ContentPlayerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedCrossfadeTargetStateParameter")
@Composable
fun MusicPlayerScreen(
    navController: NavHostController,
    contentPlayerViewModel: ContentPlayerViewModel,
    contentColor : Color = Color(0xFFEBEBEB)
) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    val exoPlayer = contentPlayerViewModel.getPlayer()

    val currentSong = contentPlayerViewModel.currentSong.observeAsState(null)
    val isPaused = contentPlayerViewModel.isPaused.observeAsState(false)
    val  bottomSheetScaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState()


//    val isDarkTheme = isSystemInDarkTheme()
    val systemUiController = rememberSystemUiController()

    val request = ImageRequest.Builder(context)
        .data(currentSong.value?.albumArtUri ?: null)
        .transformations(
            listOf(
                BlurTransformation(
                    scale = 0.5f,
                    radius = 25
                )
            )
        )
        .build()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = false
        )
    }

    Scaffold(
        bottomBar = {}
    ){
        Surface(
//            modifier = Modifier
//                .fillMaxSize()
//                .navigationBarsPadding()
//                .windowInsetsPadding(WindowInsets.statusBars)
//                .padding(bottom = 30.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Box {
                AsyncImage(
                    model = request,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    alpha = 0.7f,
//                    transform = listOf(
//                        BlurTransformation(
//                            scale = 0.5f,
//                            radius = 25
//                        )
//                    )
                    // i want to add blur effect on image

                )
                Box (modifier = Modifier
                    .matchParentSize()
                    .alpha(0.5f)
                    .background(Color.Black)){

                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .navigationBarsPadding()
                        .windowInsetsPadding(WindowInsets.statusBars)

                        .padding(20.dp)
                        .padding(bottom = 50.dp),

//                        .fillMaxSize()
//                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 20.dp),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.iconamoon_arrow_up),
                            contentDescription = null,
                            modifier = Modifier
                                .size(30.dp)
                                .clip(RoundedCornerShape(5.dp))
                                .clickable {
                                    navController.popBackStack()

                                },
                            tint = contentColor
                        )
                    }
                    AsyncImage(
                        model = currentSong.value?.albumArtUri ?: null,
                        contentDescription = null,
                        modifier = Modifier
                            .size(screenWidth - 40.dp)
                            .shadow(2.dp, RoundedCornerShape(5.dp))
                            .clip(RoundedCornerShape(5.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Row() {
                        Text(
                            text = currentSong.value?.title ?: "",
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.fillMaxWidth(),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = contentColor
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = currentSong.value?.singer ?: "",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = contentColor
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row {
                        AsyncImage(
                            model = currentSong?.value?.userImgUri ?: "",
                            contentDescription = null,
                            modifier = Modifier
                                .size(20.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop,
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = currentSong.value?.user + "님의 커버" ?: "",
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.fillMaxWidth(),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = contentColor
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    // TODO 음악 프로그래스 바 구현하기
//                    AndroidView(factory = { context ->
//
//                        StyledPlayerControlView(context).apply {
//                            player = exoPlayer
//                        }
//                    }, modifier = Modifier
//                        .fillMaxWidth()
//                        .height(200.dp)
//                        .clip(RoundedCornerShape(5.dp))
//                        .background(Color(0x8C5C5C5C))
//                        .alpha(0.5f)
//                    )
                    LinearProgressIndicator(
                        progress = 0.5f,
                        trackColor = Color(0x8C5C5C5C),
                        color = Color(0x8CE2E2E2),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                            .height(5.dp)
                            .clip(RoundedCornerShape(5.dp))
//                            .alpha(0.5f)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "00:00",
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Start,
                            color = contentColor
                        )
                        Text(
                            text = "03:00",
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Start,
                            color = contentColor
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
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
                                    .clip(RoundedCornerShape(5.dp)),
                                tint = contentColor
                            )
                            Crossfade(targetState = isPaused.value, label = "") {
                                when (isPaused.value) {
                                    true -> {
                                        Icon(
                                            painterResource(id = R.drawable.teenyicons_play),
                                            contentDescription = null,
                                            modifier = Modifier
                                                .size(42.dp)
                                                .clip(RoundedCornerShape(5.dp))
                                                .clickable {
//                                                    contentPlayerViewModel.pauseMusic()
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
                                    .clip(RoundedCornerShape(5.dp)),
                                tint = contentColor
                            )
                        }
                    }
                }
                PlayerCommentBottomSheet(
                    bottomSheetScaffoldState = bottomSheetScaffoldState
                )
            }
        }
    }
}