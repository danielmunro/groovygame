package com.groovygame.mob

import com.groovygame.util.Constants
import com.groovygame.util.Coords

class Patrol {
    private List<Coords> path
    private boolean headingTowardDest = true

    def getPath() {
        path
    }

    def proceed(Mob mob) {
        if (headingTowardDest) {
            for (int i = 0; i < path.size() - 1; i++) {
                if (path[i] == mob.getCoords().scale(Constants.TILE_SCALE)) {
                    mob.setCoords(getNextPath(mob.getCoords(), i + 1))
                    return
                }
            }
            headingTowardDest = false
            return
        }
        for (int i = path.size(); i > 0; i--) {
            if (path[i] == mob.getCoords().scale(Constants.TILE_SCALE)) {
                mob.setCoords(getNextPath(mob.getCoords(), i - 1))
                return
            }
        }
        headingTowardDest = true
    }

    def getNextPath(Coords origin, int nextI) {
        Coords nextCoords = path[nextI].scale(Constants.TILE_SIZE)
        [
                new Coords(origin.getX()-1, origin.getY()),
                new Coords(origin.getX(), origin.getY()-1),
                new Coords(origin.getX()+1, origin.getY()),
                new Coords(origin.getX(), origin.getY()+1),
        ]
        .sort{ Coords a, Coords b ->
             nextCoords.distanceFrom(a) - nextCoords.distanceFrom(b)
        }
        .first()
    }
}
