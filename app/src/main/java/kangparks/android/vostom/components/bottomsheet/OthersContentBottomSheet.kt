package kangparks.android.vostom.components.bottomsheet

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import drawVerticalScrollbar
import kangparks.android.vostom.components.content.OthersContentDetail
import kangparks.android.vostom.components.content.OthersContentList
import kangparks.android.vostom.viewModel.bottomsheet.OthersContentBottomSheetViewModel
import kangparks.android.vostom.viewModel.bottomsheet.OthersContentViewType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OthersContentBottomSheet(
    bottomSheetScaffoldState : BottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
    viewModel : OthersContentBottomSheetViewModel = OthersContentBottomSheetViewModel()
) {
    val currentView = viewModel.currentView.observeAsState(initial = OthersContentViewType.OthersContentList)
    val currentSinger = viewModel.currentSigner.observeAsState(initial = null)
    val scrollState = rememberScrollState()

    BottomSheetScaffold(
        sheetShadowElevation = 10.dp,
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp)
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                AnimatedVisibility(
                    visible = currentView.value.viewType == OthersContentViewType.OthersContentList.viewType,
                    enter = fadeIn(),
                    exit = fadeOut()
                ){
                    OthersContentList(bottomSheetViewModel = viewModel)
                }
                AnimatedVisibility(
                    visible = (currentView.value.viewType == OthersContentViewType.OthersContentDetail.viewType)&&(currentSinger.value != null),
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    OthersContentDetail(bottomSheetViewModel = viewModel, singer = currentSinger.value!!)
                }
            }
        },
        sheetPeekHeight = 120.dp,
        sheetSwipeEnabled = true
    ) {}


}