package dreifa.app.types


/**
 * A generic holder for correlation data - there is a unique id and 'type' which will
 * help in determining which system generated the correlation data.
 *
 */
data class CorrelationContext(
    /**
     * A type that can be used to link the correlation data back to the
     * system that generated the id. An example might be 'org.acme.workflow`
     */
    val type: String,

    /**
     * The correlation data. It must confirm to the rules for UniqueId
     */
    val id: UniqueId,

    /**
     * Should this be propagated between tiers?. By default, no is assumed - if OpenTelemetry
     * is enabled then it is generally enough for just one span to capture and log the correlation
     * data
     */
    val propagate: Boolean = false,

    /**
     * Open Telemetry convention is that consistent attribute names are used
     * by all systems.
     */
    val openTelemetryAttrName: String = "dreifa.app.correlation.$type"
) {
    constructor(type: String, id: String, propagate: Boolean = false) : this(type, UniqueId(id), propagate)

    override fun toString() = "$type-$id"
}


