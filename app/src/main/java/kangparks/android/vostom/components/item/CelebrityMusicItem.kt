package kangparks.android.vostom.components.item

import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import kangparks.android.vostom.R
import kangparks.android.vostom.models.content.Music
import kangparks.android.vostom.viewModel.player.StarMusicPlayerViewModel

@Composable
fun CelebrityMusicItem(
    listOfCoverSongs : State<List<Music>>,
    index : Int,
//    music: Music,
    containerModifier: Modifier = Modifier,
    size: Int = 48,
    onClick: (() -> Unit)? = null,
    starMusicPlayerViewModel : StarMusicPlayerViewModel
) {
    val context = LocalContext.current
    val currentMusic = listOfCoverSongs.value[index]

    val defaultColor = MaterialTheme.colorScheme.onSurface

    val isPlaying = starMusicPlayerViewModel.isPlaying.observeAsState(initial = false)
    val isPaused = starMusicPlayerViewModel.isPaused.observeAsState(initial = false)
    val currentPlayIndex = starMusicPlayerViewModel.currentPlayIndex.observeAsState(-1)
    val currentPlaySong = starMusicPlayerViewModel.currentSong.observeAsState(null)

    Row(
        modifier = containerModifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .clickable(
                onClick = {
                    starMusicPlayerViewModel.setMediaSource(
                        contents = listOfCoverSongs.value,
                        context = context,
                        index = index
                    )
                }
            ),
        verticalAlignment = Alignment.CenterVertically
    ){
        Box {
            AsyncImage(
                model = currentMusic.albumArtUri,
                contentDescription = null,
                modifier = Modifier
                    .size(size.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )
            if(currentPlaySong.value != null){
                if((currentPlaySong.value!!.id == currentMusic.id)){
                    Box(
                        modifier = Modifier
                            .size(size.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0x8A5C5C5C))
                            .clickable {
                                if(!isPaused.value){
                                    starMusicPlayerViewModel.pause()
                                }else{
                                    starMusicPlayerViewModel.resume()
                                }
                            }
                        ,
                        contentAlignment = Alignment.Center
                    ){
                        if(!isPaused.value){
                            Icon(
                                painter = painterResource(id = R.drawable.ion_pause),
                                tint = Color.White,
                                contentDescription = null,
                            )
                        }else{
                            Icon(
                                painter = painterResource(id = R.drawable.teenyicons_play),
                                tint = Color.White,
                                contentDescription = null,
                            )
                        }
                    }
                }
            }

        }

        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = currentMusic.title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color =
                if(currentPlaySong.value != null){
                    if((currentPlaySong.value!!.id == currentMusic.id)){
                        Color(0xFF9258CA)
                    }else{
                        defaultColor
                    }
                }else{
                    defaultColor
                }

        )
//        if(currentPlaySong.value != null){
//            if((currentPlaySong.value!!.id == currentMusic.id)){
////                if(!isPaused.value){
////                    Text(
////                        text = "재생중",
////                        fontSize = 14.sp,
////                        fontWeight = FontWeight.Bold,
////                        maxLines = 1,
////                        overflow = TextOverflow.Ellipsis,
////                        modifier = Modifier.clickable {
////
////                        }
////                    )
////                }else if(isPaused.value){
////                    Text(
////                        text = "일시정지",
////                        fontSize = 14.sp,
////                        fontWeight = FontWeight.Bold,
////                        maxLines = 1,
////                        overflow = TextOverflow.Ellipsis,
////                        modifier = Modifier.clickable {
////                            starMusicPlayerViewModel.resume()
////                        }
////                    )
////                }
//            }
//        }

    }
}