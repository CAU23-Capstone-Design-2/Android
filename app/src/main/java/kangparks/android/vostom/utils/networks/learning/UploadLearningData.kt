package kangparks.android.vostom.utils.networks.learning

import android.content.Context
import android.util.Log
import android.widget.Toast
import kangparks.android.vostom.models.VostomResponse
import kangparks.android.vostom.utils.api.LearningService
import kangparks.android.vostom.utils.helper.builder.createApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
//import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.File

suspend fun uploadLearningData(
    accessToken: String,
    recordFiles: List<File>,
    context: Context
): Boolean {
    val learningService: LearningService = createApiService()

    val parts = mutableListOf<MultipartBody.Part>()

    for (recordFile in recordFiles) {
        Log.d("NETWORK-uploadLearningData", "$recordFile")
        val audioRequestBody =
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(), recordFile)
        val audioPart =
            MultipartBody.Part.createFormData("voiceFiles", recordFile.name, audioRequestBody)
        parts.add(audioPart)
    }

    return try {
        val response: Response<VostomResponse<ResponseBody>> =
            learningService.addUserAudioFiles(accessToken, parts)
        if (response.isSuccessful) {
            val learningData = response.body()?.data
            Log.d("NETWORK-uploadLearningData", "학습 데이터 전송 완료 $learningData")
            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(context, "학습 데이터 업로드 완료", Toast.LENGTH_SHORT).show()
            }
            true
        } else {
            // error
            Log.e("NETWORK-uploadLearningData", "오류 발생 ${response.errorBody()}")
            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(
                    context, "업로드 중 오류 발생했습니다.\n" +
                            "다시 시도해 주세요.", Toast.LENGTH_SHORT
                ).show()
            }
            false
        }
    } catch (e: Exception) {
        Log.e("NETWORK-uploadLearningData", "알수 없는 오류 $e, ${e.message}")
        CoroutineScope(Dispatchers.Main).launch {
            Toast.makeText(context, "알 수 없는 오류가 발생했습니다.\n다시 시도해 주세요.", Toast.LENGTH_SHORT).show()
        }
        false
    }
}