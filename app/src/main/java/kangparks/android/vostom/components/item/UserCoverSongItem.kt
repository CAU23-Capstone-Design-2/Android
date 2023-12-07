package kangparks.android.vostom.components.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import kangparks.android.vostom.models.content.Music
import kangparks.android.vostom.utils.helper.media.getMediaSource
import kangparks.android.vostom.viewModel.player.ContentPlayerViewModel

@Composable
fun UserCoverSongItem(
    contentPlayerViewModel : ContentPlayerViewModel,
    content: Music? = null,
    contentSize : Int = 140,
    index : Int,
    playList : State<List<Music>>
//    onClick: () -> Unit = {},
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .width(contentSize.dp)
            .clip(RoundedCornerShape(5.dp))
            .clickable(onClick = {
                if(content != null){
//                    val mediaSource = getMediaSource(
//                        context = context,
//                        musicId = content.id
//                    )
                    contentPlayerViewModel.setMediaSource(
                        context = context,
//                        mediaSource = mediaSource,
                        index = index,
                        playList = playList.value
                    )
                    contentPlayerViewModel.playMusic(content)

                }
            })
    ) {
        AsyncImage(
            model = content?.albumArtUri?:null,
            contentDescription = null,
            modifier = Modifier
                .size(contentSize.dp)
                .clip(RoundedCornerShape(5.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = content?.title ?: "",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(5.dp))
        Row {
            AsyncImage(
                model = content?.userImgUri?:"",
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = content?.userName + "님의 커버" ?: "",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}