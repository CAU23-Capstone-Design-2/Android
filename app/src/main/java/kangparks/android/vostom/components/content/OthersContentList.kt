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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kangparks.android.vostom.components.item.CelebrityItemHorizontal
import kangparks.android.vostom.viewModel.bottomsheet.CelebrityContentViewModel
import kangparks.android.vostom.viewModel.bottomsheet.CelebrityContentViewType

@Composable
fun CelebrityContentList(
    celebrityContentViewModel : CelebrityContentViewModel
) {
    val celebrityList = celebrityContentViewModel.celebrityList.observeAsState(initial = listOf())
    val context = LocalContext.current

    Column {
        LazyColumn(
            state = rememberLazyListState(),
            contentPadding = PaddingValues(bottom = 48.dp)
        ){
            item {
                Text(
                    text = "연예인 노래 Cover 살펴보기",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 15.dp)
                )
            }
            items(celebrityList.value.size) { index ->
                CelebrityItemHorizontal(
                    content = celebrityList.value[index],
                    contentSize = 48,
                    onClick = {
                        celebrityContentViewModel.changeView(CelebrityContentViewType.CelebrityContentDetail)
                        celebrityContentViewModel.setAndUpdateCelebrityMusicList(
                            celebrity =  celebrityList.value[index],
                            context = context
                        )
                    }
                )
            }
        }
    }
}