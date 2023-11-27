package kangparks.android.vostom.components.player

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ContentPlayerSlider(
    contentColor : Color,
) {
    LinearProgressIndicator(
        progress = 0.5f,
        trackColor = Color(0x8C5C5C5C),
        color = Color(0x8CE2E2E2),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .height(5.dp)
            .clip(RoundedCornerShape(5.dp))
    )
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "00:00",
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            textAlign = TextAlign.Start,
            color = contentColor
        )
        Text(
            text = "03:00",
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            textAlign = TextAlign.Start,
            color = contentColor
        )
    }

    Spacer(modifier = Modifier.height(20.dp))
}