package kangparks.android.vostom.components.searchbar

import androidx.compose.foundation.border
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

@Composable
fun SearchBar(
    value : String,
    onValueChange : (String) -> Unit,
    placeholder : String,
    onSearch : (KeyboardActionScope.() -> Unit)?
) {
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
                imeAction = androidx.compose.ui.text.input.ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = onSearch,
            ) ,
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.colors(
                cursorColor = Color.Black,
                focusedLabelColor = Color.Transparent,
                unfocusedLabelColor = Color.Transparent,
                disabledLabelColor = Color.Transparent,
                errorLabelColor = Color.Transparent,
                focusedContainerColor = Color(0xFFEEEEEE),
                unfocusedContainerColor = Color(0xFFEEEEEE),
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