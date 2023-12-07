package kangparks.android.vostom.utils.networks.content

import android.util.Log
import kangparks.android.vostom.utils.api.ContentService
import kangparks.android.vostom.utils.helper.builder.createApiService

suspend fun likeMusic(
    accessToken : String,
    musicId : Int
){
    val contentService: ContentService = createApiService()

    try{
        val response = contentService.likeMusic(
            accessToken = accessToken,
            id = musicId
        )

        if(response.isSuccessful){
            Log.d("network-likeMusic", "likeMusic : success like music")
        }
        else {
            Log.e("network-likeMusic", "likeMusic : fail like music")
        }
    }catch (e : Exception){
        Log.d("network-likeMusic", "likeMusic : fail server response")
    }
}

suspend fun undoLikeMusic(
    accessToken : String,
    musicId : Int
){
    val contentService: ContentService = createApiService()

    try{
        val response = contentService.undoLikeMusic(
            accessToken = accessToken,
            id = musicId
        )

        if(response.isSuccessful){
            Log.d("network-undoLikeMusic", "undoLikeMusic : success undo like music")
        }
        else {
            Log.e("network-undoLikeMusic", "undoLikeMusic : fail undo like music")
        }
    }catch (e : Exception){
        Log.d("network-undoLikeMusic", "undoLikeMusic : fail server response")
    }
}