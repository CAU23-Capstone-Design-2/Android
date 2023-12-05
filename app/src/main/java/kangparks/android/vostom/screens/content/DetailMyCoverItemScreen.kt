package kangparks.android.vostom.screens.content

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kangparks.android.vostom.components.appbar.ContentAppBar
import kangparks.android.vostom.components.item.CoverSongItem
import kangparks.android.vostom.components.template.HomeContentLayoutTemplate
import kangparks.android.vostom.components.template.PullRefreshLayoutTemplate
import kangparks.android.vostom.navigations.HomeContent
import kangparks.android.vostom.viewModel.content.ContentStoreViewModel
import kangparks.android.vostom.viewModel.player.ContentPlayerViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailMyCoverItemScreen(
    navController: NavHostController,
    token: String,
    contentStoreViewModel: ContentStoreViewModel,
    contentPlayerViewModel : ContentPlayerViewModel
) {
    val context = LocalContext.current
    val isPlaying = contentPlayerViewModel.isPlaying.observeAsState(initial = false)
    val myCoverItemList = contentStoreViewModel.myCoverItemList.observeAsState()

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp

    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }

    fun refresh() = refreshScope.launch {
        refreshing = true
        delay(1000)
        contentStoreViewModel.updateHomeContent(
//            token!!,
            context = context
        )
        refreshing = false
    }

    val state = rememberPullRefreshState(refreshing, ::refresh)

    val isDarkTheme = isSystemInDarkTheme()
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = !isDarkTheme
        )
    }

    BackHandler(enabled = true) {
        if(contentPlayerViewModel.isShowPlayer.value == true){
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = !isDarkTheme
            )
            contentPlayerViewModel.hidePlayer()
            return@BackHandler
        }else{
            navController.popBackStack()
        }

    }

    PullRefreshLayoutTemplate(
        state = state,
        refreshing = refreshing,
        isDarkTheme = isDarkTheme
    ){
        HomeContentLayoutTemplate(
            contentPlayerViewModel = contentPlayerViewModel,
            navController = navController,
            surfaceBottomPadding = 0,
            playerBottomPadding = 20,
            surfaceModifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
            isPlaying = isPlaying
        ) {
            Column(
                modifier = Modifier
//            .fillMaxSize()
//            .windowInsetsPadding(WindowInsets.statusBars)
                    .padding(horizontal = 20.dp)
            ) {
                ContentAppBar(
                    backButtonAction = {
                        navController.popBackStack()
                    },
                    backButtonContent = "뒤로",
                    sideButtonContent = {
                        Text(
                            text = "커버곡 삭제",
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .clickable {
                                    navController.navigate(HomeContent.DeleteCoverSong.route)
                                }
                                .padding(5.dp)
                        )
                    }
                )
                Text(
                    text = "나의 커버곡",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(
                        bottom = if(isPlaying.value) 90.dp else 48.dp
                    )
                ){
                    myCoverItemList.value?.let {
                        List(it.size) { index ->
                            item {
                                if(index % 2 == 0){
                                    Box(
                                        modifier = Modifier
                                            .padding(
                                                top = 10.dp,
                                                bottom = 10.dp,
                                                end = 10.dp
                                            )
                                    ){
                                        CoverSongItem(
                                            content = it[index],
                                            contentSize = (screenWidth-60)/2,
                                            onClick = {
                                                contentPlayerViewModel.playMusic(it[index])
                                            }
                                        )
                                    }
                                }
                                else{
                                    Box(
                                        modifier = Modifier
                                            .padding(
                                                top = 10.dp,
                                                bottom = 10.dp,
                                                start = 10.dp
                                            )
                                    ){
                                        CoverSongItem(
                                            content = it[index],
                                            contentSize = (screenWidth-60)/2,
                                            onClick = {
                                                contentPlayerViewModel.playMusic(it[index])
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}