package kangparks.android.vostom.components.template

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PullRefreshLayoutTemplate(
    state : PullRefreshState,
    refreshing : Boolean,
    isDarkTheme : Boolean,
    content : @Composable () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .pullRefresh(state = state)
    ){
        Box(
            modifier = Modifier
                .animateContentSize()
                .background(
                    brush = if (isDarkTheme) Brush.verticalGradient(
                        colors = listOf(
                            Color(0x54575757),
                            MaterialTheme.colorScheme.background,
                        )
                    ) else Brush.verticalGradient(
                        colors = listOf(
                            Color(0x54575757),
                            MaterialTheme.colorScheme.background,
                        )
                    ),
                    alpha = 1.0f
                )
                .fillMaxWidth()
                .height(
                    if (refreshing) {
                        100.dp
                    } else {
                        lerp(0.dp, 100.dp, state.progress.coerceIn(0f..1f))
                    }
                )
        ) {
            if (refreshing) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.Center)
                        .padding(top = 20.dp),
                    strokeWidth = 3.dp,
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
        }
        content()
    }
}