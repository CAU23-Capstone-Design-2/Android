package kangparks.android.vostom.screens.home

import android.annotation.SuppressLint
import android.widget.Space
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import drawVerticalScrollbar
import kangparks.android.vostom.Greeting
import kangparks.android.vostom.components.appbar.ContentAppBar
import kangparks.android.vostom.components.button.RoundedButton
import kangparks.android.vostom.components.navigationBar.BottomNavigationBar
import kangparks.android.vostom.components.section.HorizontalSongSection
import kangparks.android.vostom.models.content.CoverSong

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController : NavHostController){

    val isDarkTheme = isSystemInDarkTheme()
    val systemUiController = rememberSystemUiController()
    val scrollState = rememberScrollState()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = !isDarkTheme
        )
    }

    Surface(
        modifier = Modifier.fillMaxSize().padding(bottom = 64.dp),
        color = MaterialTheme.colorScheme.background
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.statusBars).padding(bottom = 20.dp).drawVerticalScrollbar(scrollState).verticalScroll(scrollState),
        ){
            ContentAppBar(
                sideButtonAction = {},
                sideButtonContent = "커버곡 생성하기",
                contentTitleFront = "Vos",
                contentFrontColor = Color(0xFF8e5fe9),
                contentTitleBack = "tom",
                contentBackColor = Color(0xFFe0a2ff),
                containerModifier = Modifier.padding(horizontal = 20.dp)
            )

            HorizontalSongSection(
                title = "나의 커버곡",
                contents = listOf<CoverSong>(
                    CoverSong(
                        id =1,
                        title = "커버곡1",
                        singer = "아티스트1",
                        user = "유저1",
                        albumArtUri = "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg",
                        userImgUri = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
                    ),
                    CoverSong(
                        id =2,
                        title = "커버곡1",
                        singer = "아티스트1",
                        user = "유저1",
                        albumArtUri = "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg",
                        userImgUri = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
                    ),
                    CoverSong(
                        id =3,
                        title = "커버곡1",
                        singer = "아티스트1",
                        user = "유저1",
                        albumArtUri = "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg",
                        userImgUri = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
                    ),
                    CoverSong(
                        id =4,
                        title = "커버곡1",
                        singer = "아티스트1",
                        user = "유저1",
                        albumArtUri = "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg",
                        userImgUri = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
                    ),
                    CoverSong(
                        id =5,
                        title = "커버곡1",
                        singer = "아티스트1",
                        user = "유저1",
                        albumArtUri = "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg",
                        userImgUri = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
                    )
                ),
            )
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            HorizontalSongSection(
                title = "나의 커버곡",
                contents = listOf<CoverSong>(
                    CoverSong(
                        id =1,
                        title = "커버곡1",
                        singer = "아티스트1",
                        user = "유저1",
                        albumArtUri = "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg",
                        userImgUri = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
                    ),
                    CoverSong(
                        id =2,
                        title = "커버곡1",
                        singer = "아티스트1",
                        user = "유저1",
                        albumArtUri = "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg",
                        userImgUri = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
                    ),
                    CoverSong(
                        id =3,
                        title = "커버곡1",
                        singer = "아티스트1",
                        user = "유저1",
                        albumArtUri = "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg",
                        userImgUri = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
                    ),
                    CoverSong(
                        id =4,
                        title = "커버곡1",
                        singer = "아티스트1",
                        user = "유저1",
                        albumArtUri = "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg",
                        userImgUri = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
                    ),
                    CoverSong(
                        id =5,
                        title = "커버곡1",
                        singer = "아티스트1",
                        user = "유저1",
                        albumArtUri = "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg",
                        userImgUri = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
                    )
                ),
            )
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            HorizontalSongSection(
                title = "나의 커버곡",
                contents = listOf<CoverSong>(
                    CoverSong(
                        id =1,
                        title = "커버곡1",
                        singer = "아티스트1",
                        user = "유저1",
                        albumArtUri = "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg",
                        userImgUri = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
                    ),
                    CoverSong(
                        id =2,
                        title = "커버곡1",
                        singer = "아티스트1",
                        user = "유저1",
                        albumArtUri = "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg",
                        userImgUri = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
                    ),
                    CoverSong(
                        id =3,
                        title = "커버곡1",
                        singer = "아티스트1",
                        user = "유저1",
                        albumArtUri = "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg",
                        userImgUri = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
                    ),
                    CoverSong(
                        id =4,
                        title = "커버곡1",
                        singer = "아티스트1",
                        user = "유저1",
                        albumArtUri = "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg",
                        userImgUri = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
                    ),
                    CoverSong(
                        id =5,
                        title = "커버곡1",
                        singer = "아티스트1",
                        user = "유저1",
                        albumArtUri = "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg",
                        userImgUri = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
                    )
                ),
            )
            Spacer(modifier = Modifier.padding(vertical = 20.dp))
        }
    }
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .windowInsetsPadding(WindowInsets.statusBars)
//            .padding(horizontal = 20.dp)
//            .padding(bottom = 48.dp)
//    ){
//
//    }
}