package kangparks.android.vostom.components.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import kangparks.android.vostom.models.content.Celebrity

@Composable
fun CelebrityItem(
    content: Celebrity? = null,
    contentSize: Int = 120,
    onClick: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .width(contentSize.dp)
            .clip(RoundedCornerShape(5.dp))
            .clickable(onClick = onClick)
    ) {
        AsyncImage(
            model = content?.imgUrl ?: null,
            contentDescription = null,
            modifier = Modifier
                .size(contentSize.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = content?.celebrityName ?: "",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(120.dp)
        )
    }
}

@Composable
fun CelebrityItemHorizontal(
    content: Celebrity,
    contentSize: Int,
    onClick: (() -> Unit)? = null,
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(5.dp))
            .clickable(
                enabled = onClick != null,
                onClick = onClick?: {}
            ),
        verticalAlignment = Alignment.CenterVertically
    ){
        AsyncImage(
            model = content.imgUrl,
            contentDescription = null,
            modifier = Modifier.size(contentSize.dp).clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(text = content.celebrityName, fontSize = 15.sp, fontWeight = FontWeight.Bold )
    }
}