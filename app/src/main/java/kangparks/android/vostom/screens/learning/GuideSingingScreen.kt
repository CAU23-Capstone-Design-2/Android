package kangparks.android.vostom.screens.learning

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kangparks.android.vostom.components.searchbar.SearchBar
import kangparks.android.vostom.components.template.LearningLayoutTemplate
import kangparks.android.vostom.navigations.Content
import kangparks.android.vostom.utils.helper.search.getSongList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun GuideSingingScreen(navController : NavHostController){
    val searchContent = remember{ mutableStateOf("")}
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val coroutineScope = rememberCoroutineScope()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LearningLayoutTemplate(
            backButtonContent = "아전 단계로",
            backButtonAction = { navController.popBackStack()},
            mainContent = "좋아하는 노래를 선택한 후 신나게 불러봐요!",
            nextButtonContent = "선택한 노래 녹음하기",
            nextButtonAction = { navController.navigate(Content.Loading.route) } // 임시 이동
        ){
            Column(

            ) {
                Spacer(modifier = Modifier.height(20.dp))
                SearchBar(
                    value = searchContent.value,
                    onValueChange = {
                        searchContent.value = it
                    },
                    placeholder = "가수와 노래 제목을 입력해 주세요.",
                    onSearch = {
                        keyboardController?.hide()
                        Toast.makeText(context, "검색어 : ${searchContent.value}", Toast.LENGTH_SHORT).show()

                        GlobalScope.launch(Dispatchers.IO) {
                            Log.d("Search Result :","스크래핑 시작 전")

                            getSongList(searchContent.value)
                        }
                    }
                )
            }

        }
    }
}