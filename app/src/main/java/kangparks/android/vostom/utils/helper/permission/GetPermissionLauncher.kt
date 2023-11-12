package kangparks.android.vostom.utils.helper.permission

import android.Manifest
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import kangparks.android.vostom.navigations.LearningContent

@Composable
fun getPermissionLauncher(context : Context, navController: NavHostController): ManagedActivityResultLauncher<String, Boolean> {
    val requestBluetoothPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {
            if(it){
                navController.navigate(LearningContent.GuideScript.route){
                    navController.popBackStack()
                }
            }else{
//                Toast.makeText(context, " 알람 권한이 거부되었습니다.\n설정에서 알람을 킬 수 있습니다.", Toast.LENGTH_SHORT).show()
                if(getResultOfCurrentPermissions(context)){
                    Toast.makeText(context, "필수 권한이 설정 되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    )

    val requestNotificationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {
            if(it){
//                requestBluetoothPermissionLauncher.launch(Manifest.permission.BLUETOOTH_CONNECT)
            }else{
                Toast.makeText(context, " 알람 권한이 거부되었습니다.\n설정에서 알람을 킬 수 있습니다.", Toast.LENGTH_SHORT).show()
                if(getResultOfCurrentPermissions(context)){
                    Toast.makeText(context, "필수 권한이 설정 되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }
            navController.navigate(LearningContent.GuideScript.route){
                navController.popBackStack()
            }
        }
    )

    val requestAudioRecordPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {
            if(it){
                requestNotificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }else{
                Toast.makeText(context, "필수 권한이 설정 되지 않았습니다.\n다시 시도해 주세요", Toast.LENGTH_LONG).show()
//                navController.navigate(Content.PermissionGuide.route){
//                    navController.popBackStack()
//                }
            }
        }
    )

    return requestAudioRecordPermissionLauncher
}