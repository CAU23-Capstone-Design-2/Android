package kangparks.android.vostom.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import drawVerticalScrollbar
import kangparks.android.vostom.components.appbar.ContentAppBar
import kangparks.android.vostom.components.item.CoverSongItem
import kangparks.android.vostom.components.item.OthersItem
import kangparks.android.vostom.components.item.UserCoverSongItem
import kangparks.android.vostom.components.player.ContentPlayer
import kangparks.android.vostom.components.section.HorizontalSongSection
import kangparks.android.vostom.models.content.CoverSong
import kangparks.android.vostom.models.content.Singer
import kangparks.android.vostom.utils.dummy.myCoverItemList
import kangparks.android.vostom.utils.dummy.myGroupCoverItemList
import kangparks.android.vostom.utils.dummy.othersItemList

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController) {

    val isDarkTheme = isSystemInDarkTheme()
    val systemUiController = rememberSystemUiController()
    val scrollState = rememberScrollState()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = !isDarkTheme
        )
    }

    Scaffold(
//        contentWindowInsets =
    ){
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding().padding(bottom = 40.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.statusBars)
                    .padding(bottom = 20.dp)
                    .drawVerticalScrollbar(scrollState)
                    .verticalScroll(scrollState),
            ) {
                ContentAppBar(
                    sideButtonAction = {},
                    sideButtonContent = "커버곡 생성",
                    contentTitleFront = "Vos",
                    contentFrontColor = Color(0xFF8e5fe9),
                    contentTitleBack = "tom",
                    contentBackColor = Color(0xFFe0a2ff),
                    containerModifier = Modifier.padding(horizontal = 20.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                HorizontalSongSection(
                    title = "나의 커버곡",
                    contents = myCoverItemList as List<CoverSong>,
                ) { item : CoverSong ->
                    CoverSongItem(content = item)
                }
                Spacer(modifier = Modifier.padding(vertical = 15.dp))
                HorizontalSongSection(
                    title = "나의 그룹 커버곡",
                    contents = myGroupCoverItemList as List<CoverSong>,
                ) { item: CoverSong ->
                    UserCoverSongItem(content = item)
                }
                Spacer(modifier = Modifier.padding(vertical = 15.dp))
                HorizontalSongSection(
                    title = "연예인 AI 커버",
                    contents = othersItemList as List<Singer>,
                    contentPaddingValue = 10
                ) { item: Singer ->
                    OthersItem(content = item)
                }
                Spacer(modifier = Modifier.padding(vertical = 15.dp))
            }
//        ContentPlayer()
        }
    }

}