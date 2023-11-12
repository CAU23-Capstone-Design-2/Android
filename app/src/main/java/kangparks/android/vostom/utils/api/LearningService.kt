package kangparks.android.vostom.utils.api

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Part

interface LearningService {
    @Headers("Content-Type: application/json")
    @POST("/api/learning/addData")
    suspend fun addLearningData(
        @Header("Authorization") accessToken: String,
        @Part image: List<MultipartBody.Part>,
    ) : Response<Any>
}