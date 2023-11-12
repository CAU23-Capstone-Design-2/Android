package kangparks.android.vostom.utils.networks.learning

import android.util.Log
import kangparks.android.vostom.utils.api.LearningService
import kangparks.android.vostom.utils.builder.createApiService
//import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

suspend fun uploadLearningData(
    accessToken : String,
    recordFiles : List<File>
){
    val learningService : LearningService = createApiService()

    val parts = mutableListOf<MultipartBody.Part>()

    for (recordFile in recordFiles) {
        val audioRequestBody = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), recordFile)
        val audioPart = MultipartBody.Part.createFormData("audioFiles", recordFile.name, audioRequestBody)
        parts.add(audioPart)
    }

    val response = learningService.addLearningData(accessToken, parts)
    if (response.isSuccessful) {
        val learningData = response.body()
        Log.d("NETWORK-uploadLearningData", "$learningData")
    } else {
        // error
        Log.e("NETWORK-uploadLearningData", "$response")
    }
}