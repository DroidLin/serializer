package com.android.dependencies.serializer

import java.io.Closeable
import java.io.Serializable

/**
 * @author liuzhongao
 * @since 2024/3/6 15:49
 */
interface ReadOnlyByteChannel : Closeable {

    fun <T> readValue(): T?

    fun readByte(): Byte

    fun readInt(): Int

    fun readLong(): Long

    fun readDouble(): Double

    fun readChar(): Char

    fun readFloat(): Float

    fun readShort(): Short

    fun readBoolean(): Boolean

    fun readString(): String?

    fun readSerializable(): Serializable?

    fun readByteArray(): ByteArray?

    fun readIntArray(): IntArray?

    fun readLongArray(): LongArray?

    fun readDoubleArray(): DoubleArray?

    fun readCharArray(): CharArray?

    fun readFloatArray(): FloatArray?

    fun readShortArray(): ShortArray?

    fun <T> readArray(): Array<T>?

    fun <T> readList(): List<T>?

    fun <T : Packable> readPackable(): T?

}