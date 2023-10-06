package kangparks.android.vostom.screens.learning

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import kangparks.android.vostom.Greeting
import kangparks.android.vostom.components.template.LearningLayoutTemplate
import kangparks.android.vostom.navigations.Content

@Composable
fun LearningScriptScreen(navController : NavHostController){

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LearningLayoutTemplate(
            hasBackButton = true,
            backButtonContent = "이전 단계로",
            backButtonAction = { navController.popBackStack()},
            hasMainContent = true,
            mainContent = "다음 제공되는 스크립트를 따라 읽어보세요.",
            hasNextButton = true,
            nextButtonContent = "스크립트 녹음하기",
            nextButtonAction = { navController.navigate(Content.LearningFirstSing.route) } // 임시 이동
        ){
            Column(modifier = Modifier
                .height(500.dp)
                .width(500.dp))
            {
                Column(
                    modifier = Modifier.fillMaxSize()
                        .verticalScroll(rememberScrollState())
                )
                {
                    Text(text = " 안녕하세요? 저희는 이 과정을 통해 학습 정확도를 향상시키고 목소리 합성에 이를 활용하고자 합니다.\n" +
                            "각각의 스크립트에 맞게 다양한 감정을 담아 읽어주세요!\n" +
                            "   \"오늘은 해가 환하게 빛나며, 신선한 공기가 미소를 지어보이게 합니다. 새로운 아침이 시작되었고, 삶은 무한한 기쁨과 희망으로 가득 차있습니다. 이 순간을 최대한 느끼며, 우리는 행복한 삶을 만들어 나갈 수 있습니다.\"\n" +
                            "\n" +
                            "   \"때로는 눈물이 흐를 때가 있죠. 삶은 어려움과 상실의 연속이지만, 그 과정에서 우리는 더 강해지고 깊이 성장할 수 있습니다. 슬픔은 우리의 감정의 한 부분이며, 그 속에서 우리는 강한 내면을 발견할 수 있습니다.\"\n" +
                            "\n" +
                            "   \"이 순간의 아름다움에 감동을 느껴봅시다. 자연의 풍경, 사람들 간의 연결, 그리고 모든 삶의 순간들은 감사함과 사랑으로 가득합니다. 우리는 이 감동을 느끼며 삶을 즐길 수 있습니다.\"\n" +
                            "\n" +
                            "   \"내일은 우리에게 새로운 기회와 도전을 제공할 것입니다. 자신감을 키워보세요. 우리는 자기 믿음을 갖고 자신의 능력을 믿으면 어떤 어려움도 극복할 수 있습니다. 당신은 훌륭한 사람입니다.\"\n" +
                            "\n" +
                            "   \"우주의 무한한 신비로움을 느껴봅시다. 밤하늘의 별들, 자연의 흐름, 그리고 인간의 창의력은 정말 경이로운 것이에요. 이 작은 행성 위에서 우리가 살아가는 것은 정말로 놀라운 일입니다.\"\n" +
                            "\n" +
                            "수고하셨습니다! 이제 마지막 과정만 남았습니다.\n 마지막 Step으로 넘어갈게요!\n\n")
                }
            }
        }
    }
}