package kangparks.android.vostom.screens.group

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kangparks.android.vostom.R
import kangparks.android.vostom.components.appbar.ContentAppBar
import kangparks.android.vostom.components.button.RoundedButton
import kangparks.android.vostom.components.searchbar.SearchBar
import kangparks.android.vostom.viewModel.content.ContentStoreViewModel
import kangparks.android.vostom.viewModel.group.CurrentGroupViewModel
import kangparks.android.vostom.viewModel.group.GroupInfoVIewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EditGroupScreen(
    navController: NavHostController,
    contentStoreViewModel : ContentStoreViewModel,
    currentGroupViewModel : CurrentGroupViewModel,
    groupInfoVIewModel: GroupInfoVIewModel = viewModel()
) {
    val context = LocalContext.current
    val isDarkTheme = isSystemInDarkTheme()
    val systemUiController = rememberSystemUiController()

    val keyboardController = LocalSoftwareKeyboardController.current

    val userId = contentStoreViewModel.userId.observeAsState(-1)
    val curGroup = currentGroupViewModel.currentGroup.observeAsState(null)
    val curImgUri = groupInfoVIewModel.currentImgUri.observeAsState(null)

//    var painter = rememberAsyncImagePainter(
//        curGroup.value!!.groupImgUri
//    )

    var imgUri = remember {
        mutableStateOf(curGroup.value!!.groupImgUri)
    }

    val photoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
    ){ uri ->
        CoroutineScope(Dispatchers.IO).launch{
//            addPhotoFromGallery(uri, context)
            Log.d("BuildGroupScreen", "uri : $uri")
            if(uri != null) {
                groupInfoVIewModel.setCurrentImgUri(uri)
                imgUri.value = uri.toString()
            }
        }
    }

    val groupTitle = rememberSaveable {
        mutableStateOf(curGroup.value!!.title)
    }

    val groupDescription = rememberSaveable {
        mutableStateOf(curGroup.value!!.description)
    }

    LaunchedEffect(null){
        groupInfoVIewModel.selectGroup(curGroup.value!!)
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
    ) {
        Column {
            ContentAppBar(
                backButtonAction = {
                    navController.popBackStack()
                },
                backButtonContent = "취소",
            )
            Text(
                text = "그룹 편집",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "그룹 대표 사진",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Box(){
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(10.dp))
                ){
                    if(imgUri.value != null){
                        AsyncImage(
                            model = imgUri.value,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }else{
                        Box(modifier = Modifier.background(Color(0xFF9C9C9C)))
                    }
                }
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(10.dp))
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
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "그룹 이름",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            SearchBar(
                value = groupTitle.value,
                onValueChange = {
                    groupTitle.value = it
                },
                placeholder = "그룹 이름을 입력해 주세요.",
                onSearch = {
                    keyboardController?.hide()
                }
            )
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "그룹 설명",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            SearchBar(
                value = groupDescription.value,
                onValueChange = {
                    groupDescription.value = it
                },
                placeholder = "그룹 설명을 입력해 주세요.",
                onSearch = {
                    keyboardController?.hide()
                }
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
                text = "그룹 편집 완료",
                onClick = {
                    if(groupInfoVIewModel.currentImgUri.value == null){
                        Toast.makeText(context, "그룹 대표 사진을 선택해 주세요.", Toast.LENGTH_SHORT).show()
                        return@RoundedButton
                    }
                    if(groupTitle.value.isEmpty()){
                        Toast.makeText(context, "그룹 이름을 입력해 주세요.", Toast.LENGTH_SHORT).show()
                        return@RoundedButton
                    }
                    if(groupDescription.value.isEmpty()){
                        Toast.makeText(context, "그룹 설명을 입력해 주세요.", Toast.LENGTH_SHORT).show()
                        return@RoundedButton
                    }
                    groupInfoVIewModel.updateGroupWithInfo(
                        context = context,
                        id = userId.value,
                        name = groupTitle.value,
                        description = groupDescription.value,
                    )
                    navController.popBackStack()
                }

            )
        }

    }
}