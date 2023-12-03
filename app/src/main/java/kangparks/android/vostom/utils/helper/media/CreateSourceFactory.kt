package kangparks.android.vostom.utils.helper.media

import android.content.Context
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.upstream.ResolvingDataSource
import com.google.android.exoplayer2.util.Util

fun createSourceFactory(context : Context, token : String): ResolvingDataSource.Factory{
    val defaultDataSourceFactory = DefaultHttpDataSource.Factory().setUserAgent(Util.getUserAgent(context, "Vostom"))

    val dataSourceFactory = ResolvingDataSource.Factory(defaultDataSourceFactory){
        it.withAdditionalHeaders(mutableMapOf<String, String>().apply{
            put("accessToken",token)
        })
    }

    return dataSourceFactory
}