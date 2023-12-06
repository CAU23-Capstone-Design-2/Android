package kangparks.android.vostom.utils.networks.group

import android.content.Context
import android.net.Uri
import android.util.Log
import kangparks.android.vostom.utils.api.GroupService
import kangparks.android.vostom.utils.helper.builder.createApiService
import kangparks.android.vostom.utils.store.getAccessToken
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody

data class UpdateGroupDto(
    val groupName: String,
    val groupDescription: String,
)

suspend fun updateGroup(
    context : Context,
    teamId : Int,
    teamName: String,
    teamDescription: String,
    teamImgUri: String,
): Boolean {
    val groupService: GroupService = createApiService()

    val token = getAccessToken(context)
    val uri = Uri.parse(teamImgUri)

    context.contentResolver.openInputStream(uri)?.use { inputStream ->
        val byteArray = inputStream.readBytes()

        val requestFile = byteArray?.let {
            RequestBody.create("image/*".toMediaTypeOrNull(), it)
        }
        val imagePart = requestFile?.let {
            MultipartBody.Part.createFormData("groupImage", "${teamName}Img.png", it)
        }

        try {
            val response = imagePart?.let {
                groupService.updateGroup(
                    accessToken = token!!,
                    requestGroupDto = UpdateGroupDto(
                        groupName = teamName,
                        groupDescription = teamDescription,
                    ),
                    groupImage = it,
                    id = teamId,
                )
            }
            if (response != null) {
                if (response.isSuccessful) {
                    Log.d("network", "updateGroup : success!")
                    return true
                } else {
                    Log.e("network", "updateGroup : server error : ${response.errorBody()}")
                    Log.e("network", "updateGroup : server error : ${response}")
                    return false
                }
            } else {
                Log.e("network", "updateGroup : response error")
                return false
            }
        } catch (e: Exception) {
            Log.e("network", "updateGroup : unknown error : ${e.message}")
            return false
        }
    }

    return false
}