package kangparks.android.vostom.utils.api

import kangparks.android.vostom.models.VostomResponse
import kangparks.android.vostom.models.learning.LearningStateResponse
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Part

interface LearningService {
    @Headers("Content-Type: application/json")
    @POST("/api/learning/addFiles")
    suspend fun addUserAudioFiles(
        @Header("Authorization") accessToken: String,
        @Part image: List<MultipartBody.Part>,
    ) : Response<VostomResponse<ResponseBody>>

    @Headers("Content-Type: application/json")
    @GET("/api/learning/state")
    suspend fun getLearningState(
        @Header("Authorization") accessToken: String,
    ) : Response<VostomResponse<LearningStateResponse>>
}