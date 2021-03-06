package dreifa.app.types

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.sameInstance
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.RuntimeException
import java.lang.StringBuilder

class MapOfAnyTests {
    private val testMap = mapOf(
        "aString" to "foo",
        "anInt" to 1,
        "aLong" to 2,
        "anIntString" to "3",
        "aNull" to null,
        "aString" to "string",
        "aStringBuffer" to StringBuffer("buffer"),
        "aStringBuilder" to StringBuilder("builder"),
    ).toMapOfAny()

    @Test
    fun `should return MapOfAny`() {
        val data = mapOf("name" to "Bob", "age" to 21)
        assertThat(data.toMapOfAny(), sameInstance(data))
    }

    @Test
    fun `should not return MapOfAny if has null keys`() {
        val data = mapOf("name" to "Bob", null to 21)
        assertThrows<RuntimeException> { data.toMapOfAny() }
    }

    @Test
    fun `should unpack Int`() {
        assertEquals(1, testMap.unpackInt("anInt"))
        assertEquals(2, testMap.unpackInt("aLong"))
        assertEquals(3, testMap.unpackInt("anIntString"))
        assertThrows<RuntimeException> { testMap.unpackInt("aString") }
        assertThrows<RuntimeException> { testMap.unpackInt("aNull") }
    }

    @Test
    fun `should unpack Long`() {
        assertEquals(1L, testMap.unpackLong("anInt"))
        assertEquals(2L, testMap.unpackLong("aLong"))
        assertEquals(3L, testMap.unpackLong("anIntString"))
        assertThrows<RuntimeException> { testMap.unpackLong("aString") }
        assertThrows<RuntimeException> { testMap.unpackLong("aNull") }
    }

    @Test
    fun `should unpack String`() {
        assertEquals("string", testMap.unpackString("aString"))
        assertEquals("buffer", testMap.unpackString("aStringBuffer"))
        assertEquals("builder", testMap.unpackString("aStringBuilder"))
        assertThrows<RuntimeException> { testMap.unpackString("anInt") }
        assertThrows<RuntimeException> { testMap.unpackString("aNull") }
    }

    @Test
    fun `should castTo`() {
        val aString: String = testMap.castTo("aString")
        assertThat("string", equalTo(aString))
        val anInt: Int = testMap.castTo("anInt")
        assertThat(1, equalTo(anInt))

        assertThrows<RuntimeException> {
            val dummy: String = testMap.castTo("anInt")
            dummy.length  // stop compiler generating an unused param warning
        }
    }

}