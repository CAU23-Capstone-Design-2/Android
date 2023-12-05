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
    var likeCount: Int,

    @SerializedName("likedByUser")
    var likedByUser: Boolean,

    @SerializedName("title")
     val title: String,

    @SerializedName("userId")
    val userId: Int,

    @SerializedName("userImgUri")
    val userImgUri: String,

    @SerializedName("userName")
    val userName: String

){
    fun setLikeState(){
        if(this.likedByUser){
            this.likedByUser = false
            this.likeCount -= 1
        }
        else{
            this.likedByUser = true
            this.likeCount += 1
        }

    }
}