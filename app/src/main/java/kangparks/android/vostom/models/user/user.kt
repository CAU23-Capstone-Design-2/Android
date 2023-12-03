package kangparks.android.vostom.models.user

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("nickname")
    val nickname : String,

    @SerializedName("profileImage")
    val profileImage : String,
)
