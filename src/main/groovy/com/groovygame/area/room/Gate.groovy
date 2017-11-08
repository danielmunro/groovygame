package com.groovygame.area.room

import com.groovygame.mob.Mob
import com.groovygame.util.Coords
import groovy.transform.Immutable
import sun.plugin.dom.exception.InvalidStateException

@Immutable
class Gate {
    private Coords srcCoords
    private Map srcMap
    private Coords destCoords
    private Map destMap

    boolean isReadyToTransport(Mob mob) {
        mob.getCoords() == srcCoords
    }

    void transport(Mob mob) {
        if (!isReadyToTransport(mob)) {
            throw new InvalidStateException('Mob is not at gate source!')
        }

        srcMap.removeMob(mob)
        destMap.addMob(mob)
        mob.setCoords(destCoords)
    }
}
