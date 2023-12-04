package kangparks.android.vostom.utils.networks.user

import android.util.Log
import kangparks.android.vostom.models.VostomResponse
import kangparks.android.vostom.models.user.UserInfo
import kangparks.android.vostom.utils.api.UserService
import kangparks.android.vostom.utils.helper.builder.createApiService
import retrofit2.Response

suspend fun getUserInfo(
    token : String
):UserInfo{
    val apiService : UserService = createApiService()

    return try{
        val response : Response<VostomResponse<UserInfo>> = apiService.getUserInfo(accessToken = token)

        if(response.isSuccessful){
            if(response.body() != null){
                Log.d("network", "getUserInfo - success ${response.body()}")
                response.body()!!.data
            }
            else{
                Log.e("network", "getUserInfo - server error2 $response")
                UserInfo(nickname = "", profileImage = "")
            }
        }else{
            Log.e("network", "getUserInfo - server error $response")
            UserInfo(nickname = "", profileImage = "")
        }
    }catch (e : Exception){
        Log.e("network", "getUserInfo - unknown error ${e.message}")
        UserInfo(nickname = "", profileImage = "")
    }
}