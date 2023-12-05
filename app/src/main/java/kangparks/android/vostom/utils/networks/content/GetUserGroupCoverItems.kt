package kangparks.android.vostom.utils.networks.content

import android.util.Log
import kangparks.android.vostom.models.content.Music
import kangparks.android.vostom.utils.api.ContentService
import kangparks.android.vostom.utils.helper.builder.createApiService

suspend fun getUserGroupCoverItems(
    accessToken: String
):List<Music>{
    val contentService: ContentService = createApiService()

    return try{
        val response = contentService.getUserTeamMusic(accessToken)
        if (response.isSuccessful) {
            if(response.body() != null){

                val userCoverItems = response.body()!!.data
                Log.d("NETWORK-getUserGroupCoverItems", "getUserGroupCoverItems-success :$response ${response.body()}")
                userCoverItems
            }
            else{
                Log.e("NETWORK-getUserGroupCoverItems", "$response")
                listOf<Music>()
            }
        } else {
            // error
            Log.e("NETWORK-getUserGroupCoverItems", "$response")
            listOf<Music>()
        }
    }catch (e : Exception){
        Log.e("NETWORK-getUserGroupCoverItems", "${e.message}")
        listOf<Music>()
    }
}