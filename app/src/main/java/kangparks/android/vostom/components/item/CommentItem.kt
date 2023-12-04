package kangparks.android.vostom.components.item

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import kangparks.android.vostom.components.dropdown.DropDownIconButton
import kangparks.android.vostom.components.searchbar.SearchBar
import kangparks.android.vostom.models.content.Comment
import kangparks.android.vostom.navigations.HomeContent
import kangparks.android.vostom.viewModel.player.ContentPlayerViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CommentItem(
    it: Comment,
    contentPlayerViewModel : ContentPlayerViewModel,
    screenWidth: Dp
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current
    val isEditMode = remember {
        mutableStateOf(false)
    }

    val commentValue = remember {
        mutableStateOf(it.content)
    }

    val isDropDownOpen = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.padding(bottom = 30.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = it.userImgUri,
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = it.nickname,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = it.date.substring(0, 10),
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
            )
            DropDownIconButton(
                dropDownState = isDropDownOpen,
                dropDownIconSize = 34,
                dropDownContent = {
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = "댓글 수정",
//                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        },
                        onClick = {
                            isDropDownOpen.value = false
                            isEditMode.value = true
                        },
                    )
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = "댓글 삭제",
//                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        },
                        onClick = {
                            isDropDownOpen.value = false
                            contentPlayerViewModel.deleteSelectedComment(
                                context = context,
                                commentId = it.id
                            )

                        },
                    )
                }
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.padding(start = 38.dp),
            verticalAlignment = Alignment.Top,
        ) {
            AnimatedVisibility(
                visible = !isEditMode.value,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Row {
                    Text(
                        text = it.content,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .width(screenWidth - 140.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .wrapContentWidth()
                            .clickable {
                                if (it.likedByUser) {
                                    contentPlayerViewModel.setUnLikeComment(
                                        context = context,
                                        commentId = it.id
                                    )
                                } else {
                                    contentPlayerViewModel.setLikeComment(
                                        context = context,
                                        commentId = it.id
                                    )
                                }
                            }
                    ) {
                        Spacer(modifier = Modifier.width(5.dp))
                        Icon(
                            imageVector = if (it.likedByUser) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = null,
                            modifier = Modifier.size(14.dp),
                            tint = Color(0xFFFF6078),
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = it.likeCount.toString(),
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                    }
                }
            }
            AnimatedVisibility(
                visible = isEditMode.value,
                enter = fadeIn(),
                exit = fadeOut()
            ){
                SearchBar(
                    value = commentValue.value,
                    onValueChange = { commentValue.value = it },
                    placeholder = "댓글을 입력해주세요.",
                    onSearch = {
                        contentPlayerViewModel.editCurrentComment(
                            context = context,
                            commentId = it.id,
                            comment = commentValue.value.toString()
                        )
                        commentValue.value =""
                        isEditMode.value = false
                        keyboardController?.hide()
                    }
                )
            }


        }

    }
}