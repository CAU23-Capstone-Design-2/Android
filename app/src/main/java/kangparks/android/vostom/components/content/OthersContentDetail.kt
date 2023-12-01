package kangparks.android.vostom.components.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import coil.compose.AsyncImage
import kangparks.android.vostom.components.button.IconAndTextButton
import kangparks.android.vostom.components.item.SongItem
import kangparks.android.vostom.viewModel.bottomsheet.CelebrityContentViewModel
import kangparks.android.vostom.viewModel.bottomsheet.OthersContentViewType

@Composable
fun OthersContentDetail(
    celebrityContentViewModel : CelebrityContentViewModel
) {
    val currentCelebrity = celebrityContentViewModel.currentSigner.observeAsState(initial = null)
    val listOfCoverSongs = celebrityContentViewModel.currentSingerMusicList.observeAsState(listOf())

    Column {
        LazyColumn(
            state = rememberLazyListState(),
            contentPadding = PaddingValues(bottom = 48.dp)
        ){
            item {
                IconAndTextButton(
                    backButtonAction = {
                        celebrityContentViewModel.changeView(OthersContentViewType.OthersContentList)
                    },
                    backButtonContent = "이전",
                    containerModifier = Modifier.padding(bottom = 15.dp)
                )
//                CoverItem(
//                    singer = singer,
//                    size = 64
//                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clip(RoundedCornerShape(5.dp)),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    AsyncImage(
                        model = currentCelebrity.value?.imgUrl,
                        contentDescription = null,
                        modifier = Modifier.size(64.dp).clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    currentCelebrity.value?.celebrityName?.let { Text(text = it, fontSize = 15.sp, fontWeight = FontWeight.Bold ) }
                }
                Spacer(modifier = Modifier.height(15.dp))
            }

            items(listOfCoverSongs.value.size) { index ->
                SongItem(
                    music = listOfCoverSongs.value[index]
                )
            }

        }
    }
}