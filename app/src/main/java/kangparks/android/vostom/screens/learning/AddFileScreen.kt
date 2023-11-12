package kangparks.android.vostom.screens.learning

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kangparks.android.vostom.R
import kangparks.android.vostom.components.button.AddFileButton
import kangparks.android.vostom.components.item.AddFileItem
import kangparks.android.vostom.components.template.LearningLayoutTemplate
import kangparks.android.vostom.navigations.Content
import kangparks.android.vostom.utils.helper.file.copyFileToAppDir
import kangparks.android.vostom.utils.helper.file.getFileNameFromUri
import kangparks.android.vostom.viewModel.learning.AddFileViewModel
import kangparks.android.vostom.viewModel.recorder.RecordFileViewModel

@Composable
fun AddFileScreen(
    navController : NavHostController,
    recordFileViewModel : RecordFileViewModel
) {

    val addFileViewModel = remember { AddFileViewModel() }
    val sizeOfAddedRecordFiles = addFileViewModel.sizeOfAddedRecordFiles.observeAsState(initial = 0)
    val filesNameofAddedList = addFileViewModel.filesNameofAddedList.observeAsState(initial = mutableListOf())

    val context = LocalContext.current
    val pickAudioFile = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetMultipleContents(), onResult = {

        for(uri in it){
            val fileName = getFileNameFromUri(uri, context)
            val copiedFile = copyFileToAppDir(uri, fileName, context)

            addFileViewModel.addRecordFileFromDevice(copiedFile, context)
        }
    })

    BackHandler(enabled = true) { } // 뒤로 가기 방지

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ){
        LearningLayoutTemplate(
            mainContent = "학습에 도움되는 목소리 녹음 파일이 있으면 추가해 주세요!",
            backButtonContent = "빌드 11-12-11-23",
            nextButtonContent = "녹음 파일 추가 완료",
            nextButtonAction = {
                val addFileList = addFileViewModel.getRecordFileFromDeviceList()
                for (file in addFileList) {
                    recordFileViewModel.addRecordFile(file)
                }
                recordFileViewModel.testForCurrentList()
                navController.navigate(Content.GuideFinishLearning.route)
            },
            othersOptionButtonContent = "추가할 파일이 없어요!",
            othersOptionButtonAction = {
                navController.navigate(Content.GuideFinishLearning.route)
            }
        ){
            Column {
                AddFileButton(pickAudioFile = pickAudioFile)
                Spacer(modifier = Modifier.height(20.dp))

                AnimatedVisibility(
                    visible = sizeOfAddedRecordFiles.value == 0,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Text(
                        text = "추가된 녹음 파일이 없습니다.",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                AnimatedVisibility(
                    visible = sizeOfAddedRecordFiles.value != 0,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Column {
                        Text(
                            text = "추가된 녹음 파일 : ${sizeOfAddedRecordFiles.value}개",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        filesNameofAddedList.value.map {AddFileItem(fileName = it)}
                    }
                }
            }
        }
    }
}
