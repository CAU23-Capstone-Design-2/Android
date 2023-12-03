package kangparks.android.vostom.components.player

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import kangparks.android.vostom.R
import kangparks.android.vostom.viewModel.player.ContentPlayerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomContentPlayer(
    navController: NavHostController,
    bottomSheetScaffoldState : BottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
    contentPlayerViewModel: ContentPlayerViewModel,
    bottomPaddingValue : Int = 0
) {
    val currentSong = contentPlayerViewModel.currentSong.observeAsState(null)
    val isPaused = contentPlayerViewModel.isPaused.observeAsState(false)
    val isDarkTheme = isSystemInDarkTheme()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter

    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .padding(bottom = bottomPaddingValue.dp)
                .windowInsetsPadding(WindowInsets.statusBars)
                .height(70.dp)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
//                            change.consumeAllChanges()
                        val (x, y) = dragAmount
                        if (y < -30) {
                            contentPlayerViewModel.showPlayer()
                        }
                    }}
                .shadow(10.dp, RoundedCornerShape(15.dp))
                .clip(RoundedCornerShape(15.dp))
                .background(
//                    if (isDarkTheme) Color(0xFF292929) else Color(0xFFF9F9F9)
                    MaterialTheme.colorScheme.background
                )
                .clickable {
                           contentPlayerViewModel.showPlayer()
//                           navController.navigate(HomeContent.MusicPlayer.route)
                },
        ){
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start

            ){
                AsyncImage(
                    model = currentSong.value?.albumArtUri ?: null,
                    contentDescription = null,
                    modifier = Modifier
                        .size(48.dp)
                        .shadow(2.dp, RoundedCornerShape(5.dp))
                        .clip(RoundedCornerShape(5.dp))

                    ,
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(15.dp))
                Column(
                    modifier = Modifier.width(160.dp)
                ){
                    Text(
                        text = currentSong.value?.title ?: "",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = currentSong.value?.userName ?: "",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Row {
                    Crossfade(targetState = isPaused.value, label = "") { isPaused ->
                        when(isPaused){
                            true -> Icon(
                                painter = painterResource(id = R.drawable.teenyicons_play),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(30.dp)
                                    .clip(RoundedCornerShape(5.dp))
                                    .clickable { contentPlayerViewModel.resumeMusic() }
                            )
                            false -> Icon(
                                painter = painterResource(id = R.drawable.ion_pause),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(30.dp)
                                    .clip(RoundedCornerShape(5.dp))
                                    .clickable { contentPlayerViewModel.pauseMusic() }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .clickable { contentPlayerViewModel.stopMusic() }
                    )
                }
            }
        }
    }
}