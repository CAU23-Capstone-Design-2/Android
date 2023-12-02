package kangparks.android.vostom.models.learning

import com.google.gson.annotations.SerializedName

data class LearningStateResponse(
    @SerializedName("state")
    val state: Int,
)
