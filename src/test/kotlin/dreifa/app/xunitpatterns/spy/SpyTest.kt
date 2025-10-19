package dreifa.app.xunitpatterns.spy

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.concurrent.thread

class SpyTest {

    @Test
    fun `should spy on events`() {
        val spy = Spy()
        spy.spy("action1")
        spy.spy("action2")
        spy.spy("action3")
        Assertions.assertEquals(listOf("action1", "action2", "action3"), spy.secrets())
    }

    @Test
    fun `should be thread safe`() {
        val spy = Spy()

        val threads = (1..10).map { thread ->
            thread {
                (1..100).forEach { action->
                    spy.spy("thread$thread-$action")
                }
            }
        }
        threads.forEach { it.join() }

        Assertions.assertEquals(1000, spy.secrets().size)

        val expected = mutableListOf<String>()
        (1..10).forEach { thread ->
            (1..100).forEach { action ->
                expected.add("thread$thread-$action")
            }
        }

        Assertions.assertEquals(expected.toSet(), spy.secrets().toSet())
    }
}