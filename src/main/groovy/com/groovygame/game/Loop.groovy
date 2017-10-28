package com.groovygame.game

import com.groovygame.util.Time

class Loop {
    private static final LOOP_WAIT_IN_MILLISECONDS = 3
    private static final UPDATES_PER_ANIMATION_LOOP = 8
    private int updatesSinceLastAnimationLoop = 0
    private boolean running = true
    private Service service

    void loop() {
        def lastUpdateMilliseconds = 0
        while (running) {
            if (isReadyForUpdate(lastUpdateMilliseconds, Time.getCurrentMilliseconds())) {
                update()
                lastUpdateMilliseconds = Time.getCurrentMilliseconds()
            }
        }
    }

    private static boolean isReadyForUpdate(long lastUpdate, long currentTime) {
        return currentTime > lastUpdate + LOOP_WAIT_IN_MILLISECONDS
    }

    private void update() {
        service.gameLoopUpdate()
        if (updatesSinceLastAnimationLoop > UPDATES_PER_ANIMATION_LOOP) {
            service.animationFrameUpdate()
            updatesSinceLastAnimationLoop = 0
        } else {
            updatesSinceLastAnimationLoop++
        }
    }
}
