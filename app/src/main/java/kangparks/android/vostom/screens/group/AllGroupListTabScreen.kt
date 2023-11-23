package kangparks.android.vostom.screens.group

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kangparks.android.vostom.components.item.GroupItem
import kangparks.android.vostom.navigations.HomeContent
import kangparks.android.vostom.viewModel.group.CurrentGroupViewModel
import kangparks.android.vostom.viewModel.group.GroupListViewModel

@Composable
fun AllGroupListTabScreen(
    navController: NavHostController,
    isPlaying: State<Boolean>,
    groupListViewModel : GroupListViewModel,
    currentGroupViewModel: CurrentGroupViewModel,
    screenWidth : Int
){
    val allGroupList = groupListViewModel.allGroupList.observeAsState(initial = null)

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            bottom = if(isPlaying.value) 90.dp else 48.dp
        )
    ){
        allGroupList.value?.let {
            List(it.size){index ->
                item{
                    if(index % 2 == 0){
                        Box(
                            modifier = Modifier
                                .padding(
                                    top = 10.dp,
                                    bottom = 10.dp,
                                    end = 10.dp
                                )
                        ){
                            GroupItem(
                                content = it[index],
                                contentSize = (screenWidth-60)/2,
                                onClick = {
                                    currentGroupViewModel.selectGroup(it[index])
                                    navController.navigate(HomeContent.Group.route)
                                }
                            )
                        }
                    }
                    else{
                        Box(
                            modifier = Modifier
                                .padding(
                                    top = 10.dp,
                                    bottom = 10.dp,
                                    start = 10.dp
                                )
                        ){
                            GroupItem(
                                content = it[index],
                                contentSize = (screenWidth-60)/2,
                                onClick = {
                                    currentGroupViewModel.selectGroup(it[index])
                                    navController.navigate(HomeContent.Group.route)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
