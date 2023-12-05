package kangparks.android.vostom.utils.networks.content

import android.util.Log
import kangparks.android.vostom.models.content.Comment
import kangparks.android.vostom.utils.api.ContentService
import kangparks.android.vostom.utils.helper.builder.createApiService

suspend fun getCommentData(
    token : String,
    coverId : String
) {
    val contentService: ContentService = createApiService()

    val response = contentService.getComments(
        accessToken =  token,
        coverId = coverId
    )

    if (response.isSuccessful) {
        val commentList = response.body()
        Log.d("NETWORK-getCommentData", "$commentList")

    } else {
        // error
        Log.e("NETWORK-getCommentData", "$response")
    }
}