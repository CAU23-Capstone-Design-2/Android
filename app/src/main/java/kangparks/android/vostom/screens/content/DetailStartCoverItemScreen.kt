package kangparks.android.vostom.screens.content

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kangparks.android.vostom.components.appbar.ContentAppBar
import kangparks.android.vostom.components.item.CoverSongItem
import kangparks.android.vostom.components.template.HomeContentLayoutTemplate
import kangparks.android.vostom.models.content.Music
import kangparks.android.vostom.viewModel.content.StarContentViewModel
import kangparks.android.vostom.viewModel.player.ContentPlayerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailStarCoverItemScreen(
    navController: NavHostController,
    startContentViewModel: StarContentViewModel,
    contentPlayerViewModel : ContentPlayerViewModel
//    startId : Int,
//    starName : String
) {
    val isPlaying = contentPlayerViewModel.isPlaying.observeAsState(initial = false)

    val currentSinger = startContentViewModel.currentSinger.observeAsState()
    val currentSingerContent = startContentViewModel.currentSingerContent.observeAsState()

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp

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
        }
        else{
            navController.popBackStack()
        }
    }

    HomeContentLayoutTemplate(
        contentPlayerViewModel = contentPlayerViewModel,
        navController = navController,
        surfaceBottomPadding = 0,
        playerBottomPadding = 20,
//        surfaceModifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
        isPlaying = isPlaying
    ) {
        AsyncImage(
            model = currentSinger.value?.imgUrl ?: "",
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop,
            alpha = 1f,
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.DarkGray,
                        )
                    ),
                    alpha = 1.0f
                )
            ,
        )
        Column {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(bottom = 30.dp),
                    contentAlignment = Alignment.BottomStart,
                ) {
                    Column(
                        modifier = Modifier
//                    .windowInsetsPadding(WindowInsets.statusBars)
                            .padding(horizontal = 20.dp)
                    ) {
                        Text(
                            text = "${currentSinger.value?.celebrityName}님의 커버곡",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(
                    start = 20.dp,
                    end = 20.dp,
                    top = 10.dp,
                    bottom = if(isPlaying.value) 90.dp else 48.dp
                )
            ) {
                currentSingerContent.value?.let {
                    List(it.size) { index ->
                        val coverItem = Music(
                            id = it[index].id,
                            title = it[index].title,
                            userName = currentSinger.value?.celebrityName ?: "",
                            albumArtUri = it[index].albumArtUri,
                            userId = currentSinger.value?.id ?: 0,
                            userImgUri = currentSinger.value?.imgUrl ?: "",
                            contentUri = "",
                            likeCount = 0,
                            likedByUser = false,
                        )
                        item {
                            if (index % 2 == 0) {
                                Box(
                                    modifier = Modifier
                                        .padding(
                                            top = 10.dp,
                                            bottom = 10.dp,
                                            end = 10.dp
                                        )
                                ) {
                                    CoverSongItem(
                                        content = coverItem,
                                        contentSize = (screenWidth - 60) / 2,
                                        onClick = {
                                            contentPlayerViewModel.playMusic(coverItem)
                                        }
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
                                    CoverSongItem(
                                        content = coverItem,
                                        contentSize = (screenWidth - 60) / 2,
                                        onClick = {
                                            contentPlayerViewModel.playMusic(coverItem)
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }

        }

        Column(
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.statusBars)
                .padding(horizontal = 20.dp)
        ) {
            ContentAppBar(
                backButtonAction = {
                    navController.popBackStack()
                },
                backButtonContent = "뒤로",
            )
//            Spacer(modifier = Modifier.height(200.dp))
//            Text(
//                text = "${currentSinger.value?.name}님의 커버곡",
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Bold
//            )
//            Spacer(modifier = Modifier.height(10.dp))
        }
    }

//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .navigationBarsPadding()
//    ) {
//
//
//        AnimatedVisibility(
//            visible = isPlaying.value,
//            enter = fadeIn(),
//            exit = fadeOut()
//        ) {
//            BottomContentPlayer(
//                navController = navController,
//                contentPlayerViewModel = contentPlayerViewModel,
//                bottomPaddingValue = 20
//            )
//        }
//    }
}