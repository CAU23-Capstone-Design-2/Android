package kangparks.android.vostom.models.user

import com.google.gson.annotations.SerializedName

data class user(
    @SerializedName("_id")
    val id : Long,

    @SerializedName("name")
    val name : String,

    @SerializedName("birthday")
    val birthday : String,

    @SerializedName("gender")
    val gender : String,
)
