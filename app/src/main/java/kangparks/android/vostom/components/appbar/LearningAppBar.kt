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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LearningAppBar(
    hasBackButton : Boolean = false,
    backButtonAction : () -> Unit = {},
    backButtonContent : String = "",
    modifier: Modifier = Modifier,
){
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = modifier.height(40.dp).fillMaxWidth()
    ){
        if(hasBackButton){
            Row (
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.clickable { backButtonAction() }
            ){
                Icon(Icons.Rounded.ArrowBack, contentDescription = "back button")
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = backButtonContent, fontSize = 16.sp, fontWeight = FontWeight.Bold )
            }

        }
    }
}