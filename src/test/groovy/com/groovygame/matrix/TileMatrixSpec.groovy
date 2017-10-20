package com.groovygame.matrix

import com.groovygame.map.Climate
import com.groovygame.map.ResourceLevel
import com.groovygame.map.Terrain
import com.groovygame.map.size.LargeMapSize
import com.groovygame.map.size.SmallMapSize
import com.groovygame.map.topology.FlatMapTopology
import com.groovygame.map.topology.MountainousMapTopology
import spock.lang.Specification

class TileMatrixSpec extends Specification {
    def "when given a flat topology and a small map size, tile matrix should generate at least one mountain peak, and not more than two"() {
        when:
        def tm = new TileMatrix(
                new SmallMapSize(),
                new FlatMapTopology(),
                ResourceLevel.LOW,
                Climate.TEMPERATE
        )

        then:
        def size = tm.findAllInMatrix{
            it.getTerrain() == Terrain.MOUNTAIN_PEAK
        }.size()
        0 < size
        size <= 3
    }

    def "when given a mountainous topology and a large map size, tile matrix should generate many mountain peaks"() {
        when:
        def tm = new TileMatrix(
                new LargeMapSize(),
                new MountainousMapTopology(),
                ResourceLevel.LOW,
                Climate.TEMPERATE
        )

        then:
        tm.findAllInMatrix{
            it.getTerrain() == Terrain.MOUNTAIN_PEAK
        }.size() >= new MountainousMapTopology().getMountainPeakModifier()
    }
}
