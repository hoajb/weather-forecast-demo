package vn.hoanguyen.weatherforecast.domain.exceptions

import vn.hoanguyen.weatherforecast.domain.models.ErrorModel


object NoConnectivityException : RuntimeException()

data class ApiException(
    val error: ErrorModel?,
    val httpCode: Int,
    val httpMessage: String?
) : RuntimeException()