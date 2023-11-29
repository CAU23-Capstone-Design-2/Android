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

suspend fun login(
    accessToken: String,
    context : Context,
    navController: NavHostController
) {
    val userService: UserService = createApiService()

    CoroutineScope(Dispatchers.Main).launch{
        try{
            val response : Response<Any> = userService.login(accessToken)
            if (response.isSuccessful) {
                Log.d("NETWORK-login", "success : ${response.body()}")
                Toast.makeText(context, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show()
//                navController.navigate(route = Nav.CONTENT){
//                    navController.popBackStack()
//                }
//                val token = response.body()?.accessToken
//                if (token != null) {
//                    saveAccessToken(context, token)
//                    Log.d("NETWORK-login", "success : $token")
//                    navController.navigate(route = Nav.CONTENT){
//                        navController.popBackStack()
//                    }
//                }else{
//                    Log.e("NETWORK-login", "token is null")
//                    Toast.makeText(context, "토큰 값이 올바르지 않습니다.", Toast.LENGTH_SHORT).show()
//                    navController.popBackStack()
//                }
            } else {
                Log.e("NETWORK-login", "server error - ${response.errorBody()}")
//                Toast.makeText(context, "서버 오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
                navController.popBackStack()
            }
        }
        catch (e : HttpException){
            Log.e("NETWORK-login", "unknown server error - ${e.message()}")
//            Toast.makeText(context, "서버 오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
            navController.popBackStack()
        }
        catch (e : Exception){
            Log.e("NETWORK-login", "unknown error - ${e.message}")
//            Toast.makeText(context, "알 수 없는 오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
            navController.popBackStack()
        }
    }


}