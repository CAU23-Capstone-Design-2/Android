package kangparks.android.vostom.components.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import kangparks.android.vostom.viewModel.bottomsheet.CelebrityContentViewModel
import kangparks.android.vostom.viewModel.bottomsheet.OthersContentViewType

@Composable
fun OthersContentList(
    navController : NavHostController? = null,
    celebrityContentViewModel : CelebrityContentViewModel
) {
    val singerList = celebrityContentViewModel.singerList.observeAsState(initial = listOf())

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
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .clickable(
                            onClick = {
                                celebrityContentViewModel?.changeView(OthersContentViewType.OthersContentDetail)
                                celebrityContentViewModel?.setSinger(singerList.value[index])
                            }
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    AsyncImage(
                        model = singerList.value[index].imgUrl,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp).clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = singerList.value[index].celebrityName, fontSize = 15.sp, fontWeight = FontWeight.Bold )
                }

//                CoverItem(
//                    singer = singerList.value[index],
//                    onClick = {
//
//                    }
//                )
            }
        }
    }
}