package kangparks.android.vostom.components.content

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun MainContent(
    content : String,
    modifier : Modifier = Modifier,
    color : Color = MaterialTheme.colorScheme.onSurface
){
    Box {
        Text(
            text = content,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 32.sp,
            color = color,
        )
    }

}