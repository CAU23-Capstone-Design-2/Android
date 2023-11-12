package kangparks.android.vostom.components.appbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kangparks.android.vostom.R
import kangparks.android.vostom.components.button.IconAndTextButton

@Composable
fun ContentAppBar(
    backButtonAction : ()->Unit = {},
    backButtonContent : String? = null,
    sideButtonAction : ()->Unit = {},
    sideButtonContent : String? = null,
    contentTitle : String? = null,
    contentTitleImage : Int? = null,
    containerModifier : Modifier = Modifier,
    color : Color = MaterialTheme.colorScheme.onSurface,
){

    Column{
    Row(
        modifier = containerModifier
            .height(60.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,

    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ){
            if(backButtonContent != null){
                IconAndTextButton(
                    backButtonAction = backButtonAction,
                    backButtonContent = backButtonContent,
                    color = color
                )
            }

            if(contentTitle != null){
                Text(
                    text = contentTitle,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
            }
            if(contentTitleImage != null){
                Image(
                    painter = painterResource(id = contentTitleImage),
                    contentDescription = "",
                    modifier = Modifier
                        .height(20.dp),
                    contentScale = ContentScale.FillHeight
                )
            }
        }



        if(sideButtonContent != null){
            Text(
                text = sideButtonContent,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = color,
                modifier = Modifier.clickable {
                    sideButtonAction()
                }
            )
//                modifier = Modifier.weight(1f)

        }
    }
//    Spacer(modifier = Modifier.height(10.dp))

    }
}