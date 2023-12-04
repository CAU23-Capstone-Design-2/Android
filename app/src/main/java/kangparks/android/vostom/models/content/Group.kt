package kangparks.android.vostom.models.content

import com.google.gson.annotations.SerializedName

data class Group(
    @SerializedName("teamId")
    val teamId: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("groupImgUri")
    val groupImgUri: String,

    @SerializedName("userId")
    val userId: Int,

    @SerializedName("userName")
    val userName: String,

    @SerializedName("userImgUri")
    val userImgUri: String,

    @SerializedName("groupMemberCount")
    val groupMemberCount : Int,
)
