package kangparks.android.vostom.screens.group

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.exoplayer2.ExoPlayer
import kangparks.android.vostom.components.appbar.ContentAppBar
import kangparks.android.vostom.components.dialog.VostomDialog
import kangparks.android.vostom.components.dropdown.DropDownIconButton
import kangparks.android.vostom.components.template.HomeContentLayoutTemplate
import kangparks.android.vostom.navigations.HomeContent
import kangparks.android.vostom.utils.media.getMediaItem
import kangparks.android.vostom.viewModel.group.CurrentGroupViewModel
import kangparks.android.vostom.viewModel.player.ContentPlayerViewModel

@Composable
fun GroupScreen(
    navController : NavHostController,
    contentPlayerViewModel : ContentPlayerViewModel,
    currentGroupViewModel : CurrentGroupViewModel,
) {
    val isPlaying = contentPlayerViewModel.isPlaying.observeAsState(initial = false)
    val currentGroup = currentGroupViewModel.currentGroup.observeAsState(initial = null)
    val currentGroupItemList = currentGroupViewModel.currentGroupCoverItemList.observeAsState(initial = null)

    val isParticipant = remember {
        mutableStateOf(false)
    }

    val isDropDownOpen = remember {
        mutableStateOf(false)
    }

    val isParticipantDialogOpen = remember {
        mutableStateOf(false)
    }

    val isLeaveGroupDialogOpen = remember {
        mutableStateOf(false)
    }

    val isRemoveGroupDialogOpen = remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    val isDarkTheme = isSystemInDarkTheme()
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = !isDarkTheme
        )
    }

    BackHandler(enabled = true) {
        if(contentPlayerViewModel.isShowPlayer.value == true){
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = !isDarkTheme
            )
            contentPlayerViewModel.hidePlayer()
            return@BackHandler
        }
        else{
            navController.popBackStack()
        }
    }

    HomeContentLayoutTemplate(
        contentPlayerViewModel = contentPlayerViewModel,
        navController = navController,
        surfaceBottomPadding = 0,
        playerBottomPadding = 20,
        surfaceModifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
        isPlaying = isPlaying
    ){
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
        ){
            ContentAppBar(
                backButtonAction = {
                    navController.popBackStack()
                },
                backButtonContent = "목록",
                sideButtonContent = {
                    when(isParticipant.value){
                        true -> {
                            DropDownIconButton(
                                dropDownState = isDropDownOpen.value,
                                dropDownContent = {
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                text = "커버곡 추가",
                                                fontWeight = FontWeight.Bold,
                                                textAlign = TextAlign.Center
                                            )
                                        },
                                        onClick = {
                                            isDropDownOpen.value = false
                                            navController.navigate(HomeContent.AddCoverToGroup.route)
                                        },
                                    )
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                text = "커버곡 삭제",
                                                fontWeight = FontWeight.Bold,
                                                textAlign = TextAlign.Center
                                            )
                                        },
                                        onClick = {
                                            isDropDownOpen.value = false
                                            navController.navigate(HomeContent.RemoveCoverFromGroup.route)
                                        },
                                    )
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                text = "그룹 편집",
                                                fontWeight = FontWeight.Bold,
                                                textAlign = TextAlign.Center
                                            )
                                        },
                                        onClick = {
                                            isDropDownOpen.value = false
                                            navController.navigate(HomeContent.EditGroup.route)
                                        },
                                    )
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                text = "그룹 나가기",
                                                fontWeight = FontWeight.Bold,
                                                textAlign = TextAlign.Center
                                            )
                                        },
                                        onClick = {
                                            isDropDownOpen.value = false
                                            isLeaveGroupDialogOpen.value = true
                                        },
                                    )
                                }
                            )

                        }
                        false -> {
                            Text(
                                text = "그룹 참가",
                                textAlign = TextAlign.End,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .clickable {
                                        isParticipantDialogOpen.value = true
                                    }
                                    .padding(5.dp)
                            )
                        }
                    }
                }
            )

            LazyColumn(
                contentPadding = PaddingValues(bottom = if(isPlaying.value) 100.dp else 15.dp)
            ){
                item {
                    Row {
                        AsyncImage(
                            model = currentGroup.value?.groupImgUri,
                            contentDescription = null,
                            modifier = Modifier
                                .size(140.dp)
                                .clip(RoundedCornerShape(5.dp)),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        Column {
                            Text(
                                text = currentGroup.value?.title ?: "",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = currentGroup.value?.description ?: "",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Row {
                                AsyncImage(
                                    model = currentGroup.value?.userImgUri ?:null,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(20.dp)
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Crop
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = currentGroup.value?.userName?: "",
                                    fontSize = 13.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = "님 외 ${currentGroup.value?.groupMemberCount?.minus(1)}명 참여 중"?: "",
                                    fontSize = 13.sp,
                                    maxLines = 1,
                                )

                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
                currentGroupItemList.value?.let {
                    items(it.size){index ->
                        Row(
                            modifier = Modifier
                                .fillParentMaxWidth()
                                .padding(vertical = 5.dp)
                                .clip(RoundedCornerShape(5.dp))
                                .clickable(onClick =
                                    {

                                    }
                                )
                        ){
                            AsyncImage(
                                model = it[index].albumArtUri ?: null,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(RoundedCornerShape(5.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.width(15.dp))
                            Column {
                                Text(
                                    text = it[index].title,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Spacer(modifier = Modifier.height(5.dp))
                                Text(
                                    text = it[index].userName,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Spacer(modifier = Modifier.height(5.dp))
                                Row {
                                    AsyncImage(
                                        model = it[index].userImgUri?:"",
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(20.dp)
                                            .clip(CircleShape),
                                        contentScale = ContentScale.Crop
                                    )
                                    Spacer(modifier = Modifier.width(5.dp))
                                    Text(
                                        text = it[index].userName + "님의 커버" ?: "",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Normal,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    AnimatedVisibility(visible = isParticipantDialogOpen.value) {
        VostomDialog(
            title = "그룹에 참가하시겠습니까?",
            content = "그룹에서 커버곡을 공유해봐요!",
            positiveText = "확인",
            negativeText = "취소",
            onPositiveClick = {
                isParticipant.value = !isParticipant.value
                isParticipantDialogOpen.value = false
            },
            onNegativeClick = {
                isParticipantDialogOpen.value = false
            },
            onDismiss = {
                isParticipantDialogOpen.value = false
            }
        )
    }
    AnimatedVisibility(visible = isLeaveGroupDialogOpen.value) {
        VostomDialog(
            title = "그룹에서 떠나겠습니까?",
            content = "그룹에 공유한 커버곡은 삭제됩니다.",
            positiveText = "확인",
            negativeText = "취소",
            onPositiveClick = {
                isParticipant.value = !isParticipant.value
                isLeaveGroupDialogOpen.value = false
            },
            onNegativeClick = {
                isLeaveGroupDialogOpen.value = false
            },
            onDismiss = {
                isLeaveGroupDialogOpen.value = false
            }
        )
    }
    AnimatedVisibility(visible = isRemoveGroupDialogOpen.value) {
        VostomDialog(
            title = "그룹를 삭제하시겠습니까?",
            content = "그룹에 공유한 커버곡은 삭제됩니다.",
            positiveText = "확인",
            negativeText = "취소",
            onPositiveClick = {
                isParticipant.value = !isParticipant.value
                isLeaveGroupDialogOpen.value = false
            },
            onNegativeClick = {
                isLeaveGroupDialogOpen.value = false
            },
            onDismiss = {
                isLeaveGroupDialogOpen.value = false
            }
        )
    }
}