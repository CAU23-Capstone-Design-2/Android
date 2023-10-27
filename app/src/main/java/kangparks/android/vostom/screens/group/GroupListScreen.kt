package kangparks.android.vostom.screens.group

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kangparks.android.vostom.components.appbar.ContentAppBar
import kangparks.android.vostom.navigations.Content

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GroupListScreen(navController : NavHostController){

    Box(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(horizontal = 20.dp)
            .padding(bottom = 48.dp)
    ){
        Column {
            ContentAppBar(
                sideButtonAction = {
                    navController.navigate(Content.BuildGroup.route)
                },
                sideButtonContent = "그룹 만들기",
                contentTitleFront = "Gr",
                contentFrontColor = Color(0xFF000000),
                contentTitleBack = "oup",
                contentBackColor = Color(0xFFc1b7c1),
            )
        }
    }
}