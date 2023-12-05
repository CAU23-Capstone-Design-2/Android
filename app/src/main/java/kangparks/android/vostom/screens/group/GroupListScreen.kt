package kangparks.android.vostom.screens.group

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessAlarm
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kangparks.android.vostom.components.appbar.ContentAppBar
import kangparks.android.vostom.components.skeleton.CoverSongItemSkeleton
import kangparks.android.vostom.components.template.HomeContentLayoutTemplate
import kangparks.android.vostom.components.template.PullRefreshLayoutTemplate
import kangparks.android.vostom.navigations.HomeContent
import kangparks.android.vostom.viewModel.content.ContentStoreViewModel
import kangparks.android.vostom.viewModel.group.CurrentGroupViewModel
import kangparks.android.vostom.viewModel.player.ContentPlayerViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterialApi::class
)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GroupListScreen(
    navController: NavHostController,
    contentPlayerViewModel: ContentPlayerViewModel,
    contentStoreViewModel: ContentStoreViewModel,
    currentGroupViewModel: CurrentGroupViewModel,

    ) {
    val isPlaying = contentPlayerViewModel.isPlaying.observeAsState(initial = false)
    val allGroupList = contentStoreViewModel.allGroupList.observeAsState(initial = listOf())
    val myGroupList = contentStoreViewModel.myGroupList.observeAsState(initial = listOf())

    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp

    val selectedTabIndex = rememberSaveable() {
        mutableIntStateOf(0)
    }
    val tabItems = listOf(
        TabItem("탐색", Icons.Outlined.AccountBalance),
        TabItem("내 그룹", Icons.Outlined.AccessAlarm),
    )
    val pagerState = rememberPagerState {
        tabItems.size
    }

    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }

    fun refresh() = refreshScope.launch {
        refreshing = true
        delay(1000)
        contentStoreViewModel.updateGroupContent(
            context = context
        )
        refreshing = false
    }

    val state = rememberPullRefreshState(refreshing, ::refresh)

    LaunchedEffect(key1 = null) {
        contentStoreViewModel.initGroupContent(context)
    }

    LaunchedEffect(key1 = selectedTabIndex.value) {
        pagerState.animateScrollToPage(selectedTabIndex.value)
    }

    LaunchedEffect(key1 = pagerState.isScrollInProgress) {
        if (selectedTabIndex.value == pagerState.currentPage) return@LaunchedEffect
        if (!pagerState.isScrollInProgress) {
            selectedTabIndex.value = pagerState.currentPage
        }
    }

    val isDarkTheme = isSystemInDarkTheme()
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = !isDarkTheme
        )
    }

    BackHandler(enabled = true) {
        if (contentPlayerViewModel.isShowPlayer.value == true) {
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = !isDarkTheme
            )
            contentPlayerViewModel.hidePlayer()
            return@BackHandler
        } else {
            navController.popBackStack()
        }
    }

    PullRefreshLayoutTemplate(
        state = state,
        refreshing = refreshing,
        isDarkTheme = isDarkTheme
    ) {
        HomeContentLayoutTemplate(
            navController = navController,
            contentPlayerViewModel = contentPlayerViewModel,
            isPlaying = isPlaying,
            surfaceModifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
            surfaceBottomPadding = 40
        ) {
            Column {
                ContentAppBar(
                    sideButtonContent = {
                        Text(
                            text = "그룹 만들기",
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .clickable {
                                    navController.navigate(HomeContent.BuildGroup.route)
                                }
                                .padding(5.dp)
                        )
                    },
                    contentTitle = "그룹",
                    containerModifier = Modifier.padding(horizontal = 20.dp)
                )

                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TabRow(
                        selectedTabIndex = selectedTabIndex.value,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    ) {
                        tabItems.forEachIndexed { index, tabItem ->
                            Tab(
                                selected = selectedTabIndex.value == index,
                                onClick = {
                                    selectedTabIndex.value = index
//                                pagerState.animateScrollToPage(index)
                                },
                                text = {
                                    Text(
                                        text = tabItem.title,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                    )
                                },
                            )
                        }
                    }
                }
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalAlignment = Alignment.Top,
                    pageSpacing = 20.dp,
                    contentPadding = PaddingValues(horizontal = 20.dp),
                ) { idx ->
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column {
                            if (idx == 0) {
                                Crossfade(targetState = allGroupList.value, label = "") {
                                    when (it.isEmpty()) {
                                        true -> {
                                            LazyVerticalGrid(
                                                columns = GridCells.Fixed(2),
                                                contentPadding = PaddingValues(
                                                    bottom = if (isPlaying.value) 90.dp else 48.dp
                                                )
                                            ) {
                                                items(6) { idx ->
                                                    if (idx % 2 == 0) {
                                                        Box(
                                                            modifier = Modifier
                                                                .padding(
                                                                    top = 10.dp,
                                                                    bottom = 10.dp,
                                                                    end = 10.dp
                                                                )
                                                        ) {
                                                            CoverSongItemSkeleton(
                                                                skeletonSize = (screenWidth - 60) / 2,
                                                                titleBoxWidth = 100,
                                                                descriptionWidth = 140
                                                            )
                                                        }
                                                    } else {
                                                        Box(
                                                            modifier = Modifier
                                                                .padding(
                                                                    top = 10.dp,
                                                                    bottom = 10.dp,
                                                                    start = 10.dp
                                                                )
                                                        ) {
                                                            CoverSongItemSkeleton(
                                                                skeletonSize = (screenWidth - 60) / 2,
                                                                titleBoxWidth = 100,
                                                                descriptionWidth = 140
                                                            )
                                                        }

                                                    }
                                                }
                                            }
                                        }

                                        false -> {
                                            AllGroupListTabScreen(
                                                navController = navController,
                                                isPlaying = isPlaying,
                                                allGroupList = allGroupList.value,
                                                currentGroupViewModel = currentGroupViewModel,
                                                screenWidth = screenWidth
                                            )
                                        }
                                    }
                                }

                            } else if (idx == 1) {
                                Crossfade(targetState = myGroupList.value, label = "") { it ->
                                    when (it.isEmpty()) {
                                        true -> {
                                            LazyVerticalGrid(
                                                columns = GridCells.Fixed(2),
                                                contentPadding = PaddingValues(
                                                    bottom = if (isPlaying.value) 90.dp else 48.dp
                                                )
                                            ) {
                                                items(6) { idx ->
                                                    if (idx % 2 == 0) {
                                                        Box(
                                                            modifier = Modifier
                                                                .padding(
                                                                    top = 10.dp,
                                                                    bottom = 10.dp,
                                                                    end = 10.dp
                                                                )
                                                        ) {
                                                            CoverSongItemSkeleton(
                                                                skeletonSize = (screenWidth - 60) / 2,
                                                                titleBoxWidth = 100,
                                                                descriptionWidth = 140
                                                            )
                                                        }
                                                    } else {
                                                        Box(
                                                            modifier = Modifier
                                                                .padding(
                                                                    top = 10.dp,
                                                                    bottom = 10.dp,
                                                                    start = 10.dp
                                                                )
                                                        ) {
                                                            CoverSongItemSkeleton(
                                                                skeletonSize = (screenWidth - 60) / 2,
                                                                titleBoxWidth = 100,
                                                                descriptionWidth = 140
                                                            )
                                                        }

                                                    }
                                                }
                                            }
                                        }

                                        false -> {
                                            MyGroupListTabScreen(
                                                navController = navController,
                                                isPlaying = isPlaying,
                                                myGroupList = myGroupList.value,
                                                currentGroupViewModel = currentGroupViewModel,
                                                screenWidth = screenWidth
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
}

data class TabItem(
    val title: String,
    val icon: ImageVector
)