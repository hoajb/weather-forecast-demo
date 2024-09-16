package vn.hoanguyen.weatherforecast.data.extensions

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import vn.hoanguyen.weatherforecast.data.response.ErrorResponse
import vn.hoanguyen.weatherforecast.data.response.toModel
import vn.hoanguyen.weatherforecast.domain.exceptions.ApiException
import vn.hoanguyen.weatherforecast.domain.exceptions.NoConnectivityException
import java.io.IOException
import java.io.InterruptedIOException
import java.net.UnknownHostException
import kotlin.experimental.ExperimentalTypeInference

@OptIn(ExperimentalTypeInference::class)
fun <T> flowTransform(@BuilderInference block: suspend FlowCollector<T>.() -> T) = flow {
    runCatching { block() }
        .onSuccess { result -> emit(result) }
        .onFailure { exception -> throw exception.mapError() }
}

private fun Throwable.mapError(): Throwable {
    return when (this) {
        is UnknownHostException,
        is InterruptedIOException -> NoConnectivityException

        is HttpException -> {
            val errorResponse = parseErrorResponse(response())
            ApiException(
                errorResponse?.toModel(),
                code(),
                message()
            )
        }

        else -> this
    }
}


private fun parseErrorResponse(response: Response<*>?): ErrorResponse? {
    val jsonString = response?.errorBody()?.string()
    return try {
        val gson = Gson()
        gson.fromJson(jsonString, ErrorResponse::class.java)
    } catch (exception: IOException) {
        null
    } catch (exception: JsonSyntaxException) {
        null
    }
}