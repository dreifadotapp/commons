package dreifa.app.types

/**
 * A type safe list of CorrelationContext for convenience and to support
 * Simple Serialisation (https://github.com/dreifadotapp/simple-serialisation)
 */
class CorrelationContexts(data: List<CorrelationContext>) : ArrayList<CorrelationContext>(data) {
    companion object {

        fun empty() = CorrelationContexts(emptyList())

        fun listOf(vararg elements: CorrelationContext): CorrelationContexts = CorrelationContexts(elements.asList())

        fun single(type: String, id: UniqueId, propagate: Boolean = false): CorrelationContexts {
            return single(CorrelationContext(type, id, propagate))
        }

        fun single(type: String, id: String, propagate: Boolean = false): CorrelationContexts {
            return single(CorrelationContext(type, id, propagate))
        }

        fun single(correlation: CorrelationContext): CorrelationContexts {
            return listOf(correlation)
        }
    }
}
