package com.groovygame.mob

import com.groovygame.area.room.provider.DemoWorldMapProvider
import com.groovygame.util.Constants
import spock.lang.Specification

class MobSpec extends Specification {
    def map = new DemoWorldMapProvider().getMap()
    def mob = map.getMobs().first()
    def path = mob.getPatrolPath()

    def "a patrolling mob follows its path from source to destination, then back again"() {
        expect:
        mob.getCoords().scale(Constants.TILE_SCALE) == path[0]

        for (def i = 0; i < path.size()-1; i++) {
            when:
            mob.patrol()

            then:
            mob.getCoords().scale(Constants.TILE_SCALE) == path[i+1]
        }

        for (def i = path.size(); i > 0; i--) {
            when:
            mob.patrol()

            then:
            mob.getCoords().scale(Constants.TILE_SCALE) == path[i-1]
        }
    }

    def "a mob should be able to create identical projectile copies"() {
        when:
        def projectile = mob.getNewProjectile()

        then:
        projectile == mob.getNewProjectile()
    }
}
