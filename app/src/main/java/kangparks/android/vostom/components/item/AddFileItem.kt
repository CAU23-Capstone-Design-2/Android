package kangparks.android.vostom.components.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kangparks.android.vostom.R

@Composable
fun AddFileItem(
    fileName : String
) {
    Row {
        Image(
            painter = painterResource(id = R.drawable.audio_file),
            contentDescription = "",
            modifier = Modifier
                .height(20.dp),
            contentScale = ContentScale.FillHeight
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = fileName,
            fontSize = 14.sp,
            color = Color(0xFF2E2E2E)
        )
    }
    Spacer(modifier = Modifier.height(5.dp))
}