package kangparks.android.vostom.screens.permission

import android.Manifest
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import kangparks.android.vostom.components.template.LearningLayoutTemplate
import kangparks.android.vostom.navigations.Content
import kangparks.android.vostom.components.content.PermissionContents
import kangparks.android.vostom.utils.helper.permission.getPermissionLauncher
import kangparks.android.vostom.utils.helper.permission.getResultOfCurrentPermissions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

@Composable
fun PermissionGuideScreen(navController : NavHostController) {
    val context = LocalContext.current
    val permissionLauncher = getPermissionLauncher(context, navController)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LearningLayoutTemplate(
            backButtonContent = "이전",
            backButtonAction = { navController.popBackStack()},
            mainContent = "목소리 학습을 위해 다음 권한 설정이 필요합니다.",
            subContent = "허용하지 않으면 목소리 학습을 진행할 수 없어요. \uD83D\uDE22",
            nextButtonContent = "권한 설정하기",
            nextButtonAction = {
                permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
            },
        ){
            PermissionContents()
        }
    }
}