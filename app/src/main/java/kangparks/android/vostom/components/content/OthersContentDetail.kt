package kangparks.android.vostom.components.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kangparks.android.vostom.components.button.IconAndTextButton
import kangparks.android.vostom.components.item.CelebrityItemHorizontal
import kangparks.android.vostom.components.item.CelebrityMusicItem
import kangparks.android.vostom.viewModel.bottomsheet.CelebrityContentViewModel
import kangparks.android.vostom.viewModel.bottomsheet.CelebrityContentViewType

@Composable
fun CelebrityContentDetail(
    celebrityContentViewModel : CelebrityContentViewModel
) {
    val currentCelebrity = celebrityContentViewModel.currentCelebrity.observeAsState(initial = null)
    val listOfCoverSongs = celebrityContentViewModel.currentCelebrityMusicList.observeAsState(listOf())

    Column {
        LazyColumn(
            state = rememberLazyListState(),
            contentPadding = PaddingValues(bottom = 48.dp)
        ){
            item {
                IconAndTextButton(
                    backButtonAction = {
                        celebrityContentViewModel.changeView(CelebrityContentViewType.CelebrityContentList)
                    },
                    backButtonContent = "이전",
                    containerModifier = Modifier.padding(bottom = 15.dp)
                )
                currentCelebrity.value?.let {
                    CelebrityItemHorizontal(
                        content = it,
                        contentSize = 64,
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
            }

            items(listOfCoverSongs.value.size) { index ->
                CelebrityMusicItem(
                    music = listOfCoverSongs.value[index]
                )
            }

        }
    }
}