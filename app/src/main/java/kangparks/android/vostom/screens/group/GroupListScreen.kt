package kangparks.android.vostom.screens.group

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessAlarm
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kangparks.android.vostom.components.appbar.ContentAppBar
import kangparks.android.vostom.navigations.HomeContent

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GroupListScreen(navController: NavHostController) {

    val selectedTabIndex = remember {
        mutableStateOf(0)
    }
    val tabItems = listOf(
        TabItem("탐색", Icons.Outlined.AccountBalance),
        TabItem("내 그룹", Icons.Outlined.AccessAlarm),
    )
    val pagerState = rememberPagerState {
        tabItems.size
    }

    LaunchedEffect(key1 = selectedTabIndex.value){
        pagerState.animateScrollToPage(selectedTabIndex.value)
    }

    LaunchedEffect(key1 = pagerState.currentPage, pagerState.isScrollInProgress){
//        if(selectedTabIndex.value == pagerState.currentPage) return@LaunchedEffect
        if(!pagerState.isScrollInProgress){
            selectedTabIndex.value = pagerState.currentPage
        }
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
                sideButtonAction = {
                    navController.navigate(HomeContent.BuildGroup.route)
                },
                sideButtonContent = "그룹 만들기",
                contentTitle = "그룹",
            )

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                TabRow(selectedTabIndex = selectedTabIndex.value) {
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
                                    color = Color(0xFF000000)
                                )
                            },
//                            icon = {
//                                Icon(
//                                    imageVector = tabItem.icon,
//                                    contentDescription = null,
//                                    tint = Color(0xFF000000)
//                                )
//                            }
                        )
                    }
                }
            }
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) { idx ->
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ){
                    Text(text = "Page $idx")
                }

            }

        }
    }
}

data class TabItem(
    val title: String,
    val icon: ImageVector
)