package kangparks.android.vostom.screens.player

import android.annotation.SuppressLint
import android.widget.Space
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import kangparks.android.vostom.R
import kangparks.android.vostom.viewModel.player.ContentPlayerViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedCrossfadeTargetStateParameter")
@Composable
fun MusicPlayerScreen(
    navController: NavHostController,
    contentPlayerViewModel: ContentPlayerViewModel
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    val currentSong = contentPlayerViewModel.currentSong.observeAsState(null)
    val isPaused = contentPlayerViewModel.isPaused.observeAsState(false)

    val isDarkTheme = isSystemInDarkTheme()

    Scaffold {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .windowInsetsPadding(WindowInsets.statusBars)
                .padding(bottom = 30.dp),
            color = MaterialTheme.colorScheme.background
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ){
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.iconamoon_arrow_up),
                            contentDescription = null,
                            modifier = Modifier
                                .size(20.dp)
                                .clip(RoundedCornerShape(5.dp))
                        )
                    }
                }
                AsyncImage(
                    model = currentSong.value?.albumArtUri ?: null,
                    contentDescription = null,
                    modifier = Modifier
                        .size(screenWidth - 40.dp)
                        .clip(RoundedCornerShape(5.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(30.dp))
                Row(){
                    Text(
                        text = currentSong.value?.title ?: "",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = currentSong.value?.singer ?: "",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    AsyncImage(
                        model = currentSong?.value?.userImgUri ?:"",
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = currentSong.value?.user+"님의 커버" ?: "",
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                // TODO 음악 프로그래스 바 구현하기

                Spacer(modifier = Modifier.height(30.dp))
                Row {
                    Icon(
                        painterResource(id = R.drawable.ion_play_back) ,
                        contentDescription = null
                    )
                    Crossfade(targetState = isPaused.value, label = "") {
                        when(it){
                            true ->{
                                Icon(
                                    painterResource(id = R.drawable.ion_pause) ,
                                    contentDescription = null
                                )
                            }
                            false ->{
                                Icon(
                                    painterResource(id = R.drawable.teenyicons_play) ,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                    Icon(
                        painterResource(id = R.drawable.ion_play_next) ,
                        contentDescription = null
                    )
                }

            }
        }
    }
}