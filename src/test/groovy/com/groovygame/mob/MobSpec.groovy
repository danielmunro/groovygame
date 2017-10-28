package com.groovygame.mob

import com.groovygame.map.provider.DemoMapProvider
import spock.lang.Specification

class MobSpec extends Specification {
    def "a patrolling mob follows its path"() {
        setup:
        def map = new DemoMapProvider().getMap()
        def mob = map.getMobs().first()

        when:
        mob.patrol()

        then:
        mob.getCoords() == mob.getPatrolPath()[1]
    }
}
