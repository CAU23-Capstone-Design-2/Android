package kangparks.android.vostom.models.content

import com.google.gson.annotations.SerializedName

data class Comment(
    @SerializedName("id")
    val id : Int,

    @SerializedName("content")
    val content : String,

    @SerializedName("date")
    val date : String,

    @SerializedName("userId")
    val userId : Int,

    @SerializedName("nickname")
    val nickname : String = "",

    @SerializedName("userImgUri")
    val userImgUri: String,

    @SerializedName("likeCount")
    val likeCount: Int,

    @SerializedName("likedByUser")
    val likedByUser : Boolean
)