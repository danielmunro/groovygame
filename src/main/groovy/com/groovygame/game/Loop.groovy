package com.groovygame.game

import com.groovygame.util.Time

class Loop extends Observable {
    private static final LOOP_WAIT_IN_MILLISECONDS = 3
    private boolean running = true

    void loop() {
        def lastUpdateMilliseconds = 0
        while (running) {
            def currentTimeInMilliseconds = Time.getCurrentMilliseconds()
            if (isReadyForUpdate(lastUpdateMilliseconds, currentTimeInMilliseconds)) {
                update(currentTimeInMilliseconds - lastUpdateMilliseconds)
                lastUpdateMilliseconds = currentTimeInMilliseconds
            }
        }
    }

    private static boolean isReadyForUpdate(long lastUpdate, long currentTime) {
        return currentTime > lastUpdate + LOOP_WAIT_IN_MILLISECONDS
    }

    private void update(int deltaInMilliseconds) {
        setChanged()
        notifyObservers(deltaInMilliseconds)
    }
}
