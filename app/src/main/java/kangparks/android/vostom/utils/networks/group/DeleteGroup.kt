package kangparks.android.vostom.utils.networks.group

import android.content.Context
import android.util.Log
import kangparks.android.vostom.utils.api.GroupService
import kangparks.android.vostom.utils.helper.builder.createApiService
import kangparks.android.vostom.utils.store.getAccessToken

suspend fun deleteGroup(
    context: Context,
    id: Int
): Boolean {
    val groupService: GroupService = createApiService()

    val token = getAccessToken(context)

    try {
        val response = groupService.deleteGroup(
            accessToken = token!!,
            id = id
        )
        if (response.isSuccessful) {
            Log.d("network", "deleteGroup : success!")
            return true
        } else {
            Log.e("network", "deleteGroup : server error : ${response}")
            return false
        }
    } catch (e: Exception) {
        Log.e("network", "deleteGroup : unknown error : ${e.message}")
        return false
    }
}