package kangparks.android.vostom.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import kangparks.android.vostom.Greeting
import kangparks.android.vostom.navigations.Content
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun HomeScreen(navController : NavHostController){

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "다음 제공되는 스크립트를\n따라 읽어보세요!",
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Left,
            lineHeight = 25.sp
        )

        Column(
            modifier = Modifier
                .size(300.dp)
                .verticalScroll(rememberScrollState())
                .padding(30.dp)
        ) {
            repeat(100) {
                Text("Item $it", modifier = Modifier.padding(2.dp))
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(modifier = Modifier.width(300.dp).height(100.dp).padding(20.dp), onClick = { navController.navigate(Content.LearningScript.route) }) {
            Text(text = "스크립트 녹음하기", fontSize = 20.sp)
        }
    }

//    Surface(
//        modifier = Modifier.width(300.dp).height(50.dp),
//        color = MaterialTheme.colorScheme.background
//    ) {
//        Button(
//            modifier = Modifier.background(Color(0xFF800080)), onClick = { navController.navigate(Content.LearningScript.route) }
//        ) {
//            Text(text = "학습 시작")
//        }
//        Greeting("홈 화면")
//    }
}