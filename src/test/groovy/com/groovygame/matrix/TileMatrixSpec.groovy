package com.groovygame.matrix

import com.groovygame.map.topology.MountainousMapTopology
import spock.lang.Specification

class TileMatrixSpec extends Specification {
    def "when given a flat topology and a small map size, tile matrix should generate at least one mountain peak, and not more than two"() {
        when:
        def tm = TileMatrixFactory.getNewSmallFlatMap()

        then:
        def size = TileMatrixFinder.findAllMountainPeaks(tm).size()
        0 < size
        size <= 3
    }

    def "when given a mountainous topology and a large map size, tile matrix should generate many mountain peaks"() {
        when:
        def tm = TileMatrixFactory.getNewLargeMountainousMap()

        then:
        TileMatrixFinder.findAllMountainPeaks(tm).size() >= new MountainousMapTopology().getMountainPeakModifier()
    }
}
