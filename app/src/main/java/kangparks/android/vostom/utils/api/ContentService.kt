package kangparks.android.vostom.utils.api

import kangparks.android.vostom.models.VostomResponse
import kangparks.android.vostom.models.content.Celebrity
import kangparks.android.vostom.models.content.Music
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ContentService {
    @Headers("Content-Type: application/json")
    @GET("/api/user/celebrityList")
    suspend fun getCelebrityList(
        @Header("accessToken") accessToken: String
    ) : Response<VostomResponse<List<Celebrity>>>

    @Headers("Content-Type: application/json")
    @GET("/api/user/celebrity/musicList")
    suspend fun getCelebrityMusicList(
        @Header("Authorization") accessToken: String,
        @Query("id") celebrityId: String,
    ) : Response<VostomResponse<List<Music>>>


    // 미구현 API
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

    @Headers("Content-Type: application/json")
    @POST("/api/content/createCover")
    suspend fun createCover(
        @Header("Authorization") accessToken: String,
        @Body uri : String,
    ) : Response<Any>

    @Headers("Content-Type: application/json")
    @GET("/api/content/{coverId}/comment")
    suspend fun getComments(
        @Header("Authorization") accessToken: String,
        @Path("coverId") coverId: String,
    ) : Response<Any>
}