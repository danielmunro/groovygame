package com.groovygame.util

class UpdateTimer {
    private int updateIntervalInMilliseconds
    private int millisecondsSinceLastUpdate
    private Closure closure

    UpdateTimer(int updateIntervalInMilliseconds, Closure closure) {
        this.updateIntervalInMilliseconds = updateIntervalInMilliseconds
        this.closure = closure
    }

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

    void poll(int deltaInMilliseconds, Closure closure) {
        addMilliseconds(deltaInMilliseconds)
        if (isReadyForUpdate()) {
            closure()
            resetUpdateCounter()
        }
    }

    void poll(int deltaInMilliseconds) {
        addMilliseconds(deltaInMilliseconds)
        if (isReadyForUpdate()) {
            closure()
            resetUpdateCounter()
        }
    }
}
