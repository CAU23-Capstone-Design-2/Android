package kangparks.android.vostom.components.item

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kangparks.android.vostom.models.item.YoutubePlayItem

@Composable
fun YoutubeContentItem(
    selectedId : Int,
    onChangeSelect : (YoutubePlayItem) -> Unit,
    song: YoutubePlayItem
) {
    Row(
        modifier = Modifier
            .padding(bottom = 10.dp)
            .selectable(
                selected = (selectedId == song.id),
                onClick = { onChangeSelect(song) },
                role = Role.RadioButton
            )
    ){
        Box(
            modifier = Modifier.height(80.dp),
            contentAlignment = Alignment.Center,
        ){
            RadioButton(
                selected = (selectedId == song.id),
                onClick = null
            )
        }
        Spacer(modifier = Modifier.width(5.dp))
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(song.thumbnailUri)
                .crossfade(true)
                .build(),
            contentDescription = "",
            modifier = Modifier
                .width(150.dp)
                .height(80.dp)
                .clip(shape = RoundedCornerShape(5.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(
                text = song.title,
                maxLines = 3,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}