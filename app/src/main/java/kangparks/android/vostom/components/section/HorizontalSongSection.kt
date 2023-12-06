package kangparks.android.vostom.components.section

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun <T> HorizontalSongSection(
    title: String,
    sideButtonAction: ()->Unit = {},
    contents: List<T>,
    contentPaddingValue : Int = 10,
    renderItem: @Composable (T, Int) -> Unit = { t: T, i: Int -> },
    skeletonItem: @Composable () -> Unit = {}
) where T : Any{
    Column{
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 20.dp, bottom = 10.dp)
                .clip(RoundedCornerShape(5.dp))
                .clickable { sideButtonAction() }
        ){
            Text(
                text = title,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 17.sp,
            )
            Box (
                modifier = Modifier.fillMaxHeight(),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    imageVector = Icons.Rounded.KeyboardArrowRight,
                    contentDescription = "Next Button",
                    modifier = Modifier
                        .width(24.dp)
                        .height(28.dp)
                )
            }
        }

        Crossfade(targetState = contents, label = "") {
            when(it.isEmpty()){
                true -> LazyRow(
                    contentPadding = PaddingValues(horizontal = 20.dp)
                ){
                    items(3) {
                        skeletonItem()
                        Spacer(modifier = Modifier.width(contentPaddingValue.dp))
                    }

                }
                false -> LazyRow(
                    contentPadding = PaddingValues(horizontal = 20.dp)
                ){

                    items(contents.size){ index ->
                        renderItem(contents[index], index)
                        Spacer(modifier = Modifier.width(contentPaddingValue.dp))
                    }
                }
            }
        }
    }
}