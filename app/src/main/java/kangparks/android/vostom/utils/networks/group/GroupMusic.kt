package kangparks.android.vostom.utils.networks.group

import android.content.Context
import android.util.Log
import kangparks.android.vostom.models.content.Music
import kangparks.android.vostom.utils.api.GroupService
import kangparks.android.vostom.utils.helper.builder.createApiService
import kangparks.android.vostom.utils.store.getAccessToken

data class GroupMusicDto(
    val musicId: Int,
    val teamId: Int
)

suspend fun addMusicToGroup(
    context: Context,
    groupId: Int,
    musicId: Int
): Boolean {
    val groupService: GroupService = createApiService()

    val token = getAccessToken(context)

    try {
        val response = groupService.addMusicToGroup(
            accessToken = token!!,
            musicId = musicId,
            groupId = groupId
        )
        return if (response.isSuccessful) {
            Log.d("network", "addMusicToGroup : success!")
            true
        } else {
            Log.e("network", "addMusicToGroup : server error : ${response}")
            false
        }
    } catch (e: Exception) {
        Log.e("network", "addMusicToGroup : unknown error : ${e.message}")
        return false
    }
}

suspend fun deleteMusicFromGroup(
    context: Context,
    groupId: Int,
    musicId: Int
): Boolean {
    val groupService: GroupService = createApiService()

    val token = getAccessToken(context)

    try {
        val response = groupService.deleteMusicFromGroup(
            accessToken = token!!,
            musicId = musicId,
            groupId = groupId
        )
        return if (response.isSuccessful) {
            Log.d("network", "deleteMusicFromGroup : success! - $response")
            true
        } else {
            Log.e("network", "deleteMusicFromGroup : server error : ${response}")
            false
        }
    } catch (e: Exception) {
        Log.e("network", "deleteMusicFromGroup : unknown error : ${e.message}")
        return false
    }
}

suspend fun getGroupMusicList(
    context: Context,
    groupId: Int
): List<Music> {
    val groupService: GroupService = createApiService()

    val token = getAccessToken(context)

    try {
        val response = groupService.getGroupMusicList(
            accessToken = token!!,
            groupId = groupId
        )
        return if (response.isSuccessful) {
            Log.d("network", "getGroupMusicList : success! $response")
            return if (response.body()?.data != null) {
                response.body()!!.data
            } else {
                listOf<Music>()
            }
        } else {
            Log.e("network", "getGroupMusicList : server error : ${response}")
            listOf<Music>()
        }
    } catch (e: Exception) {
        Log.e("network", "getGroupMusicList : unknown error : ${e.message}")
        return listOf<Music>()
    }
}