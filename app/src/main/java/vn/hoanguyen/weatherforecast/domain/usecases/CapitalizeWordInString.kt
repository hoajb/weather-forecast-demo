package vn.hoanguyen.weatherforecast.domain.usecases

import javax.inject.Inject

class CapitalizeWordInString @Inject constructor() {
    operator fun invoke(input: String): String {
        return input.split(" ").joinToString(" ") {
            it.replaceFirstChar { char -> if (char.isLowerCase()) char.titlecase() else char.toString() }
        }
    }
}
