package kangparks.android.vostom.components.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import kangparks.android.vostom.models.content.Singer

@Composable
fun CoverItem(
    singer: Singer,
    containerModifier: Modifier = Modifier,
    size: Int = 48,
    onClick: (() -> Unit)? = null
    ){
    Row(
        modifier = containerModifier
            .padding(vertical = 8.dp)
            .clickable(
                enabled = (onClick != null),
                onClick = { onClick?.invoke() }
            ),
        verticalAlignment = Alignment.CenterVertically
    ){
        AsyncImage(
            model = singer.imageUri,
            contentDescription = null,
            modifier = Modifier.size(size.dp).clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(text = singer.description, fontSize = 15.sp, fontWeight = FontWeight.Bold )
    }
}