package kangparks.android.vostom.utils.helper.auth

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavHostController
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import kangparks.android.vostom.utils.networks.user.login
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Base64

fun withKakaoLogin(
    appKey: String,
    context: Context,
    navHostController: NavHostController
){
    KakaoSdk.init(context, appKey)

    // 카카오톡 앱 설치 되어 있을 때
    if(UserApiClient.instance.isKakaoTalkLoginAvailable(context = context)){
//        Log.d("KAKAO_AUTH", "카카오톡 로그인 진입")

        //kakao talk login
        UserApiClient.instance.loginWithKakaoTalk(context = context){ token, error->
            if (error != null){
                // fail kakao talk login
                Log.e("KAKAO-AUTH-APP", "Fail login with KAKAO app : $error")
                if(error is ClientError && error.reason == ClientErrorCause.Cancelled){
                    return@loginWithKakaoTalk
                }

                // kakao account login
                UserApiClient.instance.loginWithKakaoAccount(context = context){ accountToken, accountError->
                    if(accountError != null){
                        Log.e("KAKAO-AUTH-ACCOUNT", "Fail get KAKAO token : $accountError")
                        CoroutineScope(Dispatchers.Main).launch{
                            Toast.makeText(context, "카카오 로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
                        }

                        navHostController.popBackStack()
                    }else if(accountToken != null){
                        Log.d("KAKAO-AUTH-ACCOUNT", "success : $accountToken")
                        CoroutineScope(Dispatchers.IO).launch {
                            login(
                                accountToken.accessToken,
                                context = context,
                                navController = navHostController
                            )
                        }
                    }
                }

            }else if(token != null){
                Log.d("KAKAO-AUTH-ACCOUNT", "success : $token")
                CoroutineScope(Dispatchers.IO).launch {
                    login(
                        token.accessToken,
                        context = context,
                        navController = navHostController
                    )
                }
            }else{
                CoroutineScope(Dispatchers.Main).launch{
                    Toast.makeText(context, "카카오 로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
                }

                navHostController.popBackStack()
            }
        }
    }
    // 카카오톡 앱 설치 안되어 있을 때
    else{
//        Log.d("KAKAO_AUTH", "카카오계정 로그인 진입")
        UserApiClient.instance.loginWithKakaoAccount(context = context){ token, error->
//            Log.d("KAKAO_AUTH", "카카오계정 로그인 시도")
            if(error != null){
                Log.e("KAKAO-AxUTH-ACCOUNT", "Fail get KAKAO token : $error")
                CoroutineScope(Dispatchers.Main).launch{
                    Toast.makeText(context, "카카오 로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
                }

                navHostController.popBackStack()
            }else if(token != null){
                Log.d("KAKAO-AUTH-ACCOUNT", "success : $token")
                CoroutineScope(Dispatchers.IO).launch {
                    login(
                        token.accessToken,
                        context = context,
                        navController = navHostController
                    )
                }
            }else{
                CoroutineScope(Dispatchers.Main).launch{
                    Toast.makeText(context, "카카오 로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
                }

                navHostController.popBackStack()
            }
        }
    }
}

fun sendKakaoTokenToServer(token: OAuthToken, context: Context, navHostController: NavHostController){
    CoroutineScope(Dispatchers.Main).launch {
//        val response = sendTokenForLogin(token.accessToken)
//        if(response.isSuccessful){
//            response.body()?.token?.let {
//                checkLoginToken(context, it, navHostController)
//            }
//        }else{
//            Log.e("KAKAO-AUTH-sendKakaoTokenToServer","${response.errorBody()}")
//        }
    }
}

fun checkLoginToken(context : Context, token : String, navHostController: NavHostController){
//    saveToken(context = context, token)
    val chunks: List<String> = token.split(".")
    val decoder: Base64.Decoder = Base64.getUrlDecoder()

    val payload = String(decoder.decode(chunks[1]))

    val payloadJSON : JsonObject = JsonParser.parseString(payload).asJsonObject

//    val data = Gson().fromJson(payloadJSON, LoginPayload::class.java)

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
fun kakaoLogout(appKey : String, context: Context){
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
fun kakaoWithdrawal(appKey : String, context: Context){
    KakaoSdk.init(context, appKey)
    UserApiClient.instance.unlink { error->
        if (error != null) {
            Log.e("KAKAO-AUTH_UNLINK", "회원 탈퇴 실패 : $error")

        } else {
            Log.d("KAKAO-AUTH-UNLINK", "회원 탈퇴 성공")
        }
    }
}