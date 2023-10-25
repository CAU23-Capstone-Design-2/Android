package kangparks.android.vostom.screens.learning

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import kangparks.android.vostom.components.searchbar.SearchBar
import kangparks.android.vostom.components.template.LearningLayoutTemplate
import kangparks.android.vostom.navigations.Content
import kangparks.android.vostom.utils.helper.search.getSongList
import kangparks.android.vostom.viewModel.learning.GuideSingingViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun GuideSingingScreen(navController : NavHostController){
    val guideSingingViewModel : GuideSingingViewModel = remember { GuideSingingViewModel() }
    val searchContent = remember{ mutableStateOf("")}
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val coroutineScope = rememberCoroutineScope()

    val listOfSong = guideSingingViewModel.listOfSong.observeAsState(initial = listOf())

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
//                        Toast.makeText(context, "검색어 : ${searchContent.value}", Toast.LENGTH_SHORT).show()
                        guideSingingViewModel.searchSongWithKeyword(keyword = searchContent.value)
                    }
                )
                Spacer(modifier = Modifier.height(20.dp))

                if(listOfSong.value != null && listOfSong.value!!.isNotEmpty()) {
                    Box(){
                        LazyColumn(){
                            listOfSong.value!!.forEach { song ->
                                item {
//                                val painter = rememberAsyncImagePainter(
//                                    ImageRequest
//                                        .Builder(LocalContext.current)
//                                        .data(data = song.contentUri)
//                                        .build()
//                                )

                                    Row(
                                        modifier = Modifier.padding(bottom = 10.dp)
                                    ){
                                        AsyncImage(
                                            model = song.thumbnailUri,
                                            contentDescription = "",
                                            modifier = Modifier
                                                .width(150.dp)
                                                .height(100.dp)
                                                .clip(shape = RoundedCornerShape(5.dp)),
                                            contentScale = ContentScale.Crop
                                        )
                                        Spacer(modifier = Modifier.width(10.dp))
                                        Column {
                                            Text(text = song.title)
                                        }
                                    }
                                }


                            }
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

        }
    }
}