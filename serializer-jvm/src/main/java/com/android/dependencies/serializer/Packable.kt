package com.android.dependencies.serializer

/**
 * @author liuzhongao
 * @since 2024/4/30 10:30
 */
interface Packable : java.io.Serializable {

    fun unzipFromReader(reader: ReadOnlyByteChannel) {}

    fun zipToWriter(writer: WriteOnlyByteChannel)

    interface Creator {

        fun <T : Packable> newInstance(reader: ReadOnlyByteChannel): T
    }

    companion object {
        private const val serialVersionUID: Long = -90000113L
    }
}