package kangparks.android.vostom.components.appbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kangparks.android.vostom.components.button.IconAndTextButton

@Composable
fun LearningAppBar(
    backButtonAction : () -> Unit = {},
    backButtonContent : String? = null,
    modifier: Modifier = Modifier,
    color : Color = MaterialTheme.colorScheme.onSurface
){
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = modifier.height(40.dp).fillMaxWidth()
    ){
        if(backButtonContent != null){
            IconAndTextButton(
                backButtonAction = backButtonAction,
                backButtonContent = backButtonContent,
                color = color
            )
        }
    }
}