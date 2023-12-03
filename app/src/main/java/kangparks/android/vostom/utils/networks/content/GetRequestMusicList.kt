package kangparks.android.vostom.utils.networks.content

import android.util.Log
import kangparks.android.vostom.models.content.Music
import kangparks.android.vostom.utils.api.ContentService
import kangparks.android.vostom.utils.builder.createApiService

suspend fun getRequestMusicList(
    token : String
):List<Music>{
    val contentService: ContentService = createApiService()

    return try{
        val response = contentService.getRequestMusicList(token)

        if(response.isSuccessful){
            val responseBody = response.body()!!.data
            Log.d("Network-getRequestMusicList", "$responseBody")
            responseBody
        }else{
            Log.d("Network-getRequestMusicList", "$response")
            listOf<Music>()
        }
    }catch (e : Exception){
        Log.d("Network-getRequestMusicList", "${e.message}")
        listOf<Music>()
    }
}