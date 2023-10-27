package kangparks.android.vostom.screens.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(navController : NavHostController){

    Box(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(horizontal = 20.dp)
            .padding(bottom = 48.dp)
    ){
        ContentAppBar(
            sideButtonAction = {},
            sideButtonContent = "편집",
            contentTitleFront = "My",
            contentFrontColor = Color(0xFF000000),
            contentTitleBack = " Page",
            contentBackColor = Color(0xFFc1b7c1),
        )
    }
}