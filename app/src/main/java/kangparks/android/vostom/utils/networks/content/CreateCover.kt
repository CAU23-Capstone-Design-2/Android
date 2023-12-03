package kangparks.android.vostom.utils.networks.content

import android.util.Log
import com.google.gson.annotations.SerializedName
import kangparks.android.vostom.utils.api.ContentService
import kangparks.android.vostom.utils.builder.createApiService

data class CreateCoverBody(
    @SerializedName("imgUrl")
    val imgUrl: String,

    @SerializedName("title")
    val title: String
)

suspend fun createCover(
    token : String,
    imgUrl : String,
    title : String,
    url : String,
):Boolean{
    val contentService: ContentService = createApiService()
    Log.d("NETWORK-createCover", "NETWORK-createCover 시작")
    return try{
        val response = contentService.createCover(
            accessToken = token,
            body = CreateCoverBody(imgUrl = imgUrl, title = title),
            url = url
        )
        if (response.isSuccessful) {
            val responseBody = response.body()
            Log.d("NETWORK-createCover", "$responseBody")
            true
        } else {
            // error
            Log.e("NETWORK-createCover", "$response")
            false
        }
    }catch (e : Exception){
        Log.e("NETWORK-createCover", "${e.message}")
        false
    }

}