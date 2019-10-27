package pw.jspc.ksp.crypt

import java.nio.ByteBuffer
import java.util.*

fun UUID.bytes(): ByteArray {
    val bb = ByteBuffer.wrap(ByteArray(16))

    bb.putLong(mostSignificantBits)
    bb.putLong(leastSignificantBits)

    return bb.array()
}
