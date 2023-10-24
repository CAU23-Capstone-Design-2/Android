package kangparks.android.vostom.screens.learning

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import drawVerticalScrollbar
import kangparks.android.vostom.R
import kangparks.android.vostom.components.template.LearningLayoutTemplate
import kangparks.android.vostom.navigations.Content

@Composable
fun GuideScriptScreen(navController: NavHostController) {

    val scrollState = rememberScrollState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LearningLayoutTemplate(
            backButtonContent = "아전 단계로",
            backButtonAction = { navController.popBackStack() },
            mainContent = "다음 제공되는 스크립트를 따라 읽어보세요.",
            subContent = "예상 소요시간 : 3분",
            nextButtonContent = "스크립트 녹음하기",
            nextButtonAction = { navController.navigate(Content.CountDown.route) } // 임시 이동
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .drawVerticalScrollbar(scrollState).verticalScroll(scrollState)
            )
            {
                Text(
                    text = stringResource(id = R.string.learning_script),
                    fontSize = 16.sp,
                    lineHeight = 32.sp,
                    modifier = Modifier.padding(bottom = 170.dp)
                )
            }
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
                        .height(200.dp)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.White
                                )
                            ),
                            alpha = 1.0f
                        )
                ) {}
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                        .background(Color.White)
                ) {}

            }
        }
    }
}