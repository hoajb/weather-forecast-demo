package vn.hoanguyen.weatherforecast.presentation.base

import android.content.Context
import vn.hoanguyen.weatherforecast.R
import vn.hoanguyen.weatherforecast.app.extensions.showToast
import vn.hoanguyen.weatherforecast.domain.exceptions.ApiException

fun Throwable.userReadableMessage(context: Context): String {
    return when (this) {
        is ApiException -> error?.message
        else -> message
    } ?: context.getString(R.string.error_generic)
}

fun Throwable.showToast(context: Context) =
    context.showToast(userReadableMessage(context))