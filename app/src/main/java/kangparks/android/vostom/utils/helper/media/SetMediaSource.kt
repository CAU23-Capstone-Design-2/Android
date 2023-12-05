package kangparks.android.vostom.utils.helper.media

import android.content.Context
import android.net.Uri
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import kangparks.android.vostom.BuildConfig

fun getMediaSource(
    context: Context,
    token: String,
    musicId: Int,
): ProgressiveMediaSource {
    val streamUri = "${BuildConfig.base_url}/api/music/stream/${musicId}"

    val mediaItem = MediaItem.Builder().setUri(Uri.parse(streamUri)).build()

    return ProgressiveMediaSource.Factory(createSourceFactory(context, token))
        .createMediaSource(mediaItem)
}