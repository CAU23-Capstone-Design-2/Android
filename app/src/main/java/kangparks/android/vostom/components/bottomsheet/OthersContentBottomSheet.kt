package kangparks.android.vostom.components.bottomsheet

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kangparks.android.vostom.components.content.OthersContentDetail
import kangparks.android.vostom.components.content.OthersContentList
import kangparks.android.vostom.viewModel.bottomsheet.CelebrityContentViewModel
import kangparks.android.vostom.viewModel.bottomsheet.OthersContentViewType

@SuppressLint("UnusedCrossfadeTargetStateParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OthersContentBottomSheet(
    bottomSheetScaffoldState : BottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
    celebrityContentViewModel : CelebrityContentViewModel
) {
    val currentView = celebrityContentViewModel.currentView.observeAsState(initial = OthersContentViewType.OthersContentList)
    val currentSinger = celebrityContentViewModel.currentSigner.observeAsState(initial = null)
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
                    OthersContentList(
                        celebrityContentViewModel = celebrityContentViewModel
                    )
                }
                AnimatedVisibility(
                    visible = (currentView.value.viewType == OthersContentViewType.OthersContentDetail.viewType)&&(currentSinger.value != null),
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    OthersContentDetail(
                        celebrityContentViewModel = celebrityContentViewModel,
                    )
                }
            }
        },
        sheetPeekHeight = 120.dp,
        sheetSwipeEnabled = true
    ) {}


}