package kangparks.android.vostom.utils.helper.media

fun formatTime(milliseconds: Long): String {
    if(milliseconds == null) return "00:00"
    val totalSeconds = milliseconds / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return String.format("%02d:%02d", minutes, seconds)
}