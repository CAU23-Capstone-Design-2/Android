package kangparks.android.vostom.screens.auth

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kakao.sdk.common.model.ApplicationContextInfo
import kangparks.android.vostom.BuildConfig
import kangparks.android.vostom.components.background.VideoBackground
import kangparks.android.vostom.components.button.RoundedButton
import kangparks.android.vostom.utils.helper.auth.withKakaoLogin


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navHostController: NavHostController){
    val context = LocalContext.current

    val kakaoAppKey = BuildConfig.kakao_api_key

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    )
    {
        Box {
            VideoBackground()
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.displayCutout)
                    .padding(horizontal = 20.dp).padding(bottom = 48.dp)
                ,
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ){
                    Image(
                        painter = painterResource(id = kangparks.android.vostom.R.drawable.screen_title),
                        contentDescription = "vostom title",
                        modifier = Modifier
                            .height(36.dp),
                        contentScale = ContentScale.FillHeight,
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Image(
                        painter = painterResource(id = kangparks.android.vostom.R.drawable.screen_subtitle),
                        contentDescription = "vostom subtitle",
                        modifier = Modifier
                            .height(16.dp),
                        contentScale = ContentScale.FillHeight,
                    )
                    Spacer(modifier = Modifier.height(300.dp))
                }
                Column(modifier = Modifier.padding(bottom = 20.dp)
                    .fillMaxSize(),verticalArrangement = Arrangement.Bottom){
                    RoundedButton(
                        text = "Vostom 시작하기",
                        onClick = { withKakaoLogin(
                            appKey = kakaoAppKey,
                            context = context,
                            navHostController = navHostController
                        )}
                    )
                }
            }

        }

    }
}