package kangparks.android.vostom.utils.helper.auth

import kotlinx.coroutines.*
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavHostController
import com.google.android.exoplayer2.ExoPlayer
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import kangparks.android.vostom.models.user.TokenResponse
import kangparks.android.vostom.navigations.Nav
import kangparks.android.vostom.utils.networks.user.login
import kangparks.android.vostom.utils.store.saveAccessToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.Base64

fun withKakaoLogin(
    appKey: String,
    context: Context,
    navController: NavHostController,
    exoPlayer: ExoPlayer,
    coroutineScope: CoroutineScope
) {
    KakaoSdk.init(context, appKey)

    if (UserApiClient.instance.isKakaoTalkLoginAvailable(context = context)) {
        UserApiClient.instance.loginWithKakaoTalk(context = context) { token, error ->
            if (error != null) {
                Log.e("KAKAO-AUTH-APP", "Fail login with KAKAO app : $error")
                if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                    return@loginWithKakaoTalk
                }

                UserApiClient.instance.loginWithKakaoAccount(context = context) { accountToken, accountError ->
                    if (accountError != null) {
                        Log.e("KAKAO-AUTH-ACCOUNT", "Fail get KAKAO token : $accountError")
                        errorWithKakaoLogin(context = context)
                    } else if (accountToken != null) {
                        Log.d("KAKAO-AUTH-ACCOUNT", "success : $accountToken")
                        loginWithKakaoToken(
                            token = accountToken,
                            navController = navController,
                            context = context,
                            exoPlayer = exoPlayer,
                            coroutineScope = coroutineScope
                        )
                    }
                }

            } else if (token != null) {
                Log.d("KAKAO-AUTH-ACCOUNT", "success : $token")
                loginWithKakaoToken(
                    token = token,
                    navController = navController,
                    context = context,
                    exoPlayer = exoPlayer,
                    coroutineScope = coroutineScope
                )
            } else {
                errorWithKakaoLogin(context = context)
            }
        }
    } else {
        UserApiClient.instance.loginWithKakaoAccount(context = context) { token, error ->

            if (error != null) {
                Log.e("KAKAO-AxUTH-ACCOUNT", "Fail get KAKAO token : $error")
                errorWithKakaoLogin(context = context)
            } else if (token != null) {
                Log.d("KAKAO-AUTH-ACCOUNT", "success : $token")
                loginWithKakaoToken(
                    token = token,
                    navController = navController,
                    context = context,
                    exoPlayer = exoPlayer,
                    coroutineScope = coroutineScope
                )
            } else {
                errorWithKakaoLogin(context = context)
            }
        }
    }
}

fun loginWithKakaoToken(
    token: OAuthToken,
    navController : NavHostController,
    context: Context,
    exoPlayer : ExoPlayer,
    coroutineScope: CoroutineScope
){

    CoroutineScope(Dispatchers.IO).launch {
        val result = login(
            token.accessToken,
            context = context,
        )

        if(result.isSuccess){
            coroutineScope.launch {
                saveAccessToken(context, result.token!!)
                exoPlayer.release()
                navController.navigate(route = Nav.CONTENT) {
                    navController.popBackStack()
                }
            }
        }
        else{
            coroutineScope.launch{
                Toast.makeText(
                    context,
                    "로그인 오류가 발생했습니다.\n잠시 후 다시 시도해주세요.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}

fun errorWithKakaoLogin(
    context: Context
){
    CoroutineScope(Dispatchers.Main).launch{
        Toast.makeText(context, "카카오 로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
    }
}

fun checkLoginToken(token: String) {

    val chunks: List<String> = token.split(".")
    val decoder: Base64.Decoder = Base64.getUrlDecoder()

    val payload = String(decoder.decode(chunks[1]))

    val payloadJSON: JsonObject = JsonParser.parseString(payload).asJsonObject

    val data = Gson().fromJson(payloadJSON, TokenResponse::class.java)

    Log.d("Route to CoupleSync", "route to coupleSync : $data")

//    if(data.couple != null){
//        CoroutineScope(Dispatchers.IO).launch {
////            checkExistNeedPhotoForSync(context)
//        }
////        navHostController.navigate(route = Graph.MAIN){
////            val intent = context.packageManager.getLaunchIntentForPackage(context.packageName)
////            navHostController.popBackStack()
////            intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
////            context.startActivity(intent)
////        }
//    }else{
////        Log.d("Route to CoupleSync", "route to coupleSync : $data")
////        navHostController.navigate(route = AuthScreen.CoupleSync.route+"/${data.user.code}&${data.user.name}&${data.user.gender}"){
////            popUpTo(AuthScreen.Login.route)
////        }
//    }
}


/**For Only Dev*/
fun kakaoLogout(appKey: String, context: Context) {
    KakaoSdk.init(context, appKey)
    UserApiClient.instance.logout { error ->
        if (error != null) {
            Log.e("KAKAO-AUTH_LOGOUT", "회원 로그아웃 실패 : $error")

        } else {
            Log.d("KAKAO-AUTH-LOGOUT", "회원 로그아웃")
        }
    }
}

/**For Only Dev*/
fun kakaoWithdrawal(appKey: String, context: Context) {
    KakaoSdk.init(context, appKey)
    UserApiClient.instance.unlink { error ->
        if (error != null) {
            Log.e("KAKAO-AUTH_UNLINK", "회원 탈퇴 실패 : $error")

        } else {
            Log.d("KAKAO-AUTH-UNLINK", "회원 탈퇴 성공")
        }
    }
}