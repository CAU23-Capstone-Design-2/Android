package kangparks.android.vostom.screens.learning

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kangparks.android.vostom.components.template.LearningLayoutTemplate
import kangparks.android.vostom.navigations.Content
import kangparks.android.vostom.viewModel.learning.LearningFirstSongViewModel

@Composable
fun LearningFirstSingScreen(navController : NavHostController){
    val viewModel = LearningFirstSongViewModel()
    val listOfSong = viewModel.listOfSong.observeAsState(listOf())
    val selectedSong = viewModel.selectedSong.observeAsState(null)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LearningLayoutTemplate(
            backButtonContent = "아전 단계로",
            backButtonAction = { navController.popBackStack()},
            mainContent = "아래 제공되는 동요 중 하나를 선택해 신나게 불러 봐요!",
            subContent = "선택한 곡 : "+(selectedSong?.value ?: "없음"),
            nextButtonContent = "동요 녹음하기",
            nextButtonAction = { navController.navigate(Content.LearningSecondSing.route) } // 임시 이동
        ){
            Column {
                listOfSong.value.forEach { item ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .selectable(
                                selected = (selectedSong.value == item),
                                onClick = { viewModel.selectSong(item) },
                                role = Role.RadioButton
                            )
                            .padding(vertical = 10.dp)
                    ) {
                        RadioButton(
                            selected = (selectedSong.value == item),
                            onClick = null
                        )
                        Spacer(Modifier.width(15.dp))
                        Text(
                            text = item,
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}