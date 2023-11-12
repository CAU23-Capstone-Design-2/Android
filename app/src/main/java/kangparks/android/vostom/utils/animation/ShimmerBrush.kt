package kangparks.android.vostom.utils.animation

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun shimmerBrush(showShimmer: Boolean = true,targetValue:Float = 1000f): Brush {
    val isDarkTheme = isSystemInDarkTheme()

    return if (showShimmer) {
        val shimmerColors =
            if(isDarkTheme) listOf(
                Color.DarkGray.copy(alpha = 0.7f),
                Color.DarkGray.copy(alpha = 0.3f),
                Color.DarkGray.copy(alpha = 0.5f),
            )
            else listOf(
                Color.LightGray.copy(alpha = 0.7f),
                Color.LightGray.copy(alpha = 0.3f),
                Color.LightGray.copy(alpha = 0.5f),
            )

        val transition = rememberInfiniteTransition(label = "")
        val translateAnimation = transition.animateFloat(
            initialValue = 0f,
            targetValue = targetValue,
            animationSpec = infiniteRepeatable(
                animation = tween(1500), repeatMode = RepeatMode.Restart
            ), label = ""
        )
        Brush.linearGradient(
            colors = shimmerColors,
            start = Offset.Zero,
            end = Offset(x = translateAnimation.value, y = translateAnimation.value)
        )
    } else {
        Brush.linearGradient(
            colors = listOf(Color.Transparent, Color.Transparent),
            start = Offset.Zero,
            end = Offset.Zero
        )
    }
}