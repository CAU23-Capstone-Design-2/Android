package kangparks.android.vostom.components.content

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import kangparks.android.vostom.components.item.CoverItem
import kangparks.android.vostom.models.item.CoverItemMetaData
import kangparks.android.vostom.viewModel.content.OthersContentListViewModel

@Composable
fun OthersContentList(
    navController : NavHostController? = null,
    viewModel: OthersContentListViewModel = OthersContentListViewModel(),
) {
    val singerList = viewModel.singerList.observeAsState(initial = listOf())

    Text(
        text = "다른 사람의 노래 Cover 살펴보기",
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 10.dp)
    )
    singerList.value.forEach { singer ->
        CoverItem(
            CoverItemMetaData(
                imageUri = singer.imageUri,
                description = singer.description
            )
        )
    }
}