package kangparks.android.vostom.utils.helper.learning

import kangparks.android.vostom.models.learning.LearningState
import kangparks.android.vostom.utils.networks.learning.getLearningState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun checkCurrentUserLearningState(
    token : String?
)
: Int
//: LearningState
{
    val coroutineScope = CoroutineScope(Dispatchers.IO)

    val currentLearningState = coroutineScope.async {
        delay(1000)
//        if (token == null) {
//            LearningState.BeforeLearning
//        }else{
//            getLearningState(token)
//        }
        100
    }

    return currentLearningState.await()
}