package com.github.jspc.crypt

import kotlin.experimental.and

infix fun Byte.shl(that: Int): Int = this.toInt().shl(that)
infix fun Int.shl(that: Byte): Int = this.shl(that.toInt()) // Not necessary in this case because no there's (Int shl Byte)
infix fun Byte.shl(that: Byte): Int = this.toInt().shl(that.toInt()) // Not necessary in this case because no there's (Byte shl Byte)

infix fun Byte.and(that: Int): Int = this.toInt().and(that)
infix fun Int.and(that: Byte): Int = this.and(that.toInt()) // Not necessary in this case because no there's (Int and Byte)
infix fun Byte.and(that: Byte): Int = this.toInt().and(that.toInt()) // Not necessary in this case because no there's (Byte and Byte)

infix fun Byte.shr(that: Int): Int = this.toInt().shr(that)
infix fun Int.shr(that: Byte): Int = this.shr(that.toInt()) // Not necessary in this case because no there's (Int shl Byte)
infix fun Byte.shr(that: Byte): Int = this.toInt().shr(that.toInt()) // Not necessary in this case because no there's (Byte shl Byte)

fun hexToByte(hexString: String): Byte {
    val firstDigit = toDigit(hexString[0])
    val secondDigit = toDigit(hexString[1])

    return ((firstDigit shl 4) + secondDigit).toByte()
}

fun byteToHex(num: Byte): String {
    val hexDigits = CharArray(2)
    hexDigits[0] = Character.forDigit(num shr 4 and 0xF, 16)
    hexDigits[1] = Character.forDigit((num and 0xF).toInt(), 16)
    return String(hexDigits)
}

fun toDigit(hexChar: Char): Int {
    val digit = Character.digit(hexChar, 16)
    require(digit != -1) { "Invalid Hexadecimal Character: $hexChar" }

    return digit
}

fun deHex(hexString: String): ByteArray {
    val bytes = ByteArray(hexString.length / 2)

    var i = 0
    while (i < hexString.length) {
        bytes[i / 2] = hexToByte(hexString.substring(i, i + 2))
        i += 2
    }
    return bytes
}

fun toHex(byteArray: ByteArray): String {
    val hexStringBuffer = StringBuffer()
    for (i in byteArray.indices) {
        hexStringBuffer.append(byteToHex(byteArray[i]))
    }
    return hexStringBuffer.toString()
}
