package kangparks.android.vostom.components.skeleton

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun OthersItemSkeleton() {
    Column(

        modifier = Modifier
            .width(120.dp)
    ) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape).background(Color(0xFFD1D1D1)),
        )
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier.width(120.dp).height(14.dp).background(Color(0xFFD1D1D1))
        )
    }
}