package kangparks.android.vostom.screens.content

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import kangparks.android.vostom.navigations.HomeContent
import kangparks.android.vostom.viewModel.content.ContentStoreViewModel

@Composable
fun DetailMyCoverItemScreen(
    navController: NavHostController,
    contentStoreViewModel: ContentStoreViewModel
) {
    val myCoverItemList = contentStoreViewModel.myCoverItemList.observeAsState()

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(horizontal = 20.dp)
    ) {
        ContentAppBar(
            backButtonAction = {
                navController.popBackStack()
            },
            backButtonContent = "뒤로",
        )
        Text(
            text = "나의 커버곡",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(bottom = 48.dp)
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