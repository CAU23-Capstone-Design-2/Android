package kangparks.android.vostom.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kangparks.android.vostom.components.appbar.ContentAppBar
import kangparks.android.vostom.components.button.RoundedButton
import kangparks.android.vostom.components.searchbar.SearchBar
import kangparks.android.vostom.viewModel.content.ContentStoreViewModel


@Composable
fun EditProfileScreen(
    navController : NavHostController,
    userName : String,
    userImgUrl : String,
) {
    val isDarkTheme = isSystemInDarkTheme()
    val systemUiController = rememberSystemUiController()

    val editNameValue = rememberSaveable { mutableStateOf(userName) }

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = !isDarkTheme
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(horizontal = 20.dp)
            .padding(bottom = 48.dp)
    ){
        Column {
            ContentAppBar(
                backButtonAction = {
                    navController.popBackStack()
                },
                backButtonContent = "취소",
            )
            Text(
                text = "프로필 변경",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "프로필을 통해 다른 사람들에게 어떤 사람인지 알려주세요!",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "프로필 사진 변경",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            AsyncImage(
                model = userImgUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF8B62FF))
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "닉네임 변경",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(20.dp))
            SearchBar(
                value = editNameValue.value,
                onValueChange = {editNameValue.value = it},
                placeholder = "닉네임을 입력해주세요",
                onSearch = {},
                imeActions = ImeAction.Done,
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(
                    bottom = 58.dp,
                ),
            contentAlignment = Alignment.BottomCenter
        ){
            RoundedButton(
                text = "프로필 수정하기",
                onClick = {

                }

            )
        }
    }
}