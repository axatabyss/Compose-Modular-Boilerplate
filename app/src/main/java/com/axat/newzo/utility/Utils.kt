package com.axat.newzo.utility

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun formatDate(dateString: String): AnnotatedString {
    val formatter = DateTimeFormatter.ofPattern("d MMM yyyy hh:mm:ss a")
    val parsedDateTime = ZonedDateTime.parse(dateString).toLocalDateTime()
    val formattedDate = parsedDateTime.format(formatter)

    return buildAnnotatedString {
        withStyle(SpanStyle()) {
            append(formattedDate)
        }
    }
}