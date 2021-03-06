package dreifa.app.types

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.lang.RuntimeException

class KeyTests {

    @Test
    fun `should throw exception if too long`() {
        assertDoesNotThrow { Key.fromString("a".repeat(255)) }
        assertThrows<RuntimeException> { Key.fromString("a".repeat(256)) }
    }

    @Test
    fun `should throw exception if empty`() {
        assertThrows<RuntimeException> { Key.fromString("") }
        assertDoesNotThrow { Key.fromString("a") }
    }

    @Test
    fun `should allow all supported characters`() {
        assertDoesNotThrow { Key.fromString("abcdefghijklmnopqrstuvwyyz") }
        assertDoesNotThrow { Key.fromString("abcdefghijklmnopqrstuvwyyz".uppercase()) }
        assertDoesNotThrow { Key.fromString("1234567890") }
        assertDoesNotThrow { Key.fromString("-_:/") }
    }

    @Test
    fun `should build from UniqueId`() {
        val id = UniqueId.randomUUID()
        val key = Key.fromUniqueId(id)
        assertDoesNotThrow { Key.fromUniqueId(UniqueId.randomUUID()) }
        assertThat(Key.fromUniqueId(id), equalTo(key))
        assertThat(id.toString(), equalTo(key.toString()))
    }

    @Test
    fun `should fail for unsupported characters`() {
        // TODO - a better way of generating sets of invalid characters
        val unsupported = "!@£$%^&*()+=;|\\?><.,Ö"
        unsupported.toCharArray().forEach {
            assertThrows<RuntimeException> { Key.fromString(it.toString()) }
        }
    }
}