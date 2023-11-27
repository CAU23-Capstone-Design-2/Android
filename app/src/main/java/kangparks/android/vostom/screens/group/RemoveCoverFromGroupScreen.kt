package kangparks.android.vostom.screens.group

import android.widget.Toast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kangparks.android.vostom.components.appbar.ContentAppBar
import kangparks.android.vostom.components.blur.BlurForList
import kangparks.android.vostom.components.button.RoundedButton
import kangparks.android.vostom.viewModel.group.CurrentGroupViewModel
import kangparks.android.vostom.viewModel.group.RemoveCoverFromGroupViewModel

@Composable
fun RemoveCoverFromGroupScreen(
    accessToken : String,
    navController : NavHostController,
    currentGroupViewModel : CurrentGroupViewModel,
    removeCoverFromGroupViewModel: RemoveCoverFromGroupViewModel = viewModel()
) {
    val currentGroupCoverList = currentGroupViewModel.currentGroupCoverItemList.observeAsState(listOf())
    val selectedSong = removeCoverFromGroupViewModel.songItem.observeAsState(initial = null)

    val isDarkTheme = isSystemInDarkTheme()
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = !isDarkTheme
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(horizontal = 20.dp)
            .padding(bottom = 48.dp)
    ){
        Column {
            ContentAppBar(
                backButtonAction = {
                    navController.popBackStack()
                },
                backButtonContent = "취소",
            )
            Text(
                text = "그룹에서 나의 커버 곡 삭제",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "그룹에서 삭제할 커버 곡을 선택해 주세요.",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.height(30.dp))
            Box {
                // TODO 그룹 내 커버 곡 중 나의 커버 곡만 보여주기
                LazyColumn(
                    contentPadding = PaddingValues(bottom = 170.dp)
                ){
                    currentGroupCoverList.value?.forEach {
                        item {
                            Row(
                                modifier = Modifier
                                    .padding(bottom = 10.dp)
                                    .clip(RoundedCornerShape(5.dp))
                                    .selectable(
                                        selected = (selectedSong.value?.id == it.id),
                                        onClick = { removeCoverFromGroupViewModel.setSongItem(it) },
                                        role = Role.RadioButton
                                    ).fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    //                                    modifier = Modifier.height(80.dp),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    RadioButton(
                                        selected = (selectedSong.value?.id == it.id),
                                        onClick = null
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                AsyncImage(
                                    model = it.albumArtUri,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(60.dp)
                                        .clip(RoundedCornerShape(5.dp)),
                                    contentScale = ContentScale.Crop
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = it.title,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
                BlurForList()
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(
                    bottom = 58.dp,
                ),
            contentAlignment = Alignment.BottomCenter
        ){
            RoundedButton(
                text = "선택한 커버곡 추가하기",
                onClick = {
                    if(selectedSong.value == null){
                        Toast.makeText(navController.context, "선택된 노래가 없습니다.", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        removeCoverFromGroupViewModel.removeCoverFromGroup(accessToken)
                        Toast.makeText(navController.context, "선택한 커버곡을 그룹에서 삭제했습니다.", Toast.LENGTH_LONG).show()
                        navController.popBackStack()
                    }
                }

            )
        }
    }

}