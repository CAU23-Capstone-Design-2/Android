package kangparks.android.vostom.screens.content

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kangparks.android.vostom.components.appbar.ContentAppBar
import kangparks.android.vostom.components.item.CoverSongItem
import kangparks.android.vostom.components.item.UserCoverSongItem
import kangparks.android.vostom.components.player.BottomContentPlayer
import kangparks.android.vostom.viewModel.content.ContentStoreViewModel
import kangparks.android.vostom.viewModel.player.ContentPlayerViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailMyGroupCoverItemScreen(
    navController: NavHostController,
    contentStoreViewModel: ContentStoreViewModel,
    contentPlayerViewModel : ContentPlayerViewModel
) {
    val isPlaying = contentPlayerViewModel.isPlaying.observeAsState(initial = false)

    val myGroupCoverItemList = contentStoreViewModel.myGroupCoverItemList.observeAsState()

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp

    Scaffold(
//        contentWindowInsets =
    ){
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.statusBars)
                .navigationBarsPadding()
//                .padding(bottom = 40.dp)
        ){
            Box{
                Column(
                    modifier = Modifier
                        .fillMaxSize()
//            .windowInsetsPadding(WindowInsets.statusBars)
//            .padding(bottom = 48.dp)
                        .padding(horizontal = 20.dp)

                ) {
                    ContentAppBar(
                        backButtonAction = {
                            navController.popBackStack()
                        },
                        backButtonContent = "뒤로",
                    )
                    Text(
                        text = "나의 그룹 커버곡",
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
                        myGroupCoverItemList.value?.let {
                            List(it.size){index ->
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
                                            UserCoverSongItem(
                                                content = it[index],
                                                contentSize = (screenWidth-60)/2,
                                                onClick = {

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
                                            UserCoverSongItem(
                                                content = it[index],
                                                contentSize = (screenWidth-60)/2,
                                                onClick = {

                                                }
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                AnimatedVisibility(
                    visible = isPlaying.value,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    BottomContentPlayer(
                        contentPlayerViewModel = contentPlayerViewModel,
                        bottomPaddingValue = 20
                    )
                }
            }

        }
    }

}