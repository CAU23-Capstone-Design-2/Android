package kangparks.android.vostom.components.skeleton

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kangparks.android.vostom.utils.animation.shimmerBrush

@Composable
fun CoverSongItemSkeleton(
    hasDetailUser : Boolean = false,
    skeletonSize : Int = 140,
    titleBoxWidth : Int = 100,
    descriptionWidth : Int = 80,
) {
    Column(
        modifier = Modifier
            .width(skeletonSize.dp)
    ) {
        Box(
            modifier = Modifier
                .size(skeletonSize.dp).clip(RoundedCornerShape(5.dp))
                .background(shimmerBrush(targetValue = 1200f))
        )
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .width(titleBoxWidth.dp).height(18.dp).clip(RoundedCornerShape(5.dp))
                .background(shimmerBrush(targetValue = 1500f))
        )
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .width(descriptionWidth.dp).height(15.dp).clip(RoundedCornerShape(5.dp))
                .background(shimmerBrush(targetValue = 1500f))
        )
        if (hasDetailUser){
            Spacer(modifier = Modifier.height(5.dp))
            Box(
                modifier = Modifier
                    .width(100.dp).height(20.dp) .clip(RoundedCornerShape(5.dp))
                    .background(shimmerBrush(targetValue = 1500f))
            )
        }
    }
}