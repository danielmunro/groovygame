package com.groovygame.matrix

import com.groovygame.map.Climate
import com.groovygame.map.ResourceLevel
import com.groovygame.map.Terrain
import com.groovygame.map.size.SmallMapSize
import com.groovygame.map.topology.FlatMapTopology
import spock.lang.Specification

class TileMatrixSpec extends Specification {
    def "when given a flat topology, tile matrix should generate at least one mountain peak"() {
        when:
        def tm = new TileMatrix(
                new SmallMapSize(),
                new FlatMapTopology(),
                ResourceLevel.LOW,
                Climate.TEMPERATE
        )

        then:
        tm.findInMatrix{
            it.getTerrain() == Terrain.MOUNTAIN_PEAK
        } != null
    }
}
