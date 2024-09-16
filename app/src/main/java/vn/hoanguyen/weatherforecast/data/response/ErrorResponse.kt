package vn.hoanguyen.weatherforecast.data.response

import vn.hoanguyen.weatherforecast.domain.models.ErrorModel

data class ErrorResponse(
    val message: String
)

internal fun ErrorResponse.toModel() = ErrorModel(message = message)