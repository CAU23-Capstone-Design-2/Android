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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import kangparks.android.vostom.models.content.Group

@Composable
fun GroupItem(
    content : Group,
    contentSize : Int = 140,
    onClick : () -> Unit = {}
){
    Column(
        modifier = Modifier
            .width(contentSize.dp)
            .clip(RoundedCornerShape(5.dp))
            .clickable(onClick = onClick)
    ) {
        AsyncImage(
            model = content?.groupImgUri ?: null,
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
                model = content?.groupLeaderImg?:null,
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = content?.groupLeader?: "",
                fontSize = 13.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "님 외 ${content?.groupMemberCount?.minus(1)}명 참여 중"?: "",
                fontSize = 13.sp,
                maxLines = 1,
            )

        }

    }
}