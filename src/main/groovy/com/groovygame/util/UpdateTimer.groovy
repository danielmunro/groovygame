package com.groovygame.util

class UpdateTimer {
    private int updateIntervalInMilliseconds
    private int millisecondsSinceLastUpdate
    private Closure closure

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
        doPoll(deltaInMilliseconds, closure)
    }

    void poll(int deltaInMilliseconds) {
        doPoll(deltaInMilliseconds, closure)
    }

    void doPoll(int deltaInMilliseconds, Closure closure) {
        addMilliseconds(deltaInMilliseconds)
        if (isReadyForUpdate()) {
            closure()
            resetUpdateCounter()
        }
    }
}
