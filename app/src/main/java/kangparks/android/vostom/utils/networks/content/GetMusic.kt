package kangparks.android.vostom.utils.networks.content

import android.util.Log
import kangparks.android.vostom.models.content.Music
import kangparks.android.vostom.models.content.MusicForPlayer
import kangparks.android.vostom.utils.api.ContentService
import kangparks.android.vostom.utils.helper.builder.createApiService

suspend fun getMusic(
    accessToken: String,
    id : Int
) : MusicForPlayer? {
    val apiService : ContentService = createApiService()

    return try{
        val response = apiService.getMusic(accessToken, id)

        if(response.isSuccessful){
            Log.d("getMusic", "getMusic -success : ${response.body()?.data}")
            response.body()?.data
            if(response.body()?.data != null){
                response.body()?.data
            }
            else{
                null
            }
        }else{
            Log.e("getMusic", "getMusic -fail : ${response.errorBody()?.string()}")
            null
        }
    }catch(e : Exception){
        Log.e("getMusic", "getMusic -fail : $e")
        null
    }
}