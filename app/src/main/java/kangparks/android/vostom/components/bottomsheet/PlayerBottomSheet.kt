package kangparks.android.vostom.components.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kangparks.android.vostom.components.searchbar.SearchBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun PlayerCommentBottomSheet(
    bottomSheetScaffoldState : BottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
) {
//    val backgroundColor = MaterialTheme.colorScheme.background

    val commentValue = remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current


//    ModalBottomSheet(onDismissRequest = { /*TODO*/ }) {
//
//    }
    BottomSheetScaffold(
        sheetContainerColor= Color(0xF2E7E7E7),
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = 100.dp,
        sheetSwipeEnabled = true,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(700.dp)
                    .padding(horizontal = 20.dp)
                    .pointerInput(Unit) {
                        detectTapGestures {
                            keyboardController?.hide()
                        }
//                        detectDragGestures { change, dragAmount ->
//                            keyboardController?.hide()
//                            if(dragAmount.y <-10){
//                                CoroutineScope(Dispatchers.Default).launch {
//                                    bottomSheetScaffoldState.bottomSheetState.hide()
//                                }
//
//                            }else if (dragAmount.y > 10){
//                                CoroutineScope(Dispatchers.Default).launch {
//                                    bottomSheetScaffoldState.bottomSheetState.expand()
//                                }
//                            }
//                        }
                    }
//                    .background(color = Color(0x8AB1B1B1))
                ,
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "댓글",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "2",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color(0xFF9867FF)
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                SearchBar(
                    value = commentValue.value,
                    onValueChange = { commentValue.value = it },
                    placeholder = "댓글을 입력해주세요.",
                    onSearch = {
                        keyboardController?.hide()
                    }
                )
                Spacer(modifier = Modifier.height(20.dp))


            }
        }) {

    }
}