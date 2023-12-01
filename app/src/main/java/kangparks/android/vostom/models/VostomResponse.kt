package kangparks.android.vostom.models

import com.google.gson.annotations.SerializedName

data class VostomResponse<T>(
    @SerializedName("header")
    val header: HeaderOfTokenResponse,

    @SerializedName("data")
    val data: T,

    @SerializedName("msg")
    val msg: String
)

data class HeaderOfTokenResponse(
    @SerializedName("code")
    val code: Int,

    @SerializedName("message")
    val message: String
)
