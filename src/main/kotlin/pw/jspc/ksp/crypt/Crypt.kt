package pw.jspc.ksp.crypt

import javax.crypto.Cipher
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.SecretKeySpec

class Crypt {
    private val charset = Charsets.UTF_8
    private val cipher = Cipher.getInstance("AES/GCM/NoPadding")

    private fun parseKey(key: String): SecretKeySpec {
        return SecretKeySpec(key.toByteArray(charset), "AES")
    }

    private fun hexToByte(hexString: String): Byte {
        val firstDigit = toDigit(hexString[0])
        val secondDigit = toDigit(hexString[1])

        return ((firstDigit shl 4) + secondDigit).toByte()
    }

    private fun toDigit(hexChar: Char): Int {
        val digit = Character.digit(hexChar, 16)
        require(digit != -1) { "Invalid Hexadecimal Character: $hexChar" }

        return digit
    }

    private fun deHex(hexString: String): ByteArray {
        val bytes = ByteArray(hexString.length / 2)

        var i = 0
        while (i < hexString.length) {
            bytes[i / 2] = hexToByte(hexString.substring(i, i + 2))
            i += 2
        }
        return bytes
    }

    fun decrypt(key: String, message: String): String {
        val sks = parseKey(key)
        val deHexed: ByteArray = deHex(message)

        val params = GCMParameterSpec(128, deHexed, 0, 12)

        cipher.run {
            init(Cipher.DECRYPT_MODE, sks, params)
        }
        return cipher.doFinal(deHexed, 12, message.length - 12).toString()
    }
}