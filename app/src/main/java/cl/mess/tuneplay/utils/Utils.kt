package cl.mess.tuneplay.utils

import android.annotation.SuppressLint
import cl.mess.tuneplay.utils.Constants.MILLISECONDS_IN_SECOND
import cl.mess.tuneplay.utils.Constants.SECONDS_IN_MINUTE
import java.util.Locale

@SuppressLint("DefaultLocale")
fun formatTime(milliseconds: Long): String {
    val totalSeconds = milliseconds / MILLISECONDS_IN_SECOND
    val minutes = totalSeconds / SECONDS_IN_MINUTE
    val seconds = totalSeconds % SECONDS_IN_MINUTE
    return String.format(
        Locale(
            /* language = */ "es",
            /* country = */ "CL"
        ),
        "%02d:%02d",
        minutes,
        seconds
    )
}
