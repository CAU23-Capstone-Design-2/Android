package kangparks.android.vostom.utils.networks.group

import android.content.Context
import android.util.Log
import kangparks.android.vostom.utils.api.GroupService
import kangparks.android.vostom.utils.helper.builder.createApiService
import kangparks.android.vostom.utils.store.getAccessToken

suspend fun joinGroup(
    context: Context,
    id: Int
): Boolean {
    val groupService: GroupService = createApiService()

    val token = getAccessToken(context)

    try {
        val response = groupService.joinGroup(
            accessToken = token!!,
            teamId = id
        )
        return if (response.isSuccessful) {
            Log.d("network", "joinGroup : success!")
            true
        } else {
            Log.e("network", "joinGroup : server error : ${response}")
            false
        }
    } catch (e: Exception) {
        Log.e("network", "joinGroup : unknown error : ${e.message}")
        return false
    }
}