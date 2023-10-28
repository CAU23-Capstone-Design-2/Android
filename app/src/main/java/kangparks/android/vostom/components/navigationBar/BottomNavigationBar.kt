package kangparks.android.vostom.components.navigationBar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import kangparks.android.vostom.navigations.BottomBarContent
import kangparks.android.vostom.navigations.Content

@ExperimentalMaterial3Api
@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomBarContent.Home,
        BottomBarContent.GroupList,
        BottomBarContent.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = items.any { it.route == currentDestination?.route }

    if (bottomBarDestination) {
        Row(
            modifier = Modifier
                .height(84.dp)
                .fillMaxWidth()
                .background(Color(0xFFF9F9F9))
                .padding(top = 12.dp),
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
                                popUpTo(Content.Home.route)
                                launchSingleTop = true
                            }
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = "icon",
                        tint = Color.Black
                    )
                    Text(
                        text = item.title,
                        color = Color.Black,
                        fontSize = 12.sp,
                    )
                }
            }
        }
    }
}