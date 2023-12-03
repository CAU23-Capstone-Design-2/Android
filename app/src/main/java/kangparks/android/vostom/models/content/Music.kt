package kangparks.android.vostom.models.content

import com.google.gson.annotations.SerializedName

data class Music(
    @SerializedName("albumArtUri")
    val albumArtUri: String,

    @SerializedName("contentUri")
    val contentUri: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("likeCount")
    val likeCount: Int,

    @SerializedName("likedByUser")
    val likedByUser: Boolean,

    @SerializedName("title")
     val title: String,

    @SerializedName("userId")
    val userId: Int,

    @SerializedName("userImgUri")
    val userImgUri: String,

    @SerializedName("userName")
    val userName: String
)