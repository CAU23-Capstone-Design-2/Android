package kangparks.android.vostom.components.player

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kangparks.android.vostom.models.content.Music
import kangparks.android.vostom.utils.helper.transform.BlurTransformation

@Composable
fun ContentPlayerBackground(
    currentSong : State<Music?>,
){
    val context = LocalContext.current

    val request = ImageRequest.Builder(context)
        .data(currentSong.value?.albumArtUri ?: null)
        .transformations(
            listOf(
                BlurTransformation(
                    scale = 0.5f,
                    radius = 25
                )
            )
        )
        .build()

    Box {
        AsyncImage(
            model = request,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop,
            alpha = 0.7f,
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .alpha(0.5f)
                .background(Color.Black)
        ) {}
    }
}