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
import kangparks.android.vostom.components.content.CelebrityContentDetail
import kangparks.android.vostom.components.content.CelebrityContentList
import kangparks.android.vostom.viewModel.bottomsheet.CelebrityContentViewModel
import kangparks.android.vostom.viewModel.bottomsheet.CelebrityContentViewType

@SuppressLint("UnusedCrossfadeTargetStateParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CelebrityContentBottomSheet(
    bottomSheetScaffoldState : BottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
    celebrityContentViewModel : CelebrityContentViewModel
) {
    val currentView = celebrityContentViewModel.currentView.observeAsState(initial = CelebrityContentViewType.CelebrityContentList)
    val currentCelebrity = celebrityContentViewModel.currentCelebrity.observeAsState(initial = null)
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
                    visible = currentView.value.viewType == CelebrityContentViewType.CelebrityContentList.viewType,
                    enter = fadeIn(),
                    exit = fadeOut()
                ){
                    CelebrityContentList(
                        celebrityContentViewModel = celebrityContentViewModel
                    )
                }
                AnimatedVisibility(
                    visible = (currentView.value.viewType == CelebrityContentViewType.CelebrityContentDetail.viewType)&&(currentCelebrity.value != null),
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    CelebrityContentDetail(
                        celebrityContentViewModel = celebrityContentViewModel,
                    )
                }
            }
        },
        sheetPeekHeight = 120.dp,
        sheetSwipeEnabled = true
    ) {}


}