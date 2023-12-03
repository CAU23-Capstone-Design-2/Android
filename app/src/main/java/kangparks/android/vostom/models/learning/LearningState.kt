package kangparks.android.vostom.models.learning

sealed class LearningState(
    val state: Int
) {
    object BeforeLearning : LearningState(0)
    object Learning : LearningState(1)
    object AfterLearning : LearningState(2)
}
