package kangparks.android.vostom.screens.learning

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import kangparks.android.vostom.components.template.LearningLayoutTemplate
import kangparks.android.vostom.navigations.Content
import kangparks.android.vostom.viewModel.learning.SingingViewModel

@Composable
fun LearningSingingScreen(
    navController : NavHostController,
    singingViewModel: SingingViewModel
){
    val songItem = singingViewModel.songItem.observeAsState(initial = null)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF2F2F2F)
    ){
        LearningLayoutTemplate(
            backButtonContent = "녹음 다시 하기",
            backButtonAction = {  },
            mainContent = "사용자의 노래를 녹음 중 입니다.",
            nextButtonContent = "임시 버튼",
            nextButtonAction = { navController.navigate(Content.FinishLearningSinging.route) },
            nextButtonContainerColor = Color(0xFFFC803B),
            color = Color.White
        ){
            songItem.value?.let {
                Text(
                    text = it.title ,
                    color = Color.White,
                    )
            }
        }
    }
}