package com.groovygame.util

import spock.lang.Specification

class UpdateTimerSpec extends Specification {
    def "update timer updates every millisecond"() {
        setup:
        def incrementor = 1
        def updateTimer = new UpdateTimer(1)

        expect:
        !updateTimer.isReadyForUpdate()

        when:
        updateTimer.addMilliseconds(1)
        then:
        updateTimer.isReadyForUpdate()

        when:
        updateTimer.resetUpdateCounter()
        then:
        !updateTimer.isReadyForUpdate()

        when:
        updateTimer.addMilliseconds(0)
        then:
        !updateTimer.isReadyForUpdate()

        when:
        updateTimer.addMilliseconds(1)
        then:
        updateTimer.isReadyForUpdate()
    }
}
