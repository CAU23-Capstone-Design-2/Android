package kangparks.android.vostom.utils.api

import kangparks.android.vostom.models.VostomResponse
import kangparks.android.vostom.models.content.Celebrity
import kangparks.android.vostom.models.content.Music
import kangparks.android.vostom.utils.networks.content.CreateCoverBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
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
        @Header("accessToken") accessToken: String,
        @Query("id") celebrityId: Int,
    ) : Response<VostomResponse<List<Music>>>

    @Headers("Content-Type: application/json")
    @POST("/api/music/train")
    suspend fun createCover(
        @Header("accessToken") accessToken: String,
        @Body body : CreateCoverBody,
        @Query("url") url : String
    ) : Response<ResponseBody>

    @Headers("Content-Type: application/json")
    @GET("/api/user/music")
    suspend fun getUserMusicList(
        @Header("accessToken") accessToken: String
    ) : Response<VostomResponse<List<Music>>>

    @Headers("Content-Type: application/json")
    @GET("/api/user/music/request")
    suspend fun getRequestMusicList(
        @Header("accessToken") accessToken: String
    ) : Response<VostomResponse<List<Music>>>

    @Headers("Content-Type: application/json")
    @GET("/api/user/likedMusic")
    suspend fun getUserLikedMusic(
        @Header("accessToken") accessToken: String,
    ) : Response<VostomResponse<List<Music>>>

    @Headers("Content-Type: application/json")
    @GET("/api/user/teamMusic")
    suspend fun getUserTeamMusic(
        @Header("accessToken") accessToken: String,
    ) : Response<VostomResponse<List<Music>>>

    @Headers("Content-Type: application/json")
    @POST("/api/music/like")
    suspend fun likeMusic(
        @Header("accessToken") accessToken: String,
        @Query("id") id : String
    ) : Response<VostomResponse<ResponseBody>>

    @Headers("Content-Type: application/json")
    @DELETE("/api/music/like/undo")
    suspend fun undoLikeMusic(
        @Header("accessToken") accessToken: String,
        @Query("id") id : String
    ) : Response<VostomResponse<ResponseBody>>

//######################################################
    // 미구현 API




    @Headers("Content-Type: application/json")
    @GET("/api/content/{coverId}/comment")
    suspend fun getComments(
        @Header("Authorization") accessToken: String,
        @Path("coverId") coverId: String,
    ) : Response<Any>
}