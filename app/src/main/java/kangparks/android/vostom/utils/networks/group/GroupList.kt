package kangparks.android.vostom.utils.networks.group

import android.content.Context
import android.util.Log
import kangparks.android.vostom.models.content.Group
import kangparks.android.vostom.utils.api.GroupService
import kangparks.android.vostom.utils.helper.builder.createApiService
import kangparks.android.vostom.utils.store.getAccessToken

suspend fun getGroupList(
    context: Context
): List<Group> {
    val groupService: GroupService = createApiService()

    val token = getAccessToken(context)

    return try {
        val response = groupService.getGroupList(
            accessToken = token!!
        )
        if (response.isSuccessful) {
            Log.d("network", "getGroupList : success!")
            response.body()?.data ?: listOf()
        } else {
            Log.e("network", "getGroupList : server error : $response")
            listOf()
        }
    } catch (e: Exception) {
        Log.e("network", "getGroupList : unknown error : ${e.message}")
        listOf()
    }
}

suspend fun getMyGroupList(
    context: Context
): List<Group> {
    val groupService: GroupService = createApiService()

    val token = getAccessToken(context)

    return try {
        val response = groupService.getMyGroupList(
            accessToken = token!!
        )
        if (response.isSuccessful) {
            Log.d("network", "getMyGroupList : success!")
            response.body()?.data ?: listOf()
        } else {
            Log.e("network", "getMyGroupList : server error : $response")
            listOf()
        }
    } catch (e: Exception) {
        Log.e("network", "getMyGroupList : unknown error : ${e.message}")
        listOf()
    }
}