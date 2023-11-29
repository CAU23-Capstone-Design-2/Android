package kangparks.android.vostom.models.user

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("header")
    val header: HeaderOfTokenResponse,

    @SerializedName("data")
    val data: DataOfTokenResponse,

    @SerializedName("msg")
    val msg: String
)

data class HeaderOfTokenResponse(
    @SerializedName("code")
    val code: Int,

    @SerializedName("message")
    val message: String
)

data class DataOfTokenResponse(
    @SerializedName("id")
    val id: Long,

    @SerializedName("accessToken")
    val accessToken: String
)
