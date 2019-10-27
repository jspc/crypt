package com.github.jspc.crypt

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class UUIDTest {
    private val testKey = "88b666aa-9545-4c56-a9d6-fd148c4b0e7e"

    @Test
    fun testV4UUID() {
        val uuid = UUID.fromString(testKey)
        val bytes = uuid.bytes()

        assertEquals(136.toByte(), bytes[0])
        assertEquals(126.toByte(), bytes[15])
    }
}
