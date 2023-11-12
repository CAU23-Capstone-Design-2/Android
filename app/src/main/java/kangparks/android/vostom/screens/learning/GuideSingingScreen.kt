package kangparks.android.vostom.screens.learning

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import kangparks.android.vostom.components.blur.BlurForList
import kangparks.android.vostom.components.item.YoutubeContentItem
import kangparks.android.vostom.components.searchbar.SearchBar
import kangparks.android.vostom.components.template.LearningLayoutTemplate
import kangparks.android.vostom.navigations.LearningContent
import kangparks.android.vostom.viewModel.learning.GuideSingingViewModel
import kangparks.android.vostom.viewModel.learning.SingingViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun GuideSingingScreen(
    navController : NavHostController,
    singingViewModel: SingingViewModel
){
    val context = LocalContext.current

    val guideSingingViewModel : GuideSingingViewModel = remember { GuideSingingViewModel() }
    val listOfSong = guideSingingViewModel.listOfSong.observeAsState(initial = listOf())
    val selectedId = guideSingingViewModel.selectedSong.observeAsState(initial = -1)
    val isSearching = guideSingingViewModel.isSearching.observeAsState(initial = false)
    val isNothingResult = guideSingingViewModel.isNothingResult.observeAsState(initial = false)

    val songItem = singingViewModel.songItem.observeAsState(initial = null)

    val loadingAnimation by rememberLottieComposition(
        spec = LottieCompositionSpec.Asset("loadingForSearch.json")
    )
    val progress by animateLottieCompositionAsState(
        composition = loadingAnimation,
        iterations = LottieConstants.IterateForever,
    )

    val searchContent = remember{ mutableStateOf("")}
    val keyboardController = LocalSoftwareKeyboardController.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LearningLayoutTemplate(
            backButtonContent = "아전 단계로",
            backButtonAction = { navController.popBackStack()},
            mainContent = "좋아하는 노래를 선택한 후 신나게 불러봐요!",
            nextButtonContent = "선택한 노래 녹음하기",
            nextButtonAction = {
                if(songItem.value == null){
                    Toast.makeText(context, "선택된 노래가 없습니다.", Toast.LENGTH_SHORT).show()
                }else{
                    navController.navigate(LearningContent.CountDown.route+"/${LearningContent.LearningSinging.route}")
                }
            }
        ){
            Column{
                Spacer(modifier = Modifier.height(10.dp))
                SearchBar(
                    value = searchContent.value,
                    onValueChange = {
                        searchContent.value = it
                    },
                    placeholder = "가수와 노래 제목을 입력해 주세요.",
                    onSearch = {
                        keyboardController?.hide()
                        guideSingingViewModel.searchSongWithKeyword(keyword = searchContent.value)
                    }
                )
                Spacer(modifier = Modifier.height(20.dp))

                AnimatedVisibility(!isSearching.value && listOfSong.value != null && listOfSong.value!!.isNotEmpty()) {
                    Box(){
                        LazyColumn(
                            contentPadding = PaddingValues(bottom = 170.dp)
                        ){
                            listOfSong.value!!.forEach { song ->
                                item {
                                    YoutubeContentItem(
                                        selectedId = selectedId.value,
                                        onChangeSelect = {
                                            guideSingingViewModel.selectSong(it.id)
                                            singingViewModel.setSongItem(it)
                                        },
                                        song = song,
                                    )
                                }
                            }
                        }
                        BlurForList()
                    }
                }
                AnimatedVisibility(visible = isSearching.value) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .height(250.dp)
                                .width(250.dp),
                            contentAlignment = Alignment.TopCenter
                        ) {
                            LottieAnimation(
                                composition = loadingAnimation,
                                progress = { progress },
                                contentScale = ContentScale.FillHeight,
                            )
                        }
                    }
                }
                AnimatedVisibility(visible = isNothingResult.value) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .height(250.dp)
                                .width(250.dp),
                            contentAlignment = Alignment.TopCenter
                        ) {
                            Text(
                                text = "검색 결과가 없습니다.\uD83D\uDE25",
                                color = Color.Black,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}