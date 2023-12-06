package kangparks.android.vostom.utils.api

import kangparks.android.vostom.models.VostomResponse
import kangparks.android.vostom.models.content.Group
import kangparks.android.vostom.models.content.Music
import kangparks.android.vostom.utils.networks.group.CreateGroupDto
import kangparks.android.vostom.utils.networks.group.GroupMusicDto
import kangparks.android.vostom.utils.networks.group.UpdateGroupDto
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
import retrofit2.http.Path
import retrofit2.http.Query

interface GroupService {
    @Headers("Content-Type: application/json")
    @GET("/api/groups")
    suspend fun getGroupList(
        @Header("accessToken") accessToken: String,
    ) : Response<VostomResponse<List<Group>>>

    @Headers("Content-Type: application/json")
    @GET("/api/groups/my-groups")
    suspend fun getMyGroupList(
        @Header("accessToken") accessToken: String,
    ) : Response<VostomResponse<List<Group>>>

    @Headers("Content-Type: application/json")
    @GET("/api/groups/{groupId}/playlist")
    suspend fun getGroupMusicList(
        @Header("accessToken") accessToken: String,
        @Path("groupId") groupId : Int
    ) : Response<VostomResponse<List<Music>>>

    @Multipart
    @POST("/api/groups")
    suspend fun createGroup(
        @Header("accessToken") accessToken: String,
        @Part("createGroupDto") teamName : CreateGroupDto,
        @Part groupImage: MultipartBody.Part,
    ) : Response<VostomResponse<ResponseBody>>

    @Multipart
    @PUT("/api/groups/{groupId}")
    suspend fun updateGroup(
        @Header("accessToken") accessToken: String,
        @Part("requestGroupDto") requestGroupDto : UpdateGroupDto,
        @Part groupImage: MultipartBody.Part,
        @Path("groupId") id : Int
    ) : Response<VostomResponse<ResponseBody>>

    @Headers("Content-Type: application/json")
    @DELETE("/api/groups/{groupId}")
    suspend fun deleteGroup(
        @Header("accessToken") accessToken: String,
        @Path("groupId") id : Int
    ) : Response<VostomResponse<ResponseBody>>

    @Headers("Content-Type: application/json")
    @POST("/api/groups/{groupId}/join")
    suspend fun joinGroup(
        @Header("accessToken") accessToken: String,
        @Path("groupId") groupId : Int
    ) : Response<VostomResponse<ResponseBody>>

    @Headers("Content-Type: application/json")
    @DELETE("/api/groups/{groupId}/leave")
    suspend fun leaveGroup(
        @Header("accessToken") accessToken: String,
        @Path("groupId") groupId : Int
    ) : Response<VostomResponse<ResponseBody>>

    @Headers("Content-Type: application/json")
    @POST("/api/groups/{groupId}/add-song/{musicId}")
    suspend fun addMusicToGroup(
        @Header("accessToken") accessToken: String,
        @Path("groupId") groupId : Int,
        @Path("musicId") musicId : Int
    ) : Response<VostomResponse<Any>>

    @Headers("Content-Type: application/json")
    @DELETE("/api/groups/{groupId}/remove-song/{musicId}")
    suspend fun deleteMusicFromGroup(
        @Header("accessToken") accessToken: String,
        @Path("groupId") groupId : Int,
        @Path("musicId") musicId : Int
    ) : Response<VostomResponse<ResponseBody>>

}