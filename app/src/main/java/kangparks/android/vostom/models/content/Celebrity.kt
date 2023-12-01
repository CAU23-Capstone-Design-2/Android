package kangparks.android.vostom.models.content

import com.google.gson.annotations.SerializedName

data class Celebrity(
    @SerializedName("celebrityName")
    val celebrityName: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("imgUrl")
    val imgUrl: String
)