package com.groovygame.pathfinding

import com.groovygame.map.Layer
import com.groovygame.map.exception.ImpossiblePathException
import com.groovygame.pathfinding.LayerSearch
import com.groovygame.util.Coords
import spock.lang.Specification

class LayerSearchSpec extends Specification {
    def "simple case with blocking data"() {
        setup:
        def layer = new Layer(data:[
                [0, 0, 0, 0, 0, 0],
                [0, 0, 0, 0, 0, 0],
                [0, 0, 0, 1, 0, 0],
                [0, 0, 1, 0, 0, 0],
                [0, 1, 0, 0, 0, 0],
                [0, 0, 0, 0, 0, 0]
        ])
        def pathfinder = new LayerSearch(layer)

        expect:
        pathfinder.find(new Coords(2, 1), new Coords(2, 4)) == [
                Coords.at(2, 1),
                Coords.at(3, 1),
                Coords.at(4, 1),
                Coords.at(4, 2),
                Coords.at(4, 3),
                Coords.at(3, 3),
                Coords.at(3, 4),
                Coords.at(2, 4)
        ]
    }

    def "another test case"() {
        setup:
        def layer = new Layer(data:[
                [0, 0, 1, 0, 0, 0],
                [0, 0, 0, 0, 1, 0],
                [0, 0, 1, 1, 1, 0],
                [0, 0, 0, 0, 1, 0],
                [1, 1, 1, 0, 0, 0],
                [0, 0, 0, 0, 0, 0]
        ])
        def search = new LayerSearch(layer)

        expect:
        search.find(new Coords(5, 5), new Coords(0, 0)) == [
                Coords.at(5, 5),
                Coords.at(4, 5),
                Coords.at(3, 5),
                Coords.at(3, 4),
                Coords.at(3, 3),
                Coords.at(2, 3),
                Coords.at(1, 3),
                Coords.at(0, 3),
                Coords.at(0, 2),
                Coords.at(0, 1),
                Coords.at(0, 0)
        ]
    }

    def "simple case no blocking data"() {
        setup:
        def layer = new Layer(data:[
                [0, 0, 0, 0, 0, 0],
                [0, 0, 0, 0, 0, 0],
                [0, 0, 0, 0, 0, 0],
                [0, 0, 0, 0, 0, 0],
                [0, 0, 0, 0, 0, 0],
                [0, 0, 0, 0, 0, 0]
        ])
        def pathfinder = new LayerSearch(layer)

        expect:
        pathfinder.find(new Coords(0, 0), new Coords(5, 4)) == [
                Coords.at(0, 0),
                Coords.at(1, 0),
                Coords.at(2, 0),
                Coords.at(3, 0),
                Coords.at(4, 0),
                Coords.at(5, 0),
                Coords.at(5, 1),
                Coords.at(5, 2),
                Coords.at(5, 3),
                Coords.at(5, 4),
        ]
    }

    def "no valid path"() {
        setup:
        def layer = new Layer(data:[
                [0, 0, 0, 0, 0, 0],
                [0, 1, 0, 0, 0, 0],
                [0, 0, 1, 1, 1, 1],
                [0, 0, 1, 0, 1, 0],
                [0, 1, 0, 0, 1, 0],
                [0, 0, 0, 0, 1, 0]
        ])
        def pathfinder = new LayerSearch(layer)

        when:
        pathfinder.find(new Coords(0, 0), new Coords(5, 3))

        then:
        ImpossiblePathException e = thrown()
    }

    def "nontrivial blocking data to navigate"() {
        setup:
        def layer = new Layer(data:[
                [0, 1, 0, 1, 0, 1, 1],
                [0, 0, 0, 1, 0, 1, 0],
                [0, 1, 1, 1, 0, 1, 0],
                [0, 0, 1, 0, 0, 1, 0],
                [1, 0, 1, 0, 1, 1, 0],
                [1, 0, 0, 0, 0, 0, 0]
        ])
        def pathfinder = new LayerSearch(layer)

        expect:
        pathfinder.find(new Coords(0, 0), new Coords(6, 1)) == [
                Coords.at(0, 0),
                Coords.at(0, 1),
                Coords.at(0, 2),
                Coords.at(0, 3),
                Coords.at(1, 3),
                Coords.at(1, 4),
                Coords.at(1, 5),
                Coords.at(2, 5),
                Coords.at(3, 5),
                Coords.at(4, 5),
                Coords.at(5, 5),
                Coords.at(6, 5),
                Coords.at(6, 4),
                Coords.at(6, 3),
                Coords.at(6, 2),
                Coords.at(6, 1)
        ]
    }

}
