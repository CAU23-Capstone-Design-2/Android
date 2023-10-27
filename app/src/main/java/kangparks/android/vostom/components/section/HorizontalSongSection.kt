package kangparks.android.vostom.components.section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kangparks.android.vostom.components.item.CoverSongItem
import kangparks.android.vostom.models.content.CoverSong

@Composable
fun HorizontalSongSection(
    title : String,
    sideButtonAction : ()->Unit = {},
    contents : List<CoverSong>,
) {
    Column {
        Text(
            text = title,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 20.dp, bottom = 15.dp)
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp)
        ){

            items(contents.size){ index ->
                CoverSongItem(
                    content = contents[index]
                )
                Spacer(modifier = Modifier.width(10.dp))
            }
        }
    }

}