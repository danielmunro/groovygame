package com.groovygame.map.topology

import groovy.transform.Immutable

@Immutable
class FlatMapTopology implements MapTopology {
    int getMountainPeakModifier() {
        return 1
    }
}
