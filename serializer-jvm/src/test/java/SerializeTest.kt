import com.android.dependencies.serializer.SerializeReader
import com.android.dependencies.serializer.SerializeWriter
import org.junit.Assert
import org.junit.Test

/**
 * @author liuzhongao
 * @since 2024/3/6 17:41
 */
class SerializeTest {

    @Test
    fun intSerializeTest() {
        val serializeWriter = SerializeWriter()
        serializeWriter.writeInt(10)

        val serializeReader = SerializeReader(serializeWriter.bytePackage.byteArray)
        val number = serializeReader.readInt()
        Assert.assertEquals(number, 10)
    }

    @Test
    fun intArraySerializeTest() {
        val array = intArrayOf(-121, 2, 3, 4, 5)
        val serializeWriter = SerializeWriter()
        serializeWriter.writeIntArray(array)

        val serializeReader = SerializeReader(serializeWriter.bytePackage.byteArray)
        val tempArray = serializeReader.readIntArray()
        Assert.assertArrayEquals(array, tempArray)
    }

    @Test
    fun listSerializeTest() {
        val list: List<Any?> = listOf(1, 2, 2.2, 4, null, 5)
        val serializeWriter = SerializeWriter()
        serializeWriter.writeList(list)

        val serializeReader = SerializeReader(serializeWriter.bytePackage.byteArray)
        val tempList = serializeReader.readList<Any?>()
        Assert.assertEquals(list.size, tempList?.size)
        for (index in list.indices) {
            Assert.assertEquals(list[index], tempList?.get(index))
        }
    }

    @Test
    fun charArraySerializeTest() {
        val array = charArrayOf('a', 'b', 'c', 'd', 'e', 'f', 'g')
        val serializeWriter = SerializeWriter()
        serializeWriter.writeCharArray(array)

        val serializeReader = SerializeReader(serializeWriter.bytePackage.byteArray)
        val tempArray = serializeReader.readCharArray()
        Assert.assertArrayEquals(array, tempArray)
    }

    @Test
    fun stringSerializeTest() {
        val source = "liuzhongao"
        val serializeWriter = SerializeWriter()
        serializeWriter.writeString(source)

        val serializeReader = SerializeReader(serializeWriter.bytePackage.byteArray)
        val tempArray = serializeReader.readString()
        Assert.assertEquals(source, tempArray)
    }

    @Test
    fun packableTest() {
        val source = TestObject("liuzhongao", 22, "liuzhongao".toByteArray())
        val serializeWriter = SerializeWriter()
        serializeWriter.writePackable(source)

        val serializeReader = SerializeReader(serializeWriter.bytePackage.byteArray)
        val tempSource = serializeReader.readPackable<TestObject>()
        Assert.assertEquals(source, tempSource)
    }

    @Test
    fun packableSizeTest() {
        val source = TestObject("liuzhongao1231231231liuzhongao1231231231liuzhongao1231231231liuzhongao1231231231liuzhongao1231231231liuzhongao1231231231liuzhongao1231231231liuzhongao1231231231liuzhongao1231231231liuzhongao1231231231liuzhongao1231231231liuzhongao1231231231liuzhongao1231231231liuzhongao1231231231liuzhongao1231231231liuzhongao1231231231", 22, "liuzhongao122222234sdfasfwef".toByteArray())
        val serializeWriter = SerializeWriter()
        serializeWriter.writePackable(source)

        val serializeWriter1 = SerializeWriter()
        serializeWriter1.writeSerializable(source)

        val packableSize = serializeWriter.bytePackage.byteArray.size
        val serializableSize = serializeWriter1.bytePackage.byteArray.size

        Assert.assertTrue(packableSize < serializableSize)
    }

}