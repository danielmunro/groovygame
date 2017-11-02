package com.groovygame.game

import com.groovygame.util.Time
import com.groovygame.util.UpdateTimer

class Loop extends Observable {
    private int lastUpdateInMilliseconds = Time.getCurrentMilliseconds()
    private UpdateTimer timer = new UpdateTimer(1)

    void loop() {
        while (1) {
            def currentTimeInMilliseconds = Time.getCurrentMilliseconds()
            def deltaInMilliseconds = currentTimeInMilliseconds - lastUpdateInMilliseconds
            timer.poll(deltaInMilliseconds, {
                    update(currentTimeInMilliseconds, deltaInMilliseconds)
            })
        }
    }

    private void update(int currentTimeInMilliseconds, int deltaInMilliseconds) {
        setChanged()
        notifyObservers(deltaInMilliseconds)
        lastUpdateInMilliseconds = currentTimeInMilliseconds
    }
}
