package kangparks.android.vostom.utils.builder.media

import android.content.Context
import android.net.Uri
import com.google.android.exoplayer2.MediaItem

fun getMediaItem(
    context : Context,
    resName : String,
    defType : String,
) : MediaItem{
    val packageName = context.packageName
    val resId = context.resources.getIdentifier(resName, defType, context.packageName)
    val uriPath = "android.resource://$packageName/$resId"

    return MediaItem.Builder().setUri(Uri.parse(uriPath)).build()
}