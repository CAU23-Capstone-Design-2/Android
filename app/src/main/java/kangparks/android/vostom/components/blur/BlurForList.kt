package kangparks.android.vostom.components.blur

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BlurForList(
    blurHeight : Int = 200,
    blurBottomPadding : Int = 10,
    blurColor : Color = Color.White
) {
    val isDarkTheme = isSystemInDarkTheme()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(blurHeight.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            if(isDarkTheme) MaterialTheme.colorScheme.background else Color.White
                        )
                    ),
                    alpha = 1.0f
                )
        ) {}
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(blurBottomPadding.dp)
                .background(if(isDarkTheme) MaterialTheme.colorScheme.background else Color.White)
        ) {}

    }
}