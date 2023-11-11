package kangparks.android.vostom.utils.api

import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserService {
    @Headers("Content-Type: application/json")
    @POST("/api/auth/login")
    suspend fun login(@Header("Authorization") accessToken: String) : Response<Any>
}