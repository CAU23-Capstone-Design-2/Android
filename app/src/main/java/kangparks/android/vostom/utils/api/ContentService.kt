package kangparks.android.vostom.utils.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface ContentService {
    @Headers("Content-Type: application/json")
    @GET("/api/content/userCover")
    suspend fun getUserCoverItems(
        @Header("Authorization") accessToken: String,
    ) : Response<Any>

    @Headers("Content-Type: application/json")
    @GET("/api/content/userGroupCover")
    suspend fun getUserGroupCoverItems(
        @Header("Authorization") accessToken: String,
    ) : Response<Any>

    @Headers("Content-Type: application/json")
    @GET("/api/content/starList")
    suspend fun getStarList(
        @Header("Authorization") accessToken: String,
    ) : Response<Any>

    @Headers("Content-Type: application/json")
    @GET("/api/content/starCoverItems")
    suspend fun getStarCoverItems(
        @Header("Authorization") accessToken: String,
        @Body starId: String,
    ) : Response<Any>
}