package kangparks.android.vostom.utils.networks.comment

import android.content.Context
import android.util.Log
import kangparks.android.vostom.models.VostomResponse
import kangparks.android.vostom.utils.api.CommentService
import kangparks.android.vostom.utils.helper.builder.createApiService
import kangparks.android.vostom.utils.store.getAccessToken
import okhttp3.ResponseBody
import retrofit2.Response

data class CreateCommentDto(
    val coverSongId : Int,
    val content : String
)
suspend fun createComment(
    context: Context,
    musicId : Int,
    comment : String
):Boolean{
    val apiService : CommentService = createApiService()

    val token = getAccessToken(context)

    try{
        val response : Response<VostomResponse<Any>> = apiService.createComment(
            accessToken = token!!,
            commentData = CreateCommentDto(
                coverSongId = musicId,
                content = comment
            )
        )

        return if(response.isSuccessful) {
            Log.d("NETWORK-createComment", "createComment : 댓글 생성 완료")
            true
        }else{
            Log.e("NETWORK-createComment", "createComment : 서버 오류 발생 ${response}")
            false
        }

    }catch(e:Exception){
        Log.d("NETWORK-createComment", "createComment : 알수 없는 오류 $e, ${e.message}")
        return false
    }
}

suspend fun updateComment(
    context: Context,
    commentId : Int,
    comment : String
):Boolean{
    val apiService : CommentService = createApiService()

    val token = getAccessToken(context)

    try{
        val response = apiService.updateComment(
            accessToken = token!!,
            comment = comment,
            commentId = commentId
        )

        return if(response.isSuccessful) {
            Log.d("NETWORK-updateComment", "updateComment : 댓글 수정 완료")
            true
        }else{
            Log.e("NETWORK-updateComment", "updateComment : 서버 오류 발생 ${response}")
            false
        }

    }catch(e:Exception){
        Log.d("NETWORK-updateComment", "updateComment : 알수 없는 오류 $e, ${e.message}")
        return false
    }
}

suspend fun deleteComment(
    context: Context,
    commentId : Int
):Boolean{
    val apiService : CommentService = createApiService()

    val token = getAccessToken(context)

    try{
        val response = apiService.deleteComment(
            accessToken = token!!,
            commentId = commentId
        )

        return if(response.isSuccessful) {
            Log.d("NETWORK-deleteComment", "deleteComment : 댓글 삭제 완료")
            true
        }else{
            Log.e("NETWORK-deleteComment", "deleteComment : 서버 오류 발생 ${response}")
            false
        }

    }catch(e:Exception){
        Log.d("NETWORK-deleteComment", "deleteComment : 알수 없는 오류 $e, ${e.message}")
        return false
    }
}

suspend fun likeComment(
    context: Context,
    commentId : Int
):Boolean{
    val apiService : CommentService = createApiService()

    val token = getAccessToken(context)

    try{
        val response = apiService.likeComment(
            accessToken = token!!,
            commentId = commentId
        )

        return if(response.isSuccessful) {
            Log.d("NETWORK-likeComment", "likeComment : 댓글 좋아요 완료")
            true
        }else{
            Log.e("NETWORK-likeComment", "likeComment : 서버 오류 발생 ${response}")
            false
        }

    }catch(e:Exception){
        Log.d("NETWORK-likeComment", "likeComment : 알수 없는 오류 $e, ${e.message}")
        return false
    }
}

suspend fun unlikeComment(
    context: Context,
    commentId : Int
):Boolean{
    val apiService : CommentService = createApiService()

    val token = getAccessToken(context)

    try{
        val response = apiService.unlikeComment(
            accessToken = token!!,
            commentId = commentId
        )

        return if(response.isSuccessful) {
            Log.d("NETWORK-unlikeComment", "unlikeComment : 댓글 좋아요 취소 완료")
            true
        }else{
            Log.e("NETWORK-unlikeComment", "unlikeComment : 서버 오류 발생 ${response}")
            false
        }

    }catch(e:Exception){
        Log.d("NETWORK-unlikeComment", "unlikeComment : 알수 없는 오류 $e, ${e.message}")
        return false
    }
}