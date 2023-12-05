package kangparks.android.vostom.utils.networks.content

import android.util.Log
import kangparks.android.vostom.models.VostomResponse
import kangparks.android.vostom.utils.api.ContentService
import kangparks.android.vostom.utils.helper.builder.createApiService
import okhttp3.ResponseBody
import retrofit2.Response

suspend fun deleteCoverSong(
    token : String,
    musicId : Int
):Boolean{
    val apiService : ContentService = createApiService()

    return try {
        val response : Response<VostomResponse<ResponseBody>> = apiService.deleteCover(
            accessToken = token,
            id = musicId
        )
        if(response.isSuccessful){
            Log.d("NETWORK-deleteCoverSong", "deleteCoverSong - success : $response")
            true
        }else{
            Log.e("NETWORK-deleteCoverSong", "deleteCoverSong - server error: $response")
            false
        }

    }catch (e : Exception){
        Log.e("NETWORK-deleteCoverSong", "deleteCoverSong - unknown error: ${e.message}")
        false
    }
}