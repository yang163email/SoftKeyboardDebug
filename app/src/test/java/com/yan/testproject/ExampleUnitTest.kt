package com.yan.testproject

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test() {
        val str = "a"
        var result = ""
        for (i in (1..65538)) {
            result += str
        }

        println(result)
        println(result.length)
    }
}
