package kangparks.android.vostom.utils.networks.learning

import android.content.Context
import android.util.Log
import kangparks.android.vostom.models.VostomResponse
import kangparks.android.vostom.models.learning.LearningState
//import kangparks.android.vostom.models.learning.LearningStateResponse
import kangparks.android.vostom.utils.api.LearningService
import kangparks.android.vostom.utils.helper.builder.createApiService
import kangparks.android.vostom.utils.store.getAccessToken
import retrofit2.HttpException
import retrofit2.Response

suspend fun getLearningState(
    accessToken : String,
) : LearningState?{
    val learningService: LearningService = createApiService()

    return try {
        val response : Response<VostomResponse<Int>> = learningService.getLearningState(accessToken)
        Log.d("NETWORK-getLearningState", "response : ${response.body()}")
        if(response.isSuccessful){
            val learningState = response.body()?.data
            Log.d("NETWORK-getLearningState", "learningState : $learningState")

            when (learningState) {
                0 -> {
                    LearningState.BeforeLearning
                }
                1 -> {
                    LearningState.Learning
                }
                2-> {
                    LearningState.AfterLearning
                }
                else ->{
                    LearningState.BeforeLearning
                }
            }
        }
        else{
            LearningState.BeforeLearning
        }
    }catch (e : HttpException){
        Log.d("NETWORK-getLearningState", "HttpException : ${e.message()}")
        null
    }
    catch (e: Exception){
        Log.d("NETWORK-getLearningState", "Exception : ${e.message}")
        null
    }
}