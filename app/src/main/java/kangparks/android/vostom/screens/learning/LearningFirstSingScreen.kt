package kangparks.android.vostom.screens.learning

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kangparks.android.vostom.components.template.LearningLayoutTemplate
import kangparks.android.vostom.navigations.Content

@Composable
fun LearningFirstSingScreen(navController : NavHostController){

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LearningLayoutTemplate(
            hasBackButton = true,
            backButtonContent = "이전 단계로",
            backButtonAction = { navController.popBackStack()},
            hasMainContent = true,
            mainContent = "아래 제공되는 동요 중 하나를 선택해 신나게 불러 봐요!",
            hasNextButton = true,
            nextButtonContent = "동요 녹음하기",
            nextButtonAction = { navController.navigate(Content.LearningSecondSing.route) } // 임시 이동
        ){
            Column (modifier = Modifier
                .height(500.dp)
                .width(500.dp))
            {
                Preview_MultipleRadioButtons()
            }
        }
    }
}

@Composable
fun Preview_MultipleRadioButtons() {
    val selectedValue = remember { mutableStateOf("") }

    val isSelectedItem: (String) -> Boolean = { selectedValue.value == it }
    val onChangeState: (String) -> Unit = { selectedValue.value = it }

    val items = listOf("곰 세 마리", "작은 별", "산토끼", "퐁당퐁당", "나비야")
    Column(Modifier.padding(8.dp)) {
        Text(text = "선택한 곡: ${selectedValue.value.ifEmpty { "NONE" }}")
        Spacer(Modifier.height(15.dp))
        items.forEach { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.selectable(
                    selected = isSelectedItem(item),
                    onClick = { onChangeState(item) },
                    role = Role.RadioButton
                ).padding(vertical = 8.dp)
            ) {
                RadioButton(
                    selected = isSelectedItem(item),
                    onClick = null
                )
                Spacer(Modifier.width(15.dp))
                Text(
                    text = item,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

