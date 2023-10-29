package kangparks.android.vostom.components.template

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kangparks.android.vostom.components.appbar.LearningAppBar
import kangparks.android.vostom.components.button.RoundedButton
import kangparks.android.vostom.components.content.MainContent
import kangparks.android.vostom.components.content.SubContent
import kangparks.android.vostom.components.content.SubContentWithAnnotatedString

@Composable
fun LearningLayoutTemplate(
    backButtonContent: String? = null,
    backButtonAction: () -> Unit = {},
    mainContent: String? = null,
    subContent: String? = null,
    subAnnotatedString: AnnotatedString? = null,
    nextButtonContent: String? = null,
    nextButtonAction: () -> Unit = {},
    nextButtonBottomPaddingValue: Int = 0,
    nextButtonContainerColor : Color = Color(0xFF8B62FF),
    othersOptionButtonAction : () -> Unit = {},
    othersOptionButtonContent : String? = null,
    othersOptionButtonBottomPaddingValue : Int = 0,
    contentModifier: Modifier = Modifier,
    contentAlignment : Alignment = Alignment.TopCenter,
    color : Color = MaterialTheme.colorScheme.onSurface,
    contentHorizontalPadding : Int = 20,
    content: @Composable () -> Unit,
){

    val isDarkTheme = isSystemInDarkTheme()
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = if(color == Color.White) isDarkTheme else !isDarkTheme
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars).padding(vertical = 20.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp,),
        ){
            LearningAppBar(
                backButtonAction,
                backButtonContent,
                color = color
            )
            Spacer(modifier = Modifier.height(30.dp))
            if (mainContent != null) {
                MainContent(
                    content = mainContent,
                    color = color
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
            if (subAnnotatedString != null) {
                SubContentWithAnnotatedString(subAnnotatedString)
                Spacer(modifier = Modifier.height(20.dp))
            }
            else if (subContent != null) {
                SubContent(
                    content = subContent,
                    color = color
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = contentModifier.fillMaxSize()
                    .padding(
                        horizontal = contentHorizontalPadding.dp,
                    ),
                contentAlignment = contentAlignment
            ) {
                content()
            }
            if (nextButtonContent != null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(
                            bottom = nextButtonBottomPaddingValue.dp + 58.dp,
                            start = 20.dp,
                            end = 20.dp
                        ),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ){
                        RoundedButton(
                            text = nextButtonContent,
                            onClick = nextButtonAction,
                            buttonContainerColor = nextButtonContainerColor,
                        )

                        if(othersOptionButtonContent != null){
                            Spacer(modifier = Modifier.height(20.dp))
                            Text(
                                text = othersOptionButtonContent,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                textDecoration = TextDecoration.Underline,
                                modifier = Modifier
                                    .padding(bottom = othersOptionButtonBottomPaddingValue.dp)
                                    .clickable {
                                        othersOptionButtonAction()
                                    }
                            )
                        }
                    }
                }
            }
        }
    }
}