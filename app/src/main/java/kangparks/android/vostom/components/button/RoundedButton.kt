package kangparks.android.vostom.components.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RoundedButton(
    modifier: Modifier = Modifier,
    text : String,
    buttonContainerColor : Color = Color(0xFF8B62FF),
    onClick : () -> Unit = {}
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(50.dp).fillMaxWidth(),
        shape = RoundedCornerShape(15),
        colors = ButtonDefaults.textButtonColors(
            containerColor = buttonContainerColor,
            contentColor = Color(0xFFFFFFFF)
        ),
    ) {
        Text(text = text, fontSize = 14.sp, fontWeight = FontWeight.Bold)
    }
}