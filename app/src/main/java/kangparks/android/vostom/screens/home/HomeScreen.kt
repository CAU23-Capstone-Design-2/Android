package kangparks.android.vostom.screens.home

import android.annotation.SuppressLint
import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.exoplayer2.ExoPlayer
import drawVerticalScrollbar
import kangparks.android.vostom.R
import kangparks.android.vostom.components.appbar.ContentAppBar
import kangparks.android.vostom.components.item.CoverSongItem
import kangparks.android.vostom.components.item.OthersItem
import kangparks.android.vostom.components.item.UserCoverSongItem
import kangparks.android.vostom.components.section.HorizontalSongSection
import kangparks.android.vostom.components.skeleton.CoverSongItemSkeleton
import kangparks.android.vostom.components.skeleton.OthersItemSkeleton
import kangparks.android.vostom.components.template.HomeContentLayoutTemplate
import kangparks.android.vostom.models.content.CoverSong
import kangparks.android.vostom.models.content.Singer
import kangparks.android.vostom.navigations.HomeContent
import kangparks.android.vostom.utils.media.getMediaItem
import kangparks.android.vostom.viewModel.content.ContentStoreViewModel
import kangparks.android.vostom.viewModel.content.StarContentViewModel
import kangparks.android.vostom.viewModel.home.HomeViewModel
import kangparks.android.vostom.viewModel.home.HomeViewModelFactory
import kangparks.android.vostom.viewModel.player.ContentPlayerViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavHostController,
    token: String,
    homeViewModel: HomeViewModel = viewModel(
        factory = HomeViewModelFactory(token)
    ),
    contentStoreViewModel: ContentStoreViewModel,
    starContentViewModel: StarContentViewModel,
    contentPlayerViewModel : ContentPlayerViewModel
) {
    val myCoverItemList = homeViewModel.myCoverItemList.observeAsState(initial = listOf())
    val myGroupCoverItemList = homeViewModel.myGroupCoverItemList.observeAsState(initial = listOf())
    val othersItemList = homeViewModel.othersItemList.observeAsState(initial = listOf())
    val isPlaying = contentPlayerViewModel.isPlaying.observeAsState(initial = false)
    val isShowPlayer = contentPlayerViewModel.isShowPlayer.observeAsState(initial = false)

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val isDarkTheme = isSystemInDarkTheme()
    val systemUiController = rememberSystemUiController()
    val scrollState = rememberScrollState()
    val doubleBackToExitPressedOnce = remember { mutableStateOf(false) }

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

    HomeContentLayoutTemplate(
        contentPlayerViewModel = contentPlayerViewModel,
        navController = navController,
        isPlaying = isPlaying
    ){
        Text(
            text = "(빌드 11-20-02-00)",
            fontSize = 10.sp,
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.statusBars)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.statusBars)
                .padding(bottom = 20.dp)
                .drawVerticalScrollbar(scrollState)
                .verticalScroll(scrollState),
        ) {
            ContentAppBar(
                sideButtonAction = { navController.navigate(HomeContent.CreateCoverSong.route) },
                sideButtonContent = "커버곡 생성",
                contentTitleImage = R.drawable.screen_title,
                containerModifier = Modifier.padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            HorizontalSongSection(
                title = "나의 커버곡",
                contents = myCoverItemList.value as List<CoverSong>,
                sideButtonAction = {
                    if (myCoverItemList.value.isNotEmpty()) {
                        contentStoreViewModel.updateMyCoverItemList(myCoverItemList.value)
                        navController.navigate(HomeContent.DetailMyCoverItem.route)
                    }
                },
                renderItem = { item: CoverSong ->
                    CoverSongItem(
                        content = item,
                        onClick = {
                            val exoPlayer = contentPlayerViewModel.getPlayer()
                            if(exoPlayer == null){
                                val newPlayer = ExoPlayer.Builder(context).build().apply {
                                    setMediaItem(getMediaItem(context, "iu_all_your_moments", "raw"))
                                    playWhenReady = true
                                    prepare()
                                    volume = 1f
                                }
                                contentPlayerViewModel.setPlayer(newPlayer)
                            }else{
                                exoPlayer.replaceMediaItem(0, getMediaItem(context, "iu_all_your_moments", "raw"))
                            }
                            contentPlayerViewModel.playMusic(item)
//                            contentPlayerViewModel.showPlayer()
                        }
                    )
                },
                skeletonItem = {
                    CoverSongItemSkeleton()
                }
            )
            Spacer(modifier = Modifier.padding(vertical = 15.dp))
            HorizontalSongSection(
                title = "나의 그룹 커버곡",
                contents = myGroupCoverItemList.value as List<CoverSong>,
                sideButtonAction = {
                    if (myGroupCoverItemList.value.isNotEmpty()) {
                        contentStoreViewModel.updateMyGroupCoverItemList(
                            myGroupCoverItemList.value
                        )
                        navController.navigate(HomeContent.DetailMyGroupCoverItem.route)
                    }
                },
                renderItem = { item: CoverSong ->
                    UserCoverSongItem(
                        content = item,
                        onClick = {
                            val exoPlayer = contentPlayerViewModel.getPlayer()
                            if(exoPlayer == null){
                                val newPlayer = ExoPlayer.Builder(context).build().apply {
                                    setMediaItem(getMediaItem(context, "rose_eleven", "raw"))
                                    playWhenReady = true
                                    prepare()
                                    volume = 1f
                                }
                                contentPlayerViewModel.setPlayer(newPlayer)
                            }else{
                                exoPlayer.replaceMediaItem(0, getMediaItem(context, "rose_eleven", "raw"))
                            }
                            contentPlayerViewModel.playMusic(item)
//                            contentPlayerViewModel.showPlayer()
                        }
                    )
                },
                skeletonItem = {
                    CoverSongItemSkeleton(true)
                }
            )
            Spacer(modifier = Modifier.padding(vertical = 15.dp))
            HorizontalSongSection(
                title = "연예인 AI 커버",
                contents = othersItemList.value as List<Singer>,
                sideButtonAction = {
                    if (othersItemList.value.isNotEmpty()) {
                        contentStoreViewModel.updateOthersItemList(othersItemList.value)
                        navController.navigate(HomeContent.DetailStarList.route)
                    }
                },
                contentPaddingValue = 10,
                renderItem = { item: Singer ->
                    OthersItem(
                        content = item,
                        onClick = {
                            starContentViewModel.updateCurrentSinger(
                                accessToken = token,
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
            Spacer(modifier = Modifier.padding(vertical = if(isPlaying.value) 50.dp else 15.dp))
        }
    }

//    Scaffold(
////        contentWindowInsets =
//    ) {
//        Surface(
//            modifier = Modifier
//                .fillMaxSize()
//                .navigationBarsPadding()
//                .padding(bottom = 40.dp),
//            color = MaterialTheme.colorScheme.background
//        ) {
//            Box {
//
//                AnimatedVisibility(
//                    visible = isPlaying.value,
//                    enter = fadeIn(),
//                    exit = fadeOut()
//                ) {
//                    BottomContentPlayer(
//                        contentPlayerViewModel = contentPlayerViewModel,
//                        bottomPaddingValue = 30
//                    )
//                }
//            }
//
//
//        }
//    }

}