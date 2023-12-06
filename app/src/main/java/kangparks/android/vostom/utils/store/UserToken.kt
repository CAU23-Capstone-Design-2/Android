package kangparks.android.vostom.utils.store

import android.content.Context

private const val VOSTOM_STORE = "VostomStore"
private const val ACCESS_TOKEN = "ACCESS_TOKEN"
private const val REFRESH_TOKEN = "REFRESH_TOKEN"

fun saveAccessToken(context: Context, accessToken: String) {
    val sharedPreferences = context.getSharedPreferences(VOSTOM_STORE, Context.MODE_PRIVATE)
    with(sharedPreferences.edit()) {
        putString(ACCESS_TOKEN, accessToken)
        apply()
    }
}

fun getAccessToken(context: Context): String? {
    val sharedPreferences = context.getSharedPreferences(VOSTOM_STORE, Context.MODE_PRIVATE)
    return sharedPreferences.getString(ACCESS_TOKEN, null)
}

fun saveRefreshToken(context: Context, refreshToken: String) {
    val sharedPreferences = context.getSharedPreferences(VOSTOM_STORE, Context.MODE_PRIVATE)
    with(sharedPreferences.edit()) {
        putString(REFRESH_TOKEN, refreshToken)
        apply()
    }
}

fun getRefreshToken(context: Context): String? {
    val sharedPreferences = context.getSharedPreferences(VOSTOM_STORE, Context.MODE_PRIVATE)
    return sharedPreferences.getString(REFRESH_TOKEN, null)
}

fun deleteAccessToken(context: Context) {
    val sharedPreferences = context.getSharedPreferences(VOSTOM_STORE, Context.MODE_PRIVATE)
    with(sharedPreferences.edit()) {
        remove(ACCESS_TOKEN)
        apply()
    }
}

fun deleteAllToken(context: Context) {
    val sharedPreferences = context.getSharedPreferences(VOSTOM_STORE, Context.MODE_PRIVATE)
    with(sharedPreferences.edit()) {
        remove(ACCESS_TOKEN)
        remove(REFRESH_TOKEN)
        apply()
    }
}