package dreifa.app.types

import java.util.*
import kotlin.collections.ArrayList

/**
 * Some common type safe lists. By avoiding direct use of generics
 * these work better with serialisers as there is no longer the problem
 * of "erasures" losing information.
 */

class StringList(data: List<String>) : ArrayList<String>(data) {
    companion object {
        fun empty() = StringList(emptyList())
        fun listOf(vararg elements: String): StringList = StringList(elements.asList())
    }
}

class LongList(data: List<Long>) : ArrayList<Long>(data) {
    companion object {
        fun empty() = LongList(emptyList())
        fun listOf(vararg elements: Long): LongList = LongList(elements.asList())
    }
}

class DoubleList(data: List<Double>) : ArrayList<Double>(data) {
    companion object {
        fun empty() = DoubleList(emptyList())
        fun listOf(vararg elements: Double): DoubleList = DoubleList(elements.asList())
    }
}

class UUIDList(data: List<UUID>) : ArrayList<UUID>(data) {
    companion object {
        fun empty() = UUIDList(emptyList())
        fun listOf(vararg elements: UUID): UUIDList = UUIDList(elements.asList())
    }
}

class UniqueIdList(data: List<UniqueId>) : ArrayList<UniqueId>(data) {
    companion object {
        fun empty() = UUIDList(emptyList())
        fun listOf(vararg elements: UniqueId): UniqueIdList = UniqueIdList(elements.asList())
    }
}

/**
 * A simple (and slightly inefficient) way of creating a truely immutable
 * list
 */
abstract class SimpleImmutableList<T>(items: List<T>) : List<T> {
    private val items = ArrayList(items) // make a copy to ensure immutability

    override val size: Int
        get() = items.size

    override fun contains(element: T): Boolean = items.contains(element)

    override fun containsAll(elements: Collection<T>): Boolean = items.containsAll(elements)

    override fun get(index: Int): T = items.get(index)

    override fun indexOf(element: T): Int = items.indexOf(element)

    override fun isEmpty(): Boolean = items.isEmpty()

    override fun iterator(): Iterator<T> = items.iterator()

    override fun lastIndexOf(element: T): Int = items.lastIndexOf(element)

    override fun listIterator(): ListIterator<T> = items.listIterator()

    override fun listIterator(index: Int): ListIterator<T> = items.listIterator(index)

    override fun subList(fromIndex: Int, toIndex: Int): List<T> = items.subList(fromIndex, toIndex)

    override fun equals(other: Any?): Boolean {
        return if (other is SimpleImmutableList<*>) {
            this::class == other::class && this.items == other.items
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        return items.hashCode()
    }

    override fun toString(): String {
        return items.toString()
    }
}

class ImmutableStringList(data: List<String>) : SimpleImmutableList<String>(data)
class ImmutableLongList(data: List<Long>) : SimpleImmutableList<Long>(data)
class ImmutableDoubleList(data: List<Double>) : SimpleImmutableList<Double>(data)
class ImmutableUUIDList(data: List<UUID>) : SimpleImmutableList<UUID>(data)
class ImmutableUniqueIdList(data: List<UniqueId>) : SimpleImmutableList<UniqueId>(data)


