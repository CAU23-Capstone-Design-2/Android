package kangparks.android.vostom.utils.api

import kangparks.android.vostom.models.VostomResponse
import kangparks.android.vostom.models.content.Group
import kangparks.android.vostom.models.content.Music
import kangparks.android.vostom.utils.networks.group.GroupMusicDto
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

interface GroupService {
    @Headers("Content-Type: application/json")
    @GET("/api/group/list")
    suspend fun getGroupList(
        @Header("accessToken") accessToken: String,
    ) : Response<VostomResponse<List<Group>>>

    @Headers("Content-Type: application/json")
    @GET("/api/group/mylist")
    suspend fun getMyGroupList(
        @Header("accessToken") accessToken: String,
    ) : Response<VostomResponse<List<Group>>>

    @Headers("Content-Type: application/json")
    @GET("/api/group/playlist")
    suspend fun getGroupMusicList(
        @Header("accessToken") accessToken: String,
        @Query("id") groupId: Int,
    ) : Response<VostomResponse<List<Music>>>

    @Multipart
    @POST("/api/group")
    suspend fun createGroup(
        @Header("accessToken") accessToken: String,
        @Part teamName : String,
        @Part teamDescription : String,
        @Part teamImage: MultipartBody.Part,
    ) : Response<VostomResponse<ResponseBody>>

    @Multipart
    @PUT("/api/group")
    suspend fun updateGroup(
        @Header("accessToken") accessToken: String,
        @Part teamId : String,
        @Part teamName : String,
        @Part teamDescription : String,
        @Part teamImage: MultipartBody.Part,
    ) : Response<VostomResponse<ResponseBody>>

    @Headers("Content-Type: application/json")
    @DELETE("/api/group")
    suspend fun deleteGroup(
        @Header("accessToken") accessToken: String,
        @Body id : Int
    ) : Response<VostomResponse<ResponseBody>>

    @Headers("Content-Type: application/json")
    @POST("/api/group/join")
    suspend fun joinGroup(
        @Header("accessToken") accessToken: String,
        @Body teamId : Int
    ) : Response<VostomResponse<ResponseBody>>

    @Headers("Content-Type: application/json")
    @POST("/api/group/leave")
    suspend fun leaveGroup(
        @Header("accessToken") accessToken: String,
        @Body teamId : Int
    ) : Response<VostomResponse<ResponseBody>>

    @Headers("Content-Type: application/json")
    @PUT("/api/music/group")
    suspend fun addMusicToGroup(
        @Header("accessToken") accessToken: String,
        @Body data : GroupMusicDto,
    ) : Response<VostomResponse<ResponseBody>>

    @Headers("Content-Type: application/json")
    @DELETE("/api/music/group")
    suspend fun deleteMusicFromGroup(
        @Header("accessToken") accessToken: String,
        @Body data : GroupMusicDto,
    ) : Response<VostomResponse<ResponseBody>>

}