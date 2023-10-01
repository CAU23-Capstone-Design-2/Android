package kangparks.android.vostom.components.buttonsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kangparks.android.vostom.components.item.CoverItem
import kangparks.android.vostom.models.item.CoverItemMetaData

// TODO 아직 공사중... 어떻게 활용 할지 고민 후 적용 예정
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(){
    BottomSheetScaffold(sheetContent = {
            Column(modifier = Modifier.fillMaxWidth().height(600.dp).padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Top) {
                Text(text = "다른 사람의 노래 Cover 살펴보기", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                CoverItem(
                    CoverItemMetaData(
                        imageUri = "https://image.ytn.co.kr/general/jpg/2013/0910/201309101636387229_h.jpg",
                        description = "박명수의 AI 커버"
                    )
                )
            }
        },
        sheetPeekHeight = 120.dp,
    ){}
}