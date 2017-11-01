package com.groovygame.game

import com.groovygame.util.Time
import com.groovygame.util.UpdateTimer

class Loop extends Observable {
    private int lastUpdateInMilliseconds = Time.getCurrentMilliseconds()
    private UpdateTimer timer = new UpdateTimer(100)

    void loop() {
        while (1) {
            def currentTimeInMilliseconds = Time.getCurrentMilliseconds()
            def deltaInMilliseconds = currentTimeInMilliseconds - lastUpdateInMilliseconds
            timer.addMilliseconds(deltaInMilliseconds)
            if (timer.isReadyForUpdate()) {
                update(currentTimeInMilliseconds, deltaInMilliseconds)
            }
        }
    }

    private void update(int currentTimeInMilliseconds, int deltaInMilliseconds) {
        setChanged()
        notifyObservers(deltaInMilliseconds)
        timer.resetUpdateCounter()
        lastUpdateInMilliseconds = currentTimeInMilliseconds
    }
}
