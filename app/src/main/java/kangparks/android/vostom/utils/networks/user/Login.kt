package kangparks.android.vostom.utils.networks.user

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavHostController
import kangparks.android.vostom.models.user.TokenResponse
import kangparks.android.vostom.navigations.Nav
import kangparks.android.vostom.utils.api.UserService
import kangparks.android.vostom.utils.builder.createApiService
import kangparks.android.vostom.utils.store.saveAccessToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response

data class KakaoLoginResponse(
    val isSuccess : Boolean,
    val token: String?,
    val errorCode : Int?,
    val errorMessage : String?
)

suspend fun login(
    accessToken: String,
    context: Context,
): KakaoLoginResponse {
    val userService: UserService = createApiService()

    return try {
        val response: Response<TokenResponse> = userService.login(accessToken)
        if (response.isSuccessful) {
            val token = response.body()?.data?.accessToken
            if (token != null) {
//                saveAccessToken(context, token)
                Log.d("NETWORK-login", "success : $token")

                KakaoLoginResponse(true, token, null, null)
            } else {
                Log.e("NETWORK-login", "token is null")

                KakaoLoginResponse(false, null, 400, "token is null")
            }
        } else {
            Log.e("NETWORK-login", "server error - ${response.errorBody()}")

            KakaoLoginResponse(false, null, response.code(), response.errorBody().toString())
        }
    } catch (e: HttpException) {
        Log.e("NETWORK-login", "unknown server error - ${e.message()}")

        KakaoLoginResponse(false, null, e.code(), e.message())
    } catch (e: Exception) {
        Log.e("NETWORK-login", "unknown error - ${e.message}")

        KakaoLoginResponse(false, null, 500, e.message)
    }


}