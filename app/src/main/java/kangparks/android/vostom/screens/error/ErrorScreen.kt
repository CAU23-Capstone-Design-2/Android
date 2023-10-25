package kangparks.android.vostom.screens.error

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import kangparks.android.vostom.navigations.Nav
import kotlinx.coroutines.delay

@Composable
fun ErrorScreen(navController: NavHostController) {
    val countValue = remember{ mutableIntStateOf(5) }

    LaunchedEffect(null){
        while (countValue.value > 0){
            countValue.value -= 1
            delay(1000)
        }
        val curNav = if(true) Nav.CONTENT else Nav.AUTH // TODO(로그인 토큰 로직 구현 예정)

        navController.clearBackStack(curNav)
    }

    Surface {
        Box(
            contentAlignment = Alignment.Center
        ){
            Text(text = "잘못된 접근 입니다.")
            Text(text = "5초 후 첫 화면으로 이동합니다.")
        }
    }
}