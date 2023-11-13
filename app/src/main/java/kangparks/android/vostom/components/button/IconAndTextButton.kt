package kangparks.android.vostom.components.button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun IconAndTextButton(
    backButtonAction : (()->Unit)? = null,
    backButtonContent : String,
    imageVector: ImageVector = Icons.Rounded.ArrowBack,
    containerModifier : Modifier = Modifier,
    color : Color = MaterialTheme.colorScheme.onSurface
){
    Row (
        horizontalArrangement = Arrangement.Center,
        modifier = containerModifier.clip(RoundedCornerShape(10.dp)).clickable {
            if (backButtonAction != null) {
                backButtonAction()
            }
        }.padding(vertical = 5.dp)
    ){
        Icon(
            imageVector,
            contentDescription = "back button",
            tint = color
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = backButtonContent,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = color,
        )
    }
}