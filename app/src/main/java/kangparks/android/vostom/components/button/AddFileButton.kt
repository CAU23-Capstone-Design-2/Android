package kangparks.android.vostom.components.button

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddFileButton(
    pickAudioFile: ManagedActivityResultLauncher<String, List<@JvmSuppressWildcards Uri>>
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFEEEEEE)
        ),
        onClick = {
            pickAudioFile.launch("audio/*")
        },
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            text = "여기를 눌러 파일을 선택해주세요!",
            fontSize = 14.sp,
            color = Color(0xFFAFAFAF),
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Left
        )
    }
}