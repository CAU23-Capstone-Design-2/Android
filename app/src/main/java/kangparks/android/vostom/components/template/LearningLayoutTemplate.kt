package kangparks.android.vostom.components.template

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kangparks.android.vostom.components.appbar.LearningAppBar
import kangparks.android.vostom.components.button.RoundedButton
import kangparks.android.vostom.components.content.MainContent
import kangparks.android.vostom.components.content.SubContent

@Composable
fun LearningLayoutTemplate(
    hasBackButton : Boolean = false,
    backButtonContent : String = "",
    backButtonAction : () -> Unit = {},
    hasMainContent : Boolean = false,
    mainContent : String = "",
    hasSubContent : Boolean = false,
    subContent : String = "",
    hasNextButton : Boolean = false,
    nextButtonContent : String = "",
    nextButtonAction : () -> Unit = {},
    nextButtonBottomPaddingValue : Int = 0,
    content : @Composable () -> Unit
){

    val isDarkTheme = isSystemInDarkTheme()
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = !isDarkTheme
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(horizontal = 20.dp, vertical = 20.dp)
            .padding(bottom = 58.dp),
    ){
        LearningAppBar(hasBackButton, backButtonAction, backButtonContent)
        Spacer(modifier = Modifier.height(30.dp))
        if(hasMainContent){
            MainContent(mainContent)
            Spacer(modifier = Modifier.height(20.dp))
        }
        if(hasSubContent){
            SubContent(subContent)
            Spacer(modifier = Modifier.height(20.dp))
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ){
            Box (modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
                contentAlignment = Alignment.TopCenter){
                content()
            }
            if(hasNextButton){
                Box (modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight().padding(bottom = nextButtonBottomPaddingValue.dp),
                    contentAlignment = Alignment.BottomCenter){
                    RoundedButton(
                        text = nextButtonContent,
                        onClick = nextButtonAction
                    )
                }
            }
        }
    }
}