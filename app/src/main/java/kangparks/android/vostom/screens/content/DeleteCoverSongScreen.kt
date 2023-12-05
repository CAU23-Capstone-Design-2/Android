package kangparks.android.vostom.screens.content

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
import kangparks.android.vostom.components.navigationBar.BottomNavigationBar
import kangparks.android.vostom.navigations.HomeContent
import kangparks.android.vostom.viewModel.content.ContentStoreViewModel
import kangparks.android.vostom.viewModel.content.DeleteCoverSongViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DeleteCoverSongScreen(
    navController : NavHostController,
    contentStoreViewModel: ContentStoreViewModel,
    deleteCoverSongViewModel : DeleteCoverSongViewModel = viewModel()
) {
    val context = LocalContext.current
    val myCoverItemList = contentStoreViewModel.myCoverItemList.observeAsState()
    val selectedSong = deleteCoverSongViewModel.songItem.observeAsState(initial = null)


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
        Column{
            ContentAppBar(
                backButtonAction = {
                    navController.popBackStack()
                },
                backButtonContent = "취소",
            )
            Text(
                text = "삭제할 커버곡 선택",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "커버한 삭제 곡은 되돌릴 수 없습니다.😢",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(30.dp))
            Box(){
                LazyColumn(contentPadding = PaddingValues(bottom = 170.dp)){
                    myCoverItemList.value?.forEach{
                        item {
                            Row(
                                modifier = Modifier
                                    .padding(bottom = 10.dp)
                                    .clip(RoundedCornerShape(5.dp))
                                    .selectable(
                                        selected = (selectedSong.value?.id == it.id),
                                        onClick = { deleteCoverSongViewModel.setSongItem(it) },
                                        role = Role.RadioButton
                                    )
                                    .fillMaxWidth(),
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
                text = "선택한 커버곡 삭제하기",
                onClick = {
                        if(selectedSong.value == null){
                            Toast.makeText(context, "선택된 노래가 없습니다.", Toast.LENGTH_SHORT).show()
                        }
                        else{
                            Log.d("DeleteCoverSongScreen", " DeleteCoverSongScreen : 서버 요청")
                            deleteCoverSongViewModel.deleteCurrentCoverSong(
                                context = context,
                            )
                            contentStoreViewModel.updateHomeContent(
                                context = context
                            )
                            Toast.makeText(context, "선택한 커버곡을 삭제했습니다.", Toast.LENGTH_LONG).show()
                            navController.popBackStack()
//
                        }
                }

            )
        }

    }

}