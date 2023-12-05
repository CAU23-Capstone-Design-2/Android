package kangparks.android.vostom.models.content

import com.google.gson.annotations.SerializedName

data class Group(
    @SerializedName("groupId")
    val teamId: Int,

    @SerializedName("groupName")
    val title: String,

    @SerializedName("groupDescription")
    val description: String,

    @SerializedName("groupImgUri")
    val groupImgUri: String,

    @SerializedName("leaderId")
    val userId: Int,

    @SerializedName("leaderName")
    val userName: String,

    @SerializedName("leaderImgUri")
    val userImgUri: String,

    @SerializedName("groupMemberCount")
    val groupMemberCount : Int,

    @SerializedName("member")
    val isMember : Boolean,

    @SerializedName("leader")
    val isLeader : Boolean
)
