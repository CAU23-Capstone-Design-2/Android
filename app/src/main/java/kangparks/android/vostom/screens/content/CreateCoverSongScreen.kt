package kangparks.android.vostom.screens.content

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kangparks.android.vostom.components.appbar.ContentAppBar
import kangparks.android.vostom.components.blur.BlurForList
import kangparks.android.vostom.components.button.RoundedButton
import kangparks.android.vostom.components.item.YoutubeContentItem
import kangparks.android.vostom.components.searchbar.SearchBar
import kangparks.android.vostom.viewModel.content.CreateContentViewModel
import kangparks.android.vostom.viewModel.search.YoutubeSearchViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CreateCoverSongScreen(
    navController: NavHostController,
    token : String,
    createContentViewModel : CreateContentViewModel = viewModel()
) {
    val youtubeSearchViewModel = remember { YoutubeSearchViewModel() }

    val listOfSong = youtubeSearchViewModel.listOfSong.observeAsState(initial = listOf())
    val selectedId = youtubeSearchViewModel.selectedSong.observeAsState(initial = -1)
    val isSearching = youtubeSearchViewModel.isSearching.observeAsState(initial = false)
    val isNothingResult = youtubeSearchViewModel.isNothingResult.observeAsState(initial = false)

    val songItem = createContentViewModel.songItem.observeAsState(initial = null)

    val loadingAnimation by rememberLottieComposition(
        spec = LottieCompositionSpec.Asset("loadingForSearch.json")
    )
    val progress by animateLottieCompositionAsState(
        composition = loadingAnimation,
        iterations = LottieConstants.IterateForever,
    )

    val context = LocalContext.current
    val searchContent = remember{ mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    val isDarkTheme = isSystemInDarkTheme()
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = !isDarkTheme
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(horizontal = 20.dp)
            .padding(bottom = 48.dp)
    ) {
        Column {
            ContentAppBar(
                backButtonAction = {
                    navController.popBackStack()
                },
                backButtonContent = "취소",
            )
            Text(
                text = "커버 곡 생성",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(20.dp))
            SearchBar(
                value = searchContent.value,
                onValueChange = {
                    searchContent.value = it
                },
                placeholder = "가수와 노래 제목을 입력해 주세요.",
                onSearch = {
                    keyboardController?.hide()
                    youtubeSearchViewModel.searchSongWithKeyword(
                        keyword = searchContent.value,
                    )
                    createContentViewModel.setSongItem(null)
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
                                        youtubeSearchViewModel.selectSong(it.id)
                                        createContentViewModel.setSongItem(it)
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
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(
                    bottom = 58.dp,
                ),
            contentAlignment = Alignment.BottomCenter
        ){
            RoundedButton(
                text = "선택한 영상으로 AI 커버곡 생성하기",
                onClick = {
                    if(songItem.value == null){
                        Toast.makeText(context, "선택된 노래가 없습니다.", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        createContentViewModel.createCoverSong(token)
                        Toast.makeText(context, "선택한 노래로 AI 커버곡을 생성할게요.\n마이페이지를 확인해주세요.", Toast.LENGTH_LONG).show()
                        navController.popBackStack()
                    }
                }

            )
        }
    }
}