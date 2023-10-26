package kangparks.android.vostom.components.player

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import com.google.accompanist.web.AccompanistWebChromeClient
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@Composable
fun YoutubePlayer(
    contentId : String,
    lifecycleOwner: LifecycleOwner
){
    val webViewClient = AccompanistWebViewClient()
    val webChromeClient = AccompanistWebChromeClient()

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    val webViewState =
        rememberWebViewState(
            url = "youtube.com$contentId",
            additionalHttpHeaders = emptyMap()
        )

    Box(
        modifier = Modifier.fillMaxWidth()
            .height(600.dp),
        contentAlignment =  Alignment.TopCenter,
    ){
        WebView(
            state = webViewState,
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp),
            client = webViewClient,
            chromeClient = webChromeClient,
            onCreated = { webView ->
                with(webView) {
                    settings.run {
                        javaScriptEnabled = true
                        domStorageEnabled = true
                        javaScriptCanOpenWindowsAutomatically = false
                    }
                }
            }
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(color = Color(0xFF2F2F2F))
        )
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment =  Alignment.BottomCenter,
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((600.dp-(screenWidth/10*7))+10.dp)
                    .background(color = Color(0xFF2F2F2F))
            )
        }
    }
}