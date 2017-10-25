package com.groovygame.map.topology

import groovy.transform.Immutable

@Immutable
class MountainousMapTopology implements MapTopology {
    int getMountainPeakModifier() {
        return 3
    }
}
