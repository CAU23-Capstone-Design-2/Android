package kangparks.android.vostom.screens.home

import android.annotation.SuppressLint
import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import drawVerticalScrollbar
import kangparks.android.vostom.R
import kangparks.android.vostom.components.appbar.ContentAppBar
import kangparks.android.vostom.components.item.CelebrityItem
import kangparks.android.vostom.components.item.CoverSongItem
import kangparks.android.vostom.components.item.UserCoverSongItem
import kangparks.android.vostom.components.section.HorizontalSongSection
import kangparks.android.vostom.components.skeleton.CoverSongItemSkeleton
import kangparks.android.vostom.components.skeleton.OthersItemSkeleton
import kangparks.android.vostom.components.template.HomeContentLayoutTemplate
import kangparks.android.vostom.components.template.PullRefreshLayoutTemplate
import kangparks.android.vostom.models.content.Celebrity
import kangparks.android.vostom.models.content.Music
import kangparks.android.vostom.navigations.HomeContent
import kangparks.android.vostom.utils.helper.media.getMediaSource
import kangparks.android.vostom.viewModel.content.ContentStoreViewModel
import kangparks.android.vostom.viewModel.content.StarContentViewModel
import kangparks.android.vostom.viewModel.player.ContentPlayerViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavHostController,
    contentStoreViewModel: ContentStoreViewModel,
    starContentViewModel: StarContentViewModel,
    contentPlayerViewModel: ContentPlayerViewModel
) {

    val testBuildString = remember { mutableStateOf("빌드 12-07-02-30") }

    val myCoverItemList = contentStoreViewModel.myCoverItemList.observeAsState(initial = listOf())
    val myGroupCoverItemList =
        contentStoreViewModel.myGroupCoverItemList.observeAsState(initial = listOf())
    val othersItemList = contentStoreViewModel.othersItemList.observeAsState(initial = listOf())
    val isPlaying = contentPlayerViewModel.isPlaying.observeAsState(initial = false)

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val isDarkTheme = isSystemInDarkTheme()
    val systemUiController = rememberSystemUiController()
    val scrollState = rememberScrollState()
    val doubleBackToExitPressedOnce = remember { mutableStateOf(false) }

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

    LaunchedEffect(key1 = null) {
        delay(500)
        contentStoreViewModel.initHomeContent(context)
    }

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = !isDarkTheme
        )
    }

    BackHandler(enabled = true) {
        if (contentPlayerViewModel.isShowPlayer.value == true) {
            contentPlayerViewModel.hidePlayer()
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = !isDarkTheme
            )
            return@BackHandler
        }

        if (doubleBackToExitPressedOnce.value) {
            (context as Activity).finish()
        } else {
            doubleBackToExitPressedOnce.value = true
            Toast.makeText(context, "'뒤로' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show()

            coroutineScope.launch {
                delay(2000)
                doubleBackToExitPressedOnce.value = false
            }
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
            isPlaying = isPlaying
        ) {
//            Text(
//                text = testBuildString.value,
//                fontSize = 10.sp,
//                modifier = Modifier
//                    .windowInsetsPadding(WindowInsets.statusBars)
//            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.statusBars)
                    .padding(bottom = 20.dp)
                    .drawVerticalScrollbar(scrollState)
                    .verticalScroll(scrollState),
            ) {
                ContentAppBar(
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
                    },
                    contentTitleImage = R.drawable.screen_title,
                    containerModifier = Modifier.padding(horizontal = 20.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                HorizontalSongSection(
                    title = "나의 커버곡",
                    contents = myCoverItemList.value as List<Music>,
                    sideButtonAction = {
                        if (myCoverItemList.value.isNotEmpty()) {
                            navController.navigate(HomeContent.DetailMyCoverItem.route)
                        }
                    },
                    renderItem = { item: Music, index : Int ->
                        CoverSongItem(
                            contentPlayerViewModel = contentPlayerViewModel,
                            content = item,
                            index = index,
                            playList = myCoverItemList,
                        )
                    },
                    skeletonItem = {
                        CoverSongItemSkeleton()
                    }
                )
                Spacer(modifier = Modifier.padding(vertical = 15.dp))
                HorizontalSongSection(
                    title = "나의 그룹 커버곡",
                    contents = myGroupCoverItemList.value as List<Music>,
                    sideButtonAction = {
                        if (myGroupCoverItemList.value.isNotEmpty()) {
                            navController.navigate(HomeContent.DetailMyGroupCoverItem.route)
                        }
                    },
                    renderItem = { item: Music, index : Int ->
                        UserCoverSongItem(
                            contentPlayerViewModel = contentPlayerViewModel,
                            content = item,
                            index = index,
                            playList = myGroupCoverItemList
                        )
                    },
                    skeletonItem = {
                        CoverSongItemSkeleton()
                    }
                )
                Spacer(modifier = Modifier.padding(vertical = 15.dp))
                HorizontalSongSection(
                    title = "연예인 AI 커버",
                    contents = othersItemList.value as List<Celebrity>,
                    sideButtonAction = {
                        if (othersItemList.value.isNotEmpty()) {
                            navController.navigate(HomeContent.DetailStarList.route)
                        }
                    },
                    contentPaddingValue = 10,
                    renderItem = { item: Celebrity, index : Int ->
                        CelebrityItem(
                            content = item,
                            onClick = {
                                starContentViewModel.updateCurrentSinger(
                                    context = context,
                                    singer = item
                                )
                                navController.navigate(HomeContent.DetailStarCoverItem.route)
                            }
                        )
                    },
                    skeletonItem = {
                        OthersItemSkeleton()
                    }
                )
                Spacer(
                    modifier = Modifier
                        .animateContentSize()
                        .padding(vertical = if (isPlaying.value) 50.dp else 15.dp)
                )
            }
        }
    }
}