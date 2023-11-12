package kangparks.android.vostom.utils.networks.user

import android.util.Log
import kangparks.android.vostom.utils.api.UserService
import kangparks.android.vostom.utils.builder.createApiService

suspend fun login(accessToken: String) {
    val userService: UserService = createApiService()

    val response = userService.login(accessToken)
    if (response.isSuccessful) {
        val user = response.body()
        Log.d("NETWORK-login", "$user")
//        if (user != null) {
//
//        }
    } else {
        // error
        Log.e("NETWORK-login", "$response")
    }
}