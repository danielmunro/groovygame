package com.groovygame.matrix

import com.groovygame.util.Coords
import spock.lang.Specification

class MatrixSpec extends Specification {
    def "given an x and a y, return a matrix of the requested size"() {
        when:
        def m = new Matrix(5, 5)

        then:
        m.getWidth() == 5
        m.getHeight() == 5
    }

    def "matrices should be mutable"() {
        when:
        def m = new Matrix(5, 5)
        m.setValueAtCoords(Coords.at(3, 2), 'foo')

        then:
        m.getValueAtCoords(Coords.at(3, 2)) == 'foo'
    }
}
