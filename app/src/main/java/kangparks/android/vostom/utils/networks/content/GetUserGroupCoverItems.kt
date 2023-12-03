package kangparks.android.vostom.utils.networks.content

import android.util.Log
import kangparks.android.vostom.utils.api.ContentService
import kangparks.android.vostom.utils.helper.builder.createApiService

suspend fun getUserGroupCoverItems(
    accessToken: String
){
    val contentService: ContentService = createApiService()

    val response = contentService.getUserGroupCoverItems(accessToken)
    if (response.isSuccessful) {
        val userGroupCoverItems = response.body()
        Log.d("NETWORK-getUserGroupCoverItems", "$userGroupCoverItems")
    } else {
        // error
        Log.e("NETWORK-getUserGroupCoverItems", "$response")
    }
}