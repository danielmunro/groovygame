package com.groovygame.mob

import com.groovygame.map.provider.DemoMapProvider
import spock.lang.Specification

class MobSpec extends Specification {
    def "a patrolling mob follows its path from source to destination, then back again"() {
        setup:
        def map = new DemoMapProvider().getMap()
        def mob = map.getMobs().first()
        def path = mob.getPatrolPath()

        expect:
        mob.getCoords() == path[0]

        for (def i = 0; i < path.size()-1; i++) {
            when:
            mob.patrol()

            then:
            mob.getCoords() == path[i+1]
        }

        for (def i = path.size(); i > 0; i--) {
            when:
            mob.patrol()

            then:
            mob.getCoords() == path[i-1]
        }
    }
}