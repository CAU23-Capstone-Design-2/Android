package kangparks.android.vostom.utils.api

import kangparks.android.vostom.models.VostomResponse
import kangparks.android.vostom.models.content.Comment
import kangparks.android.vostom.utils.networks.comment.CreateCommentDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface CommentService {

    @Headers("Content-Type: application/json")
    @POST("/api/comment")
    suspend fun createComment(
        @Header("accessToken") accessToken: String,
        @Body content : CreateCommentDto,
        @Query("id") id : Int
    ) : Response<VostomResponse<Any>>

    @Headers("Content-Type: application/json")
    @PUT("/api/comment")
    suspend fun updateComment(
        @Header("accessToken") accessToken: String,
        @Body comment : CreateCommentDto,
        @Query("id") commentId : Int
    ) : Response<VostomResponse<ResponseBody>>

    @Headers("Content-Type: application/json")
    @DELETE("/api/comment")
    suspend fun deleteComment(
        @Header("accessToken") accessToken: String,
        @Query("id") commentId : Int
    ) : Response<VostomResponse<ResponseBody>>

    @Headers("Content-Type: application/json")
    @POST("/api/comment/like")
    suspend fun likeComment(
        @Header("accessToken") accessToken: String,
        @Query("id") commentId : Int
    ) : Response<VostomResponse<ResponseBody>>

    @Headers("Content-Type: application/json")
    @DELETE("/api/comment/like/undo")
    suspend fun unlikeComment(
        @Header("accessToken") accessToken: String,
        @Query("id") commentId : Int
    ) : Response<VostomResponse<ResponseBody>>

    @Headers("Content-Type: application/json")
    @GET("/api/comment/music")
    suspend fun getCommentList(
        @Header("accessToken") accessToken: String,
        @Query("id") musicId : Int
    ) : Response<VostomResponse<List<Comment>>>

}