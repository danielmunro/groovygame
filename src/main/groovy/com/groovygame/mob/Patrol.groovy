package com.groovygame.mob

import com.groovygame.util.Coords

class Patrol {
    private Coords src
    private Coords dest
    private List<Coords> path
    private boolean headingTowardDest = true

    def getPath() {
        path
    }

    def proceed(Mob mob) {
        for (int i = 0; i < path.size(); i++) {
            if (path[i] == mob.getCoords()) {
                mob.setCoords(getNextPath(i))
                return
            }
        }
    }

    def getNextPath(int i) {
        def nextI = i+1
        if (headingTowardDest && path.size() > nextI) {
            return path[nextI]
        } else {
            headingTowardDest = false
        }

        if (i == 0) {
            headingTowardDest = true
            return path[1]
        }

        return path[nextI]
    }
}
