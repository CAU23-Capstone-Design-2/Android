package kangparks.android.vostom.models.content

data class Group(
    val id: Int,
    val title: String,
    val description: String,
    val groupImgUri: String,
    val groupLeader : String,
    val groupLeaderImg : String,
    val groupMemberCount : Int,
)
