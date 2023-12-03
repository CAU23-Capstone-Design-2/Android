package kangparks.android.vostom.utils.networks.content

import android.content.Context
import android.util.Log
import android.widget.Toast
import kangparks.android.vostom.models.VostomResponse
import kangparks.android.vostom.models.content.Celebrity
import kangparks.android.vostom.models.content.Music
import kangparks.android.vostom.utils.api.ContentService
import kangparks.android.vostom.utils.builder.createApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

suspend fun getCelebrityList(
    accessToken: String,
    context : Context
):List<Celebrity>? {
    val contentService: ContentService = createApiService()

    return try{
        val response : Response<VostomResponse<List<Celebrity>>> = contentService.getCelebrityList(accessToken)
        Log.d("NETWORK-getCelebrityList", "response : ${response.body()}")

        if(response.isSuccessful) {
            val celebrityList = response.body()?.data
            celebrityList
        }
        else{
            Log.e("NETWORK-getCelebrityList", "server error - ${response.errorBody()}")
            CoroutineScope(Dispatchers.Main).launch{
                Toast.makeText(context, "서버 오류가 발생했습니다.\n다시 시도해 주세요.", Toast.LENGTH_SHORT).show()
            }
            null
        }
    }catch (e: Exception){
        Log.e("NETWORK-getCelebrityList", "unknown error - ${e.message}")
        CoroutineScope(Dispatchers.Main).launch{
            Toast.makeText(context, "알 수 없는 오류가 발생했습니다.\n다시 시도해 주세요.", Toast.LENGTH_SHORT).show()
        }
        null
    }
}

suspend fun getCelebrityMusicList(
    accessToken: String,
    celebrityId: Int,
    context : Context
):List<Music>? {
    val contentService: ContentService = createApiService()

    return try{
        val response : Response<VostomResponse<List<Music>>> = contentService.getCelebrityMusicList(accessToken, celebrityId)
        Log.d("NETWORK-getCelebrityMusicList", "response : ${response.body()}")

        if(response.isSuccessful) {
            val celebrityList = response.body()?.data
            celebrityList
        }
        else{
            Log.e("NETWORK-getCelebrityMusicList", "server error - ${response.errorBody()}")
            CoroutineScope(Dispatchers.Main).launch{
                Toast.makeText(context, "서버 오류가 발생했습니다.\n다시 시도해 주세요.", Toast.LENGTH_SHORT).show()
            }
            null
        }
    }catch (e: Exception){
        Log.e("NETWORK-getCelebrityMusicList", "unknown error - ${e.message}")
        CoroutineScope(Dispatchers.Main).launch{
            Toast.makeText(context, "알 수 없는 오류가 발생했습니다.\n다시 시도해 주세요.", Toast.LENGTH_SHORT).show()
        }
        null
    }
}