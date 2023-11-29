package kangparks.android.vostom.models.content

data class Comment(
    val id : String,
    val content : String,
    val date : String,
    val userId : String,
    val userName : String = "",
    val userImgUri: String,
    val likeCount: Int,
    val likedByUser : Boolean = false
)
