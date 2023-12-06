package kangparks.android.vostom.screens.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kangparks.android.vostom.components.appbar.ContentAppBar
import kangparks.android.vostom.components.blur.BlurForList
import kangparks.android.vostom.components.navigationBar.BottomNavigationBar
import kangparks.android.vostom.components.template.PullRefreshLayoutTemplate
import kangparks.android.vostom.navigations.HomeContent
import kangparks.android.vostom.viewModel.profile.RequestCoverSongViewModel
import kangparks.android.vostom.viewModel.profile.RequestCoverSongViewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun RequestCoverSongListScreen(
    navController: NavHostController,
    requestCoverSongViewModel: RequestCoverSongViewModel = viewModel(
        factory = RequestCoverSongViewModelFactory(applicationContext = LocalContext.current)
    )
) {
    val context = LocalContext.current
    val isDarkTheme = isSystemInDarkTheme()
    val systemUiController = rememberSystemUiController()
    val requestCoverSongList = requestCoverSongViewModel.requestCoverSongList.observeAsState(listOf())

    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }

    fun refresh() = refreshScope.launch {
        refreshing = true
        delay(1000)
        requestCoverSongViewModel.update(context)
        refreshing = false
    }

    val state = rememberPullRefreshState(refreshing, ::refresh)

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = !isDarkTheme
        )
    }

    PullRefreshLayoutTemplate(
        state = state,
        refreshing = refreshing,
        isDarkTheme = isDarkTheme
    ){
        Scaffold(
            bottomBar = { BottomNavigationBar(navController = navController) }
        ){
            Surface(
                modifier = Modifier
//                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.statusBars)
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 48.dp)
            ) {
                Column {
                    ContentAppBar(
                        backButtonAction = {
                            navController.popBackStack()
                        },
                        backButtonContent = "프로필",
                        sideButtonContent = {
                            Text(
                                text = "커버곡 생성",
                                textAlign = TextAlign.Center,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .clickable {
                                        navController.navigate(HomeContent.CreateCoverSong.route)
                                    }
                                    .padding(5.dp)
                            )
                        }
                    )
                    Text(
                        text = "요청 중인 커버곡",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "학습이 완료되면 나의 커버곡에서 확인할 수 있습니다.",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Box(){
                        LazyColumn(
                            contentPadding = PaddingValues(bottom = 170.dp)
                        ){
                            requestCoverSongList.value.forEach{
                                item {
                                    Row(
                                        modifier = Modifier
                                            .padding(bottom = 10.dp)
                                            .clip(RoundedCornerShape(5.dp))
                                            .fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically
                                    ){
                                        AsyncImage(
                                            model = it.albumArtUri,
                                            contentDescription = null,
                                            modifier = Modifier
                                                .size(60.dp)
                                                .clip(RoundedCornerShape(5.dp)),
                                            contentScale = ContentScale.Crop
                                        )
                                        Spacer(modifier = Modifier.width(10.dp))
                                        Text(
                                            text = it.title,
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Bold,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                }
                            }
                        }
                        BlurForList()
                    }
                }
            }
        }

    }

}