package kangparks.android.vostom.components.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerCommentBottomSheet(
    bottomSheetScaffoldState : BottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
) {
//    val  bottomSheetScaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    val backgroundColor = Color(0x52292929)

    BottomSheetScaffold(
        containerColor = backgroundColor,
        contentColor = backgroundColor,
        sheetContainerColor= backgroundColor,
        sheetContentColor= backgroundColor,
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = 90.dp,
        sheetSwipeEnabled = true,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(700.dp)
//                    .background(color = Color(0x8AB1B1B1))
                ,
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Text(text = "Hello")
            }
        }) {

    }
}