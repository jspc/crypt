package com.github.jspc.crypt

import java.nio.ByteBuffer
import java.security.SecureRandom
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.SecretKeySpec

class Crypt {
    private val charset = Charsets.UTF_8
    private val cipher = Cipher.getInstance("AES/GCM/NoPadding")

    private fun parseKey(key: String): SecretKeySpec {
        val uuid = UUID.fromString(key).bytes()

        return SecretKeySpec(uuid, "AES")
    }

    fun encrypt(key: String, plaintext: String): String {
        val sks = parseKey(key)

        val secureRandom = SecureRandom()
        val nonce = ByteArray(12)
        secureRandom.nextBytes(nonce)

        val params = GCMParameterSpec(128, nonce)
        cipher.run {
            init(Cipher.ENCRYPT_MODE, sks, params)
        }

        val enciphered = cipher.doFinal(plaintext.toByteArray())

        val outputBB = ByteBuffer.allocate(nonce.size + enciphered.size)
        outputBB.put(nonce)
        outputBB.put(enciphered)

        return toHex(outputBB.array())
    }

    fun decrypt(key: String, message: String): String {
        val sks = parseKey(key)
        val deHexed: ByteArray = deHex(message)
        val params = GCMParameterSpec(128, deHexed, 0, 12)

        cipher.run {
            init(Cipher.DECRYPT_MODE, sks, params)
        }

        return cipher.doFinal(deHexed, 12, deHexed.size - 12).toString(charset)
    }
}