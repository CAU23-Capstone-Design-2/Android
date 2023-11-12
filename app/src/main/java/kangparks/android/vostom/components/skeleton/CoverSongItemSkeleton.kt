package kangparks.android.vostom.components.skeleton

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CoverSongItemSkeleton(hasDetailUser : Boolean = false) {
    Column(
        modifier = Modifier
            .width(140.dp)
    ) {
        Box(
            modifier = Modifier
                .width(140.dp).height(140.dp).clip(RoundedCornerShape(5.dp))
                .background(Color(0xFFD1D1D1))
        )
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .width(100.dp).height(14.dp).clip(RoundedCornerShape(5.dp))
                .background(Color(0xFFD1D1D1))
        )
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .width(80.dp).height(13.dp).clip(RoundedCornerShape(5.dp))
                .background(Color(0xFFD1D1D1))
        )
        if (hasDetailUser){
            Spacer(modifier = Modifier.height(5.dp))
            Box(
                modifier = Modifier
                    .width(100.dp).height(12.dp) .clip(RoundedCornerShape(5.dp))
                    .background(Color(0xFFD1D1D1))
            )
        }
    }
}