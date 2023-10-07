package kangparks.android.vostom.components.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun VoiceMeasureLine() {
    Canvas(modifier = Modifier.width(200.dp).height(60.dp)){
        drawLine(
            start = Offset(0f, 0f),
            end = Offset(0f, 60f),
            color = Color.Black,
            strokeWidth = 1f
        )
        drawLine(
            start = Offset(50f, 0f),
            end = Offset(50f, 60f),
            color = Color.Black,
            strokeWidth = 1f
        )
        drawLine(
            start = Offset(100f, 0f),
            end = Offset(100f, 60f),
            color = Color.Black,
            strokeWidth = 1f
        )
        drawLine(
            start = Offset(150f, 0f),
            end = Offset(150f, 60f),
            color = Color.Black,
            strokeWidth = 1f
        )
        drawLine(
            start = Offset(200f, 0f),
            end = Offset(200f, 60f),
            color = Color.Black,
            strokeWidth = 1f
        )

        drawRect(Color.Magenta, Offset(30f, 30f), Size(40f, 40f))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    VoiceMeasureLine()
}
