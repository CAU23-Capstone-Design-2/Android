package kangparks.android.vostom.components.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PermissionContents() {
    Column {
        Column {
            Text(
                text = "필수 접근 권한",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 30.sp,
                color = Color(0xFF8B62FF),
                modifier = Modifier.padding(bottom = 20.dp)
            )
            PermissionItem(title = "∙ 녹음", content = "사용자 목소리를 분석하기 위해서 녹음 기능이 필요합니다.")
            Spacer(modifier = Modifier.padding(vertical = 15.dp))
            PermissionItem(title = "∙ 저장공간", content = "사용자 목소리를 녹음한 파일이 저장됩니다.")
        }
        Spacer(modifier = Modifier.height(40.dp))
        Column {
            Text(
                text = "선택 접근 권한",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 30.sp,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 20.dp)
            )
            PermissionItem(title = "∙ 알람", content = "학습 완료 되었을 떄 사용자에게 알람을 전송할 수 있어요.")
        }
    }
}

@Composable
fun PermissionItem(title : String, content : String){
    Box(
        contentAlignment = Alignment.TopStart
    ){
        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = content,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 80.dp)
        )
    }
}