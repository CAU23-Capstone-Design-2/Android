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
                    Text(text = " 어느 날 아버지와 아들이 당나귀를 시장에 내다 팔기 위해 시골길을 따라 몰고 가고 있었습니다. 그들은 곧 우물에서 물을 긷고 있던 처녀들 곁을 지나가게 되었습니다.\n" +
                            "\"저것 좀 봐\"하고 처녀들 중 하나가 말했습니다.\n" +
                            "\"당나귀는 편히 걷도록 두고 먼지 속을 터벅터벅 걷고 있는 저 어리석은 사람들 좀 봐\"\n" +
                            " 아버지는 그들이 한 말을 듣고는 아들을 당나귀에 태웠습니다. 그들은 채 멀리 가기도 전에 몇 명의 노인들과 마주쳤습니다.\n" +
                            "\"이것 좀 보란 말이야\"\n" +
                            " 그 중의 한 노인이 다른 노인들에게 말했습니다.\n" +
                            "\"이것만 봐도 내가 말한 것이 사실이라는 걸 알 테지. 요즘 젊은이들은 노인들을 조금도 소중하게 여기지 않아. 아버지는 불쌍하게도 곁에서 걷고 있는데 아들 녀석은 당나귀를 타고 가는 것 좀 보라구\"\n" +
                            " 이 말을 들은 아버지는 아들을 당나귀에서 내리도록 한 다음 자신이 당나귀에 올라탔습니다. 얼마 후 그들은 어린아이를 품에 안은 부인네 셋을 만났습니다.\n" +
                            "\"아이, 망측해라!\"하고 여자들은 말했습니다.\n" +
                            "\"당신은 어쩌면 저 가엾은 소년이 매우 지친 것같이 보이는데도 걷도록 하고 혼자서만 왕처럼 당당하게 당나귀를 타고 갈 수 있죠?\"\n" +
                            "그러자 아버지는 아들을 안장에 태우고는 마을을 향해 계속 타고 갔습니다. 거기에 도착하기 직전에 젊은이 몇이 그들을 불러 세우고는 말했습니다.\n" +
                            "\"그 당나귀는 당신들 것입니까?\" \"그렇소\"하고 아버지가 말했습니다.\n" +
                            "\"당신들이 당나귀를 타고 가는 것으로 미루어 보면 누구도 그렇다고 생각지 않을 것이오. 당나귀가 당신들을 태우고 가는 것보다 당신들이 당나귀를 들고 가는 것이 더 어울리는 것 같소\"하고 그들이 말했습니다.\n" +
                            " 그래서 아버지와 아들은 당나귀에서 내려 당나귀 다리를 밧줄로 단단히 묶은 다음 장대에 붙잡아 매달았습니다. 그리고 자기 장대의 한쪽 끝을\n" +
                            "잡고 당나귀를 매고 가는데 만나는 사람마다 큰소리로 웃는 것이었습니다.\n" +
                            " 이윽고 그들은 어떤 다리에 다다랐습니다. 그러자 당나귀는 발길질을 하기 시작해서 밧줄을 끊고 강속으로 떨어져 빠져 죽고 말았습니다.\n" +
                            "늙은 아버지는 할 수 없이 아들을 데리고 집으로 돌아오며 마음속으로 생각했습니다.\n" +
                            " '모든 사람의 비위를 맞추려 하다가는 결국 누구의 비위도 맞추지 못한다'\n\n" + "소설 \'부자와 당나귀\'\n\n")
                }
            }
        }
    }
}