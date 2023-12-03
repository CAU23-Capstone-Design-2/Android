package kangparks.android.vostom.utils.networks.content

import android.util.Log
import kangparks.android.vostom.models.content.Music
import kangparks.android.vostom.utils.api.ContentService
import kangparks.android.vostom.utils.builder.createApiService

suspend fun getUserCoverItems(
    accessToken: String,
):List<Music>{
    val contentService: ContentService = createApiService()

    return try{
        val response = contentService.getUserMusicList(accessToken)
        if (response.isSuccessful) {
            if(response.body() != null){
                val userCoverItems = response.body()!!.data
                Log.d("NETWORK-getUserCoverItems", "getUserCoverItems-success :$userCoverItems")
                userCoverItems
            }
            else{
                Log.e("NETWORK-getUserCoverItems", "$response")
                listOf<Music>()
            }
        } else {
            // error
            Log.e("NETWORK-getUserCoverItems", "$response")
            listOf<Music>()
        }
    }catch (e : Exception){
        Log.e("NETWORK-getUserCoverItems", "${e.message}")
        listOf<Music>()
    }
}