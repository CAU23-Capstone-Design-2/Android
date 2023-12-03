package kangparks.android.vostom.utils.builder

import kangparks.android.vostom.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

inline fun <reified T> createApiService(): T{
    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(300, TimeUnit.SECONDS) // 연결 시간 제한을 늘립니다.
        .readTimeout(300, TimeUnit.SECONDS) // 읽기 시간 제한을 늘립니다.
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.base_url)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    return retrofit.create(T::class.java)
}