package kangparks.android.vostom.components.navigationBar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import kangparks.android.vostom.navigations.BottomBarContent
import kangparks.android.vostom.navigations.HomeContent

@ExperimentalMaterial3Api
@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomBarContent.Home,
        BottomBarContent.GroupList,
        BottomBarContent.Profile
    )

    val isDarkTheme = isSystemInDarkTheme()
    val fontColor = if (isDarkTheme) Color(0xFFBEBEBE) else Color(0xFF292929)

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val navigationBarHeight = WindowInsets.navigationBars
    val bottomBarDestination = items.any { it.route == currentDestination?.route }

    if (bottomBarDestination) {
        Column {
            Row(
                modifier = Modifier
                    .height(64.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(top = 12.dp)
                    .drawBehind {
                        val borderSize = 1.dp
                        drawLine(
                            color = if (isDarkTheme) Color(0xFF292929) else Color(0xFFF1F1F1),
                            start = Offset(0f, -12.dp.toPx()),
                            end = Offset(size.width, -12.dp.toPx()),
                            strokeWidth = borderSize.toPx()
                        )
                    },
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.Top
            ) {
                items.forEach { item ->
                    Column(
                        modifier = Modifier
                            .width(40.dp)
                            .height(40.dp)
                            .clip(shape = MaterialTheme.shapes.small)
                            .clickable {
                                navController.navigate(item.route) {
                                    popUpTo(HomeContent.Home.route)
                                    launchSingleTop = true
                                }
                            },
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Icon(
                            painter = if(currentDestination!!.route == item.route) painterResource(id = item.fillIcon) else painterResource(
                                id = item.outlineIcon
                            ),
                            contentDescription = "icon",
                            tint = if(currentDestination!!.route == item.route) Color(0xFF9867FF) else fontColor
                        )
                        Text(
                            text = item.title,
                            color = if(currentDestination!!.route == item.route) Color(0xFF9867FF) else fontColor,
                            fontSize = 12.sp,
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .background(Color(0xFFF9F9F9))
            ) {

            }
        }

    }
}