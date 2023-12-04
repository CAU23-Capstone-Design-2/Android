package kangparks.android.vostom.utils.networks.user

import android.content.Context
import android.net.Uri
import android.util.Log
import kangparks.android.vostom.utils.api.UserService
import kangparks.android.vostom.utils.helper.builder.createApiService
import kangparks.android.vostom.utils.store.getAccessToken
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody

suspend fun updateProfile(
    context : Context,
    imgUri : String,
){
    val uri = Uri.parse(imgUri)
    val token = getAccessToken(context)
    val apiService : UserService = createApiService()

    context.contentResolver.openInputStream(uri)?.use { inputStream->
        val byteArray = inputStream.readBytes()

        val requestFile = byteArray?.let {
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(), it)
        }
        val imagePart = requestFile?.let {
            MultipartBody.Part.createFormData("imageFile", "userProfileImg.png", it)
        }

        try {
            Log.d("network", "updateProfile : $imagePart")
            Log.d("network", "updateProfile : token : $token")
            val response = imagePart?.let {
                apiService.updateProfile(
                    accessToken = token!!,
                    imageFile = it
                )
            }
            if (response != null) {
                if(response.isSuccessful){
                    Log.d("network", "updateProfile : success!")
                } else{
                    Log.e("network", "updateProfile : server error : ${response.errorBody()}")
                    Log.e("network", "updateProfile : server error : ${response}")
                }
            } else {
                Log.e("network", "updateProfile : response error")
            }
        }catch (e : Exception){
            Log.e("network", "updateProfile : unknown error : ${e.message}")
        }
    }

}