package kangparks.android.vostom.components.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kangparks.android.vostom.components.item.CoverItem
import kangparks.android.vostom.viewModel.bottomsheet.OthersContentBottomSheetViewModel
import kangparks.android.vostom.viewModel.bottomsheet.OthersContentViewType
import kangparks.android.vostom.viewModel.content.OthersContentListViewModel

@Composable
fun OthersContentList(
    navController : NavHostController? = null,
    bottomSheetViewModel : OthersContentBottomSheetViewModel?,
    viewModel: OthersContentListViewModel = OthersContentListViewModel(),
) {
    val singerList = viewModel.singerList.observeAsState(initial = listOf())

    Column {

        LazyColumn(
            state = rememberLazyListState(),
            contentPadding = PaddingValues(bottom = 48.dp)
        ){
            item {
                Text(
                    text = "다른 사람의 노래 Cover 살펴보기",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 15.dp)
                )
            }
            items(singerList.value.size) { index ->
                CoverItem(
                    singer = singerList.value[index],
                    onClick = {
                        if(bottomSheetViewModel != null) {
                            bottomSheetViewModel?.changeView(OthersContentViewType.OthersContentDetail)
                            bottomSheetViewModel?.setSinger(singerList.value[index])
                        }
                    }
                )
            }
        }

//    singerList.value.forEach { singer ->
//        CoverItem(
//            singer = singer,
//            onClick = {
//                if(bottomSheetViewModel != null) {
//                    bottomSheetViewModel?.changeView(OthersContentViewType.OthersContentDetail)
//                    bottomSheetViewModel?.setSinger(singer)
//                }
//            }
//        )
//    }
    }
}