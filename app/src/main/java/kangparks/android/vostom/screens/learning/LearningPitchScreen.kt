//package kangparks.android.vostom.screens.learning
//
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material3.Button
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.core.net.toUri
//import androidx.navigation.NavHostController
//import kangparks.android.vostom.components.canvas.VoiceMeasureLine
//import kangparks.android.vostom.components.player.MusicPlayer
//import kangparks.android.vostom.components.template.LearningLayoutTemplate
//import kangparks.android.vostom.navigations.Content
//import kangparks.android.vostom.navigations.LearningContent
//import kangparks.android.vostom.viewModel.player.AudioPlayerViewModel
//import kangparks.android.vostom.viewModel.recorder.AudioRecorderViewModel
//
//@Composable
//fun LearningPitchScreen(navController : NavHostController){
//    val context = LocalContext.current
//    val recorderViewModel = AudioRecorderViewModel(context, context.filesDir)
//    val playerViewModel = AudioPlayerViewModel(context, null)
//
//    Surface(
//        modifier = Modifier.fillMaxSize(),
//        color = MaterialTheme.colorScheme.background
//    ) {
//        LearningLayoutTemplate(
//            backButtonContent = "처음으로",
//            backButtonAction = { navController.popBackStack()},
//            mainContent = "예시처럼 내 목소리의 음역대를 측정해보세요.",
//            nextButtonContent = "음역대 측정하기",
//            nextButtonAction = { navController.navigate(LearningContent.LearningScript.route) } // 임시 이동
//        ){
//            MusicPlayer()
////            VoiceMeasureLine()
//            Column {
//                Button(onClick = { recorderViewModel.start(fileName = "pitch.m4a")} ) {
//                    Text("녹음 테스트")
//                }
//                Button(onClick = { recorderViewModel.stop()} ){
//                    Text("녹음 중지")
//                }
//
//                Button(onClick = {
//                    playerViewModel.setAudioFile(recorderViewModel.getAudioFile()?: return@Button)
//                    playerViewModel.start()
//                }) {
//                    Text(text = "재생 테스트")
//                }
//                Button(onClick = {
//                    playerViewModel.stop()
//                }){
//                    Text(text = "재생 중지")
//                }
//            }
//
//        }
//    }
//}