package vn.hoanguyen.weatherforecast.domain.usecases

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CapitalizeWordInStringTest {

    private val capitalizeWordInString = CapitalizeWordInString()

    @Test
    fun `invoke should capitalize all lowercase words`() {
        val input = "this is a test"
        val expected = "This Is A Test"
        val result = capitalizeWordInString(input)
        assertEquals(expected, result)
    }

    @Test
    fun `invoke should not change already capitalized words`() {
        val input = "This Is A Test"
        val expected = "This Is A Test"
        val result = capitalizeWordInString(input)
        assertEquals(expected, result)
    }

    @Test
    fun `invoke should handle empty string`() {
        val input = ""
        val expected = ""
        val result = capitalizeWordInString(input)
        assertEquals(expected, result)
    }

    @Test
    fun `invoke should handle mixed case words`() {
        val input = "thiS iS a TeSt"
        val expected = "ThiS IS A TeSt"
        val result = capitalizeWordInString(input)
        assertEquals(expected, result)
    }

    @Test
    fun `invoke should handle single word`() {
        val input = "test"
        val expected = "Test"
        val result = capitalizeWordInString(input)
        assertEquals(expected, result)
    }
}