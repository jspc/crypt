package pw.jspc.ksp.crypt

import org.junit.Assert.assertEquals
import org.junit.Test

class CryptTest {
    private val testKey = "88b666aa-9545-4c56-a9d6-fd148c4b0e7e"
    private val cipherText = "3164a3483d9c55532caa29c4665186d2cd282105f238598aa34d71b81fc29a680c6b7162df91f9e8"
    private val plainText = "hello world!"

    @Test
    fun testDecrypt() {
        val decrypted = Crypt().decrypt(this.testKey, this.cipherText)

        assertEquals(this.plainText, decrypted)
    }
}