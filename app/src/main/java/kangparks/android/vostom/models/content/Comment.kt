package kangparks.android.vostom.models.content

data class Comment(
    val commentId : Int = 0,
    val userId : Int = 0,
    val userNmaw : String = "",
    val userImgUri : String = "",
    val comment : String = "",
    val commentDate : String = "",
    val likeCount : Int = 0,
    // TODO (사용자가 해당 댓글을 좋아요 하는지 판단은 어떻게 하지?)
    // 서버에서 하나??
)
