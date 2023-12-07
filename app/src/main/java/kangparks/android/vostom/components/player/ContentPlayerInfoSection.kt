package kangparks.android.vostom.components.player

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import kangparks.android.vostom.models.content.Music
import kangparks.android.vostom.utils.networks.content.likeMusic
import kangparks.android.vostom.utils.networks.content.undoLikeMusic
import kangparks.android.vostom.utils.store.getAccessToken
import kangparks.android.vostom.viewModel.player.ContentPlayerViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun ContentPlayerInfoSection(
    contentPlayerViewModel: ContentPlayerViewModel,
    screenWidth : Dp,
    contentColor : Color,
){
    val currentSong = contentPlayerViewModel.currentSong.observeAsState(initial = null)
//    val context = LocalContext.current
////    val likeCount = remember {
////        currentSong.value?.let { mutableIntStateOf(it.likeCount) }
////    }
////    val likedByUser = remember {
////        currentSong.value?.let { mutableStateOf(it.likedByUser) }
////    }
//
////    LaunchedEffect(key1 = currentSong.value){
////        if (likeCount != null) {
////            likeCount.value = currentSong.value?.likeCount ?: 0
////        }
////        if (likedByUser != null) {
////            likedByUser.value = currentSong.value?.likedByUser ?: false
////        }
////    }

    Column {
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = currentSong.value?.title ?: "",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.width(screenWidth - 110.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = contentColor
            )

            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .clickable {
                        currentSong.value?.setLikeState()
                        if (currentSong.value?.likedByUser == true) {
                            contentPlayerViewModel.setUnLikeMusic(currentSong.value!!.id)
                        } else {
                            contentPlayerViewModel.setLikeMusic(currentSong.value!!.id)
                        }
                    },
                verticalAlignment = Alignment.CenterVertically,

            ) {
                Spacer(modifier = Modifier.width(5.dp))
                if (currentSong.value?.likedByUser != null) {
                    Icon(
                        imageVector = if (currentSong.value?.likedByUser!!) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = null,
                        modifier = Modifier.size(22.dp),
                        tint = Color(0xFFFF6078),
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
                if (currentSong.value?.likeCount != null) {
                    Text(
                        text = currentSong.value?.likeCount.toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = contentColor,
                        textAlign = TextAlign.Center,
                        lineHeight = 22.sp,
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
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
                text = currentSong.value?.userName + "님의 커버" ?: "",
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = contentColor
            )
        }
//        Spacer(modifier = Modifier.height(10.dp))
    }
}