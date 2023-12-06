package kangparks.android.vostom.utils.helper.media

import android.content.Context
import android.net.Uri
import android.util.Log
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import kangparks.android.vostom.BuildConfig
import kangparks.android.vostom.utils.store.getAccessToken

fun getMediaSource(
    context: Context,
    musicId: Int,
): ProgressiveMediaSource {
    val streamUri = "${BuildConfig.base_url}/api/music/stream/${musicId}"

    Log.d("getMediaSource", "streamUri : $streamUri")

    val token = getAccessToken(context) ?: ""

    val mediaItem = MediaItem.Builder().setUri(Uri.parse(streamUri)).build()

    return ProgressiveMediaSource.Factory(createSourceFactory(context, token))
        .createMediaSource(mediaItem)
}