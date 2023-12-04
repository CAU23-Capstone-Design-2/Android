package kangparks.android.vostom.utils.networks.comment

import android.content.Context
import android.util.Log
import kangparks.android.vostom.models.content.Comment
import kangparks.android.vostom.utils.api.CommentService
import kangparks.android.vostom.utils.helper.builder.createApiService
import kangparks.android.vostom.utils.store.getAccessToken

suspend fun getCommentList(
    context: Context,
    musicId : Int
):List<Comment>{
    val apiService : CommentService = createApiService()

    val token = getAccessToken(context)

    try{
        val response = apiService.getCommentList(
            accessToken = token!!,
            musicId = musicId
        )

        return if(response.isSuccessful) {
            Log.d("NETWORK-getCommentList", "getCommentList : 댓글 리스트 가져오기 완료")

            if(response.body()?.data != null) {
                Log.d("NETWORK-getCommentList", "getCommentList : 댓글 리스트 : ${ response.body()?.data}")
                response.body()?.data!!
            }
            else{
                Log.d("NETWORK-getCommentList", "getCommentList : 댓글 리스트 없음")
                listOf<Comment>()
            }
        }else{
            Log.e("NETWORK-getCommentList", "getCommentList : 서버 오류 발생 ${response}")
            listOf<Comment>()
        }

    }catch(e:Exception){
        Log.d("NETWORK-getCommentList", "getCommentList : 알수 없는 오류 $e, ${e.message}")
        return listOf<Comment>()
    }
}