package kangparks.android.vostom.screens.profile

import android.annotation.SuppressLint
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.exoplayer2.ExoPlayer
import drawVerticalScrollbar
import kangparks.android.vostom.components.appbar.ContentAppBar
import kangparks.android.vostom.components.button.RoundedButton
import kangparks.android.vostom.components.item.CoverSongItem
import kangparks.android.vostom.components.item.UserCoverSongItem
import kangparks.android.vostom.components.section.HorizontalSongSection
import kangparks.android.vostom.components.skeleton.CoverSongItemSkeleton
import kangparks.android.vostom.components.template.HomeContentLayoutTemplate
import kangparks.android.vostom.components.template.PullRefreshLayoutTemplate
import kangparks.android.vostom.models.content.Music
import kangparks.android.vostom.navigations.Auth
import kangparks.android.vostom.navigations.Control
import kangparks.android.vostom.navigations.HomeContent
import kangparks.android.vostom.navigations.Nav
import kangparks.android.vostom.utils.helper.media.getMediaSource
import kangparks.android.vostom.utils.media.getMediaItem
import kangparks.android.vostom.utils.store.deleteAccessToken
import kangparks.android.vostom.utils.store.getAccessToken
import kangparks.android.vostom.viewModel.content.ContentStoreViewModel
import kangparks.android.vostom.viewModel.player.ContentPlayerViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    navController: NavHostController,
    contentPlayerViewModel : ContentPlayerViewModel,
    contentStoreViewModel : ContentStoreViewModel,
) {
    val myCoverItemList = contentStoreViewModel.myCoverItemList.observeAsState(initial = listOf())
    val userName = contentStoreViewModel.userName.observeAsState(initial = "")
    val userImgUrl = contentStoreViewModel.userImgUrl.observeAsState(initial = "")
    val likedCoverItemList = contentStoreViewModel.likeItemList.observeAsState(initial = listOf())

    val isPlaying = contentPlayerViewModel.isPlaying.observeAsState(initial = false)
    val isDarkTheme = isSystemInDarkTheme()
    val systemUiController = rememberSystemUiController()
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }

    fun refresh() = refreshScope.launch {
        refreshing = true
        delay(1000)
        contentStoreViewModel.updateProfileContent(context)
        refreshing = false
    }

    val state = rememberPullRefreshState(refreshing, ::refresh)

    LaunchedEffect(key1 = null){
        delay(500)
        contentStoreViewModel.initProfileContent(context)
    }

    LaunchedEffect(key1 = userName.value){
        contentStoreViewModel.updateUserName(userName.value)
    }

    LaunchedEffect(key1 = userImgUrl.value){
        contentStoreViewModel.updateUserImgUrl(userImgUrl.value)
    }

    LaunchedEffect(key1 = likedCoverItemList.value){
        contentStoreViewModel.updateLikeItemList(likedCoverItemList.value)
    }

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

    PullRefreshLayoutTemplate(
        state = state,
        refreshing = refreshing,
        isDarkTheme = isDarkTheme
    ){
        HomeContentLayoutTemplate(
            contentPlayerViewModel = contentPlayerViewModel,
            surfaceModifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
            navController = navController,
            isPlaying = isPlaying
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.statusBars)
                    .padding(bottom = 20.dp)
                    .drawVerticalScrollbar(scrollState)
                    .verticalScroll(scrollState),
            )  {
                ContentAppBar(
                    sideButtonContent = {
                        Text(
                            text = "편집",
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .clickable {
                                    navController.navigate(HomeContent.EditProfile.route)
                                }
                                .padding(5.dp)
                        )
                    },
                    contentTitle = "프로필",
                    containerModifier = Modifier.padding(horizontal = 20.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp,
                            vertical = 10.dp
                        )
                ){
                    AsyncImage(
                        model = userImgUrl.value,
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF8B62FF)),
                        contentScale = ContentScale.Crop

                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Column {
                        Text(
                            text = userName.value,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = "생성한 AI 커버 곡 : ${myCoverItemList.value.size}개",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                RoundedButton(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    text = "요청 중인 커버 곡 보기",
                    onClick = {
                        navController.navigate(HomeContent.RequestCoverSongList.route)
                    }
                )
                Spacer(modifier = Modifier.height(30.dp))
                HorizontalSongSection(
                    title = "나의 커버곡",
                    contents = myCoverItemList.value as List<Music>,
                    sideButtonAction = {
                        if (myCoverItemList.value.isNotEmpty()) {
                            navController.navigate(HomeContent.DetailMyCoverItem.route)
                        }
                    },
                    renderItem = { item: Music ->
                        CoverSongItem(
                            content = item,
                            onClick = {
                                val token = getAccessToken(context)
                                if(token != null){
                                    val mediaSource = getMediaSource(
                                        context = context,
                                        musicId = item.id
                                    )
                                    contentPlayerViewModel.setMediaSource(
                                        context = context,
                                        mediaSource = mediaSource
                                    )
                                    contentPlayerViewModel.playMusic(item)
                                }
                            }
                        )
                    },
                    skeletonItem = {
                        CoverSongItemSkeleton()
                    }
                )
                Spacer(modifier = Modifier.padding(vertical = 15.dp))
                HorizontalSongSection(
                    title = "좋아요 한 커버곡",
                    contents =likedCoverItemList.value as List<Music>,
                    sideButtonAction = {
                        if (likedCoverItemList.value.isNotEmpty()) {
                            navController.navigate(HomeContent.DetailLikeCoverItem.route)
                        }
                    },
                    renderItem = { item: Music ->
                        UserCoverSongItem(
                            content = item,
                            onClick = {
                                val token = getAccessToken(context)
                                if(token != null){
                                    val mediaSource = getMediaSource(
                                        context = context,
                                        musicId = item.id
                                    )
                                    contentPlayerViewModel.setMediaSource(
                                        context = context,
                                        mediaSource = mediaSource
                                    )
                                    contentPlayerViewModel.playMusic(item)
                                }
                            }
                        )
                    },
                    skeletonItem = {
                        CoverSongItemSkeleton(true)
                    }
                )
                Spacer(modifier = Modifier.padding(vertical = 20.dp))
                Column(
                    modifier = Modifier.padding(horizontal = 10.dp)
                ){
                    TextButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.padding(bottom = 0.dp)
                    ) {
                        Text(
                            text = "목소리 다시 학습하기",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                    TextButton(
                        onClick = {
                            deleteAccessToken(context)
                            Toast.makeText(context, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show()

                            val intent = context.packageManager.getLaunchIntentForPackage(context.packageName)

                            intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            context.startActivity(intent)
//                            navController.navigate(Auth.Login.route){
//                                popUpTo(Auth.Login.route){
//                                    inclusive = true
//                                }
//                            }
                        },
                        modifier = Modifier.padding(bottom = 0.dp)
                    ) {
                        Text(
                            text = "로그아웃",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    }

                    TextButton(
                        onClick = {
                                  Toast.makeText(context, "아쉽게도 Vostom은 한번 가입하면 탈퇴할 수 없어요😍", Toast.LENGTH_SHORT).show()
                                  },
                        modifier = Modifier.padding(bottom = 0.dp)
                    ) {
                        Text(
                            text = "탈퇴하기",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(vertical = if(isPlaying.value) 48.dp else 10.dp))
            }
        }
    }
}