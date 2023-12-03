package kangparks.android.vostom.utils.networks.content

import android.util.Log
import kangparks.android.vostom.models.content.Music
import kangparks.android.vostom.utils.api.ContentService
import kangparks.android.vostom.utils.helper.builder.createApiService

suspend fun getUserLikedCoverItems(
    accessToken: String
):List<Music>{
    val contentService: ContentService = createApiService()

    return try{
        val response = contentService.getUserLikedMusic(accessToken)
        if (response.isSuccessful) {
            if(response.body() != null){
                val userCoverItems = response.body()!!.data
                Log.d("NETWORK-getUserLikedCoverItems", "getUserLikedCoverItems-success :$userCoverItems")
                userCoverItems
            }
            else{
                Log.e("NETWORK-getUserLikedCoverItems", "$response")
                listOf<Music>()
            }
        } else {
            // error
            Log.e("NETWORK-getUserLikedCoverItems", "$response")
            listOf<Music>()
        }
    }catch (e : Exception){
        Log.e("NETWORK-getUserLikedCoverItems", "${e.message}")
        listOf<Music>()
    }
}