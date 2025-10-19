package dreifa.app.xunitpatterns.spy

import java.util.concurrent.ConcurrentLinkedQueue

/**
 * A very basic Spy - see http://xunitpatterns.com/Test%20Spy.html
 */
class Spy {
    private val secrets = ConcurrentLinkedQueue<String>()

    fun spy(secret: String): Spy {
        secrets.add(secret)
        return this
    }

    fun secrets(): List<String> = secrets.toList()

    override fun equals(other: Any?): Boolean {
        return if (other is Spy) secrets() == secrets() else false
    }

    override fun hashCode(): Int {
        return secrets.hashCode()
    }
}