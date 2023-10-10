package kangparks.android.vostom.components.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kangparks.android.vostom.components.button.IconAndTextButton
import kangparks.android.vostom.components.item.CoverItem
import kangparks.android.vostom.components.item.SongItem
import kangparks.android.vostom.models.content.Singer
import kangparks.android.vostom.viewModel.bottomsheet.OthersContentBottomSheetViewModel
import kangparks.android.vostom.viewModel.bottomsheet.OthersContentDetailViewModel
import kangparks.android.vostom.viewModel.bottomsheet.OthersContentViewType

@Composable
fun OthersContentDetail(
    navController : NavHostController? = null,
    bottomSheetViewModel : OthersContentBottomSheetViewModel?,
    singer: Singer
) {
    val othersContentDetailViewModel: OthersContentDetailViewModel = OthersContentDetailViewModel(
        _currentSinger = Singer(singer.id, singer.name, singer.imageUri, singer.description)
    )
    val listOfCoverSongs = othersContentDetailViewModel.listOfCoverSongs.observeAsState(listOf())

    Column {
        LazyColumn(
            state = rememberLazyListState(),
            contentPadding = PaddingValues(bottom = 48.dp)
        ){
            item {
                IconAndTextButton(
                    backButtonAction = {
                        if(bottomSheetViewModel != null) {
                            bottomSheetViewModel?.changeView(OthersContentViewType.OthersContentList)
                        }
                    },
                    backButtonContent = "이전",
                    containerModifier = Modifier.padding(bottom = 15.dp)
                )
                CoverItem(
                    singer = singer,
                    size = 64
                )
                Spacer(modifier = Modifier.height(15.dp))
            }

            items(listOfCoverSongs.value.size) { index ->
                SongItem(
                    song = listOfCoverSongs.value[index]
                )
            }

        }
    }
}