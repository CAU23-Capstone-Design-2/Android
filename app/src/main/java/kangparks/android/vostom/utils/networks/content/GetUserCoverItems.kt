package kangparks.android.vostom.utils.networks.content

import android.util.Log
import kangparks.android.vostom.utils.api.ContentService
import kangparks.android.vostom.utils.builder.createApiService

suspend fun getUserCoverItems(
    accessToken: String,
){
    val contentService: ContentService = createApiService()

    val response = contentService.getUserCoverItems(accessToken)
    if (response.isSuccessful) {
        val userCoverItems = response.body()
        Log.d("NETWORK-getUserCoverItems", "$userCoverItems")
    } else {
        // error
        Log.e("NETWORK-getUserCoverItems", "$response")
    }
}