package kangparks.android.vostom.models.user

import com.google.gson.annotations.SerializedName

data class DeleteUserDto(
    @SerializedName("kakaoId")
    val kakaoId : String,

    @SerializedName("userId")
    val userId : String
)
