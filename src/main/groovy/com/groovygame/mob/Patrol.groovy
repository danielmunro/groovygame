package com.groovygame.mob

import com.groovygame.util.Constants
import com.groovygame.util.Coords

class Patrol {
    private nextI = 1
    private List<Coords> path
    private boolean headingTowardDest = true

    def getPath() {
        path
    }

    def proceed(Mob mob) {
        determineDirection()
        moveAccordingToNextCoords(mob, path[nextI].scale(Constants.TILE_SIZE))
    }

    private void determineDirection() {
        if (atEndOfPath()) {
            headTowardSrc()
        } else if (atBeginningOfPath()) {
            headTowardDest()
        }
    }

    private void moveAccordingToNextCoords(Mob mob, Coords nextCoordsScaled) {
        if (mob.getCoords() != nextCoordsScaled) {
            mob.setCoords(getNextPath(mob.getCoords(), nextCoordsScaled))
        } else {
            incrementI()
            proceed(mob)
        }
    }

    private void incrementI() {
        nextI += headingTowardDest ? 1 : -1
    }

    private void headTowardSrc() {
        headingTowardDest = false
        incrementI()
    }

    private void headTowardDest() {
        headingTowardDest = true
        incrementI()
    }

    private atBeginningOfPath() {
        !headingTowardDest && nextI == -1
    }

    private atEndOfPath() {
        headingTowardDest && nextI >= path.size()
    }

    private static getNextPath(Coords origin, Coords nextCoords) {
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
