package kangparks.android.vostom.utils.networks.content

import android.util.Log
import kangparks.android.vostom.utils.api.ContentService
import kangparks.android.vostom.utils.builder.createApiService

suspend fun getStarCoverItems(
    accessToken: String,
    starId: String,
){
    val contentService: ContentService = createApiService()

    val response = contentService.getStarCoverItems(accessToken, starId)
    if (response.isSuccessful) {
        val starCoverItems = response.body()
        Log.d("NETWORK-getStarCoverItems", "$starCoverItems")
    } else {
        // error
        Log.e("NETWORK-getStarCoverItems", "$response")
    }
}