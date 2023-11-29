package kangparks.android.vostom.utils.api

import kangparks.android.vostom.models.user.TokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserService {
    @Headers("Content-Type: application/json")
    @POST("/api/auth/login")
    suspend fun login(
        @Header("accessToken") accessToken: String
    ) : Response<TokenResponse>
}