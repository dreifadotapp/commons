package dreifa.app.clock

/**
 * Some basic timer primitives that behave appropriately
 * depending on the OS / hardware
 */
object PlatformTimer {

    /**
     * Should match the granularity of the underlying
     * clock "tick" on the platform. The idea is to be able to tune
     * sleeps in tests efficiently. For example on the original PC the
     * clock ticker 18.2 times a second, so the min time to wait is approx 1000/18. Thank
     * fully modern computers it normally less than this is
     */
    fun clockTick(): Long {
        val os = System.getProperty("os.name")
        val circleCI = "true" == System.getenv("CIRCLECI")

        // tune as required - it might need some more detailed analysis
        // of the underlying hardware
        return when (os) {
            "Mac OS X" -> 1 //  :-)
            "Linux" -> {
                if (circleCI) 20 else 5 //  currently tuned for CircleCI
            }
            else -> 50
        }
    }

    fun sleepForTicks(ticks: Int) = Thread.sleep(clockTick() * ticks)

    /**
     * The wait delay to apply in tests before checking asserts. Current rule
     * is however many internal ticks are in the test case + a margin
     * of 2 ticks
     */
    fun testDelayInTicks(ticksTaken: Int): Int = 2 + ticksTaken

    fun testDelayInMs(ticksTaken: Int): Long = testDelayInTicks(ticksTaken) * clockTick()
}

data class PlatformTick(private val ticks: Int) {
    fun ticks(): Int = ticks
    fun milliseconds(): Long = ticks * PlatformTimer.clockTick()

    companion object {
        fun of(ticks: Int): PlatformTick = PlatformTick(ticks)
    }
}