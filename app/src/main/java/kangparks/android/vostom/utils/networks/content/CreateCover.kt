package kangparks.android.vostom.utils.networks.content

import android.util.Log
import kangparks.android.vostom.utils.api.ContentService
import kangparks.android.vostom.utils.builder.createApiService

suspend fun createFun(
    token : String,
    uri : String
){
    val contentService: ContentService = createApiService()

    val response = contentService.createCover(token, uri)
    if (response.isSuccessful) {
        val createCover = response.body()
        Log.d("NETWORK-createCover", "$createCover")
    } else {
        // error
        Log.e("NETWORK-createCover", "$response")
    }
}