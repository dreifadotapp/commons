package dreifa.app.chaos

import dreifa.app.clock.PlatformTick

/**
 * Code used in the docs
 */

class ChaosDocExamples {

    fun `example1`() {
        val chaos = dreifa.app.chaos.Chaos(
            listOf(
                // average delay of 1 "platform" tick (tuned to the basic tick rate on the OS/Hardware)
                dreifa.app.chaos.DelayUptoNTicks(PlatformTick.of(2)),
                // fail statistically 1 in 10 timed
                dreifa.app.chaos.FailNPercent(10)

            )
        )
        val testDouble = TestDouble(chaos)

        // calls to foo method now have the injected chaotic behaviors
        testDouble.foo()
    }

}

class TestDouble(private val chaos: dreifa.app.chaos.Chaos) {
    fun foo(): String {
        chaos.chaos()
        return "bar"
    }
}


class TestDouble2(private val chaos: dreifa.app.chaos.Chaos) {
    fun foo(): String {
        chaos.chaos("errors")
        chaos.chaos("delays")
        return "bar"
    }
}


