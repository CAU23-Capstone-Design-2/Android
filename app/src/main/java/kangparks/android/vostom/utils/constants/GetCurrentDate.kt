package kangparks.android.vostom.utils.constants

import java.text.SimpleDateFormat
import java.util.Date

fun getCurrentDate() : String{
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val currentDate = Date()
    return sdf.format(currentDate)
}