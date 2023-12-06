package kangparks.android.vostom.screens.profile

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kangparks.android.vostom.R
import kangparks.android.vostom.components.appbar.ContentAppBar
import kangparks.android.vostom.components.button.RoundedButton
import kangparks.android.vostom.components.searchbar.SearchBar
import kangparks.android.vostom.utils.networks.user.updateProfile
import kangparks.android.vostom.viewModel.content.ContentStoreViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun EditProfileScreen(
    navController : NavHostController,
    userName : String,
    userImgUrl : String,
    contentStoreViewModel : ContentStoreViewModel,
) {
    val context = LocalContext.current
    val isDarkTheme = isSystemInDarkTheme()
    val systemUiController = rememberSystemUiController()
    val updateImgUri = remember {
        mutableStateOf(userImgUrl)
    }

//    val editNameValue = rememberSaveable { mutableStateOf(userName) }

    val photoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
    ){ uri ->
        if(uri != null){
            updateImgUri.value = uri.toString()
        }
    }

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
            Box {
                AsyncImage(
                    model = updateImgUri.value,
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF8B62FF)),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(Color(0x8A5C5C5C))
                        .clickable {
                            photoLauncher.launch(
                                PickVisualMediaRequest(
                                    ActivityResultContracts.PickVisualMedia.ImageOnly
                                )
                            )
                        }
                    ,
                    contentAlignment = Alignment.Center
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.mdi_camera),
                        tint = Color.White,
                        contentDescription = null,
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
//            Text(
//                text = "닉네임 변경",
//                fontSize = 16.sp,
//                fontWeight = FontWeight.Bold
//            )
//            Spacer(modifier = Modifier.height(20.dp))
//            SearchBar(
//                value = editNameValue.value,
//                onValueChange = {editNameValue.value = it},
//                placeholder = "닉네임을 입력해주세요",
//                onSearch = {},
//                imeActions = ImeAction.Done,
//            )
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
                    if(updateImgUri.value == userImgUrl){
                        Toast.makeText(context, "변경할 사진을 추가해주세요.", Toast.LENGTH_SHORT).show()
                        return@RoundedButton
                    }
                    CoroutineScope(Dispatchers.IO).launch{
                        updateProfile(
                            context = context,
                            imgUri = updateImgUri.value
                        )
                    }
                    Toast.makeText(context, "프로필이 변경되었습니다.", Toast.LENGTH_SHORT).show()
                    contentStoreViewModel.updateProfileContent(context)
                    navController.popBackStack()
                }

            )
        }
    }
}