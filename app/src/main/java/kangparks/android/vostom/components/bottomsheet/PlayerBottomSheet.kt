package kangparks.android.vostom.components.bottomsheet

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import kangparks.android.vostom.components.item.CommentItem
import kangparks.android.vostom.components.searchbar.SearchBar
import kangparks.android.vostom.models.content.Comment
import kangparks.android.vostom.viewModel.content.ContentStoreViewModel
import kangparks.android.vostom.viewModel.group.CurrentGroupViewModel
import kangparks.android.vostom.viewModel.player.ContentPlayerViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun PlayerCommentBottomSheet(
    bottomSheetScaffoldState : BottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
    contentStoreViewModel : ContentStoreViewModel,
    contentPlayerViewModel : ContentPlayerViewModel,
    screenWidth : Dp
) {
//    val backgroundColor = MaterialTheme.colorScheme.background

    val context = LocalContext.current
    val isDarkTheme = isSystemInDarkTheme()

    val commentValue = remember { mutableStateOf("") }

    val userId = contentStoreViewModel.userId.observeAsState(-1)
    val currentSongCommentList = contentPlayerViewModel.currentSongCommentList.observeAsState(listOf<Comment>())

    val keyboardController = LocalSoftwareKeyboardController.current

//    ModalBottomSheet(onDismissRequest = { /*TODO*/ }) {
//
//    }
    BottomSheetScaffold(
        sheetContainerColor= if(!isDarkTheme) Color(0xF2E7E7E7) else Color(0xF2474747),
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
                    }
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
                        text = currentSongCommentList.value.size.toString(),
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
                        contentPlayerViewModel.addComment(
                            context = context,
                            commentContent = commentValue.value
                        )
                        commentValue.value =""
                        keyboardController?.hide()
                    }
                )
                Spacer(modifier = Modifier.height(20.dp))
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(bottom = 50.dp)
                ) {
                    Log.d( "[PlayerCommentBottomSheet]", "currentSongCommentList.value: ${currentSongCommentList.value}")
                    items(currentSongCommentList.value.size){
                        CommentItem(
                            userId = userId.value,
                            it = currentSongCommentList.value[it],
                            contentPlayerViewModel =contentPlayerViewModel,
                            screenWidth = screenWidth
                        )
                    }
//                    currentSongCommentList.value.forEach {
//                        item {
//                            CommentItem(
//                                userId = userId.value,
//                                it = it,
//                                contentPlayerViewModel =contentPlayerViewModel,
//                                screenWidth = screenWidth
//                            )
//                        }
//                    }
                }
            }
        }) {}
}