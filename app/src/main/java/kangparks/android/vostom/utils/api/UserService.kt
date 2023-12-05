package kangparks.android.vostom.utils.api

import kangparks.android.vostom.models.VostomResponse
import kangparks.android.vostom.models.user.DeleteUserDto
import kangparks.android.vostom.models.user.TokenResponse
import kangparks.android.vostom.models.user.UserInfo
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Query

interface UserService {
    @Headers("Content-Type: application/json")
    @POST("/api/auth/login")
    suspend fun login(
        @Header("accessToken") accessToken: String
    ) : Response<VostomResponse<TokenResponse>>

    @Multipart
    @PUT("/api/user")
    suspend fun updateProfile(
        @Header("accessToken") accessToken: String,
        @Part imageFile: MultipartBody.Part
    ): Response<VostomResponse<ResponseBody>>

    @Headers("Content-Type: application/json")
    @GET("/api/user/profile")
    suspend fun getUserInfo(
        @Header("accessToken") accessToken: String,
    ) : Response<VostomResponse<UserInfo>>


    @Headers("Content-Type: application/json")
    @DELETE("/api/user")
    suspend fun withdrawalUser(
        @Header("accessToken") accessToken: String,
        @Body userInfo : DeleteUserDto
    ) : Response<VostomResponse<ResponseBody>>
}