package com.groovygame.map

import com.groovygame.util.Coords
import spock.lang.Specification

class PathfinderSpec extends Specification {
    def "pathfind me simple case with blocking data"() {
        setup:
        def layer = new Layer(data:[
                [0, 0, 0, 0, 0, 0],
                [0, 0, 0, 0, 0, 0],
                [0, 0, 0, 1, 0, 0],
                [0, 0, 1, 0, 0, 0],
                [0, 1, 0, 0, 0, 0],
                [0, 0, 0, 0, 0, 0]
        ])
        def pathfinder = new Pathfinder(layer)

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

    def "pathfind me simple case no blocking data"() {
        setup:
        def layer = new Layer(data:[
                [0, 0, 0, 0, 0, 0],
                [0, 0, 0, 0, 0, 0],
                [0, 0, 0, 0, 0, 0],
                [0, 0, 0, 0, 0, 0],
                [0, 0, 0, 0, 0, 0],
                [0, 0, 0, 0, 0, 0]
        ])
        def pathfinder = new Pathfinder(layer)

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

    def "pathfind me no valid path"() {
        setup:
        def layer = new Layer(data:[
                [0, 0, 0, 0, 0, 0],
                [0, 1, 0, 0, 0, 0],
                [0, 0, 1, 1, 1, 1],
                [0, 0, 1, 0, 1, 0],
                [0, 1, 0, 0, 1, 0],
                [0, 0, 0, 0, 1, 0]
        ])
        def pathfinder = new Pathfinder(layer)

        expect:
        pathfinder.find(new Coords(0, 0), new Coords(5, 3)) == []
    }

    def "pathfind me nontrivial"() {
        setup:
        def layer = new Layer(data:[
                [0, 1, 0, 1, 0, 1, 1],
                [0, 0, 0, 1, 0, 1, 0],
                [0, 1, 1, 1, 0, 1, 0],
                [0, 0, 1, 0, 0, 1, 0],
                [1, 0, 1, 0, 1, 1, 0],
                [1, 0, 0, 0, 0, 0, 0]
        ])
        def pathfinder = new Pathfinder(layer)

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
