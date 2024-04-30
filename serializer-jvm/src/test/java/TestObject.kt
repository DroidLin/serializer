import com.android.dependencies.serializer.ReadOnlyByteChannel
import com.android.dependencies.serializer.WriteOnlyByteChannel
import com.android.dependencies.serializer.Packable

/**
 * @author liuzhongao
 * @since 2024/4/30 11:14
 */
data class TestObject(
    val name: String,
    val age: Int,
    val byteArray: ByteArray
) : Packable {

    constructor(reader: ReadOnlyByteChannel) : this(
        name = requireNotNull(reader.readString()),
        age = requireNotNull(reader.readInt()),
        byteArray = requireNotNull(reader.readByteArray())
    )

    override fun zipToWriter(writer: WriteOnlyByteChannel) {
        writer.writeString(this.name)
        writer.writeInt(this.age)
        writer.writeByteArray(this.byteArray)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TestObject

        if (name != other.name) return false
        if (age != other.age) return false
        if (!byteArray.contentEquals(other.byteArray)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + age
        result = 31 * result + byteArray.contentHashCode()
        return result
    }

    companion object {
        private const val serialVersionUID: Long = 6759734588752146137L

        @JvmField
        val CREATOR = object : Packable.Creator {
            override fun <T : Packable> newInstance(reader: ReadOnlyByteChannel): T {
                return TestObject(reader) as T
            }
        }
    }
}