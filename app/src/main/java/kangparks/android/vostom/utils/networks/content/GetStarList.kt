package kangparks.android.vostom.utils.networks.content

import android.util.Log
import kangparks.android.vostom.utils.api.ContentService
import kangparks.android.vostom.utils.builder.createApiService

suspend fun getStarList(
    accessToken: String,
){
    val contentService: ContentService = createApiService()

    val response = contentService.getStarList(accessToken)
    if (response.isSuccessful) {
        val starList = response.body()
        Log.d("NETWORK-getStarList", "$starList")
    } else {
        // error
        Log.e("NETWORK-getStarList", "$response")
    }
}