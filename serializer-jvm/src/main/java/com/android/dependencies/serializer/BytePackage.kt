package com.android.dependencies.serializer

/**
 * @author liuzhongao
 * @since 2024/4/30 10:36
 */
data class BytePackage(val byteArray: ByteArray) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BytePackage

        if (!byteArray.contentEquals(other.byteArray)) return false

        return true
    }

    override fun hashCode(): Int {
        return byteArray.contentHashCode()
    }
}
