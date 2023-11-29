package kangparks.android.vostom.models.user

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("accessToken")
    val accessToken: String,

//    @SerializedName("refreshToken")
//    val refreshToken: String,
)
