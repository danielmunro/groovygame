package com.groovygame.util

import spock.lang.Specification

class UpdateTimerSpec extends Specification {
    def "update timer updates every millisecond"() {
        setup:
        def updateTimer = new UpdateTimer(updateIntervalInMilliseconds: 1)

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
