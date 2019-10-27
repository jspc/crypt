package pw.jspc.ksp.crypt

import org.junit.Assert.assertEquals
import org.junit.Test

class CryptTest {
    private val testKey = "88b666aa-9545-4c56-a9d6-fd148c4b0e7e"
    private val cipherText = "335db7b1670650acde15af09a6d9db25d92d319e25a76842eebc8411c669dc0cf4e19b62396e43a0"
    private val plainText = "hello world!"

    @Test
    fun testEncrypt() {
        val encrypted = Crypt().encrypt(this.testKey, this.plainText)
        print("encrypted hex: ${encrypted}\n\n")

        val decrypted = Crypt().decrypt(this.testKey, encrypted)

        assertEquals(this.plainText, decrypted)
    }

    @Test
    fun testDecrypt() {
        val decrypted = Crypt().decrypt(this.testKey, this.cipherText)

        assertEquals(this.plainText, decrypted)
    }

    @Test
    fun testDeHex() {
        val input = "48656c6c6f2c20706c617967726f756e64"
        val expect = "Hello, playground".toByteArray()

        assert(deHex(input) contentEquals expect)
    }
}