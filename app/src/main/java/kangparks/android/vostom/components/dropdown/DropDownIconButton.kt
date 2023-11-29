package kangparks.android.vostom.components.dropdown

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kangparks.android.vostom.R

@Composable
fun DropDownIconButton(
//    onClick: () -> Unit,
//    icon: @Composable () -> Unit,
//    content: @Composable () -> Unit,
    dropDownState : Boolean,
    dropDownIconSize : Int = 38,
    dropDownContent: @Composable ()->Unit,
) {
    val isDropDownOpen = remember {
        mutableStateOf(false)
    }

    Box {
        Icon(
            painter = painterResource(id = R.drawable.ri_more_2_fill),
            contentDescription = null,
            modifier = Modifier
                .size(dropDownIconSize.dp)
                .clip(shape = CircleShape)
                .clickable { isDropDownOpen.value = true }
                .padding(10.dp),
        )
        DropdownMenu(
            expanded = isDropDownOpen.value,
            onDismissRequest = { isDropDownOpen.value = false },
            modifier = Modifier.wrapContentSize()
        ) {
            dropDownContent()
        }
    }

}