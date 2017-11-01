package com.groovygame.util

import java.util.concurrent.Callable

class UpdateTimer {
    private int updateIntervalInMilliseconds
    private int millisecondsSinceLastUpdate

    UpdateTimer(int updateIntervalInMilliseconds) {
        this.updateIntervalInMilliseconds = updateIntervalInMilliseconds
        millisecondsSinceLastUpdate = 0
    }

    void addMilliseconds(int deltaInMilliseconds) {
        millisecondsSinceLastUpdate += deltaInMilliseconds
    }

    boolean isReadyForUpdate() {
        return millisecondsSinceLastUpdate >= updateIntervalInMilliseconds
    }

    void resetUpdateCounter() {
        millisecondsSinceLastUpdate = 0
    }

    void poll(int deltaInMilliseconds, Callable callable) {
        addMilliseconds(deltaInMilliseconds)
        if (isReadyForUpdate()) {
            callable()
            resetUpdateCounter()
        }
    }
}
