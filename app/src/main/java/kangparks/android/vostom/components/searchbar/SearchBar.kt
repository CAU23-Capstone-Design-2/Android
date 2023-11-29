package kangparks.android.vostom.components.searchbar

import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.TextField
import androidx.compose.ui.text.input.ImeAction

@Composable
fun SearchBar(
    value : String,
    onValueChange : (String) -> Unit,
    placeholder : String,
    onSearch : (KeyboardActionScope.() -> Unit)?,
    imeActions: ImeAction = ImeAction.Search,
    keyboardActions: KeyboardActions = KeyboardActions(
        onSearch = onSearch,
    ) ,
) {
    val isDarkTheme = isSystemInDarkTheme()

    Column {
        TextField(
            placeholder = {
                Text(
                    text = placeholder,
                    fontSize = 14.sp,
                    color = Color(0xFFAFAFAF),
                    modifier = Modifier
                        .fillMaxWidth()
                )
            },
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            maxLines = 1,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = imeActions
            ),
            keyboardActions = keyboardActions ,
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.colors(
                cursorColor = Color.Black,
                focusedLabelColor = Color.Transparent,
                unfocusedLabelColor = Color.Transparent,
                disabledLabelColor = Color.Transparent,
                errorLabelColor = Color.Transparent,
                focusedContainerColor = if(isDarkTheme) Color(0xFF747474) else Color(0xFFEEEEEE),
                unfocusedContainerColor = if(isDarkTheme) Color(0xFF747474) else Color(0xFFEEEEEE),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            modifier = Modifier
                .border(0.dp, Color.Transparent)
                .fillMaxWidth()
                .height(52.dp)
        )
    }
}