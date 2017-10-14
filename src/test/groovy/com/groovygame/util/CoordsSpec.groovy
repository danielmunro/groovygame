package com.groovygame.util

import spock.lang.Specification

class CoordsSpec extends Specification {
    def "sanity test coords equals"() {
        expect:
        new Coords(1, 1) == new Coords(1, 1)
        new Coords(1, 1) != new Coords(2, 1)
    }
}
