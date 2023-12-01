package kangparks.android.vostom.models.user

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("id")
    val id: Long,

    @SerializedName("accessToken")
    val accessToken: String
)
