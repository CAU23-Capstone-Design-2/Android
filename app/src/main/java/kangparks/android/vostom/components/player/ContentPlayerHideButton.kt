package kangparks.android.vostom.components.player

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.SystemUiController
import kangparks.android.vostom.R
import kangparks.android.vostom.viewModel.player.ContentPlayerViewModel

@Composable
fun ContentPlayerHideButton(
    contentPlayerViewModel: ContentPlayerViewModel,
    systemUiController: SystemUiController,
    contentColor : Color,
    isDarkTheme : Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            painter = painterResource(id = R.drawable.iconamoon_arrow_up),
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .clip(RoundedCornerShape(5.dp))
                .clickable {
                    systemUiController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = !isDarkTheme
                    )
                    contentPlayerViewModel.hidePlayer()
                },
            tint = contentColor
        )
    }
}