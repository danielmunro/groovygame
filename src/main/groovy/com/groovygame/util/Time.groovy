package com.groovygame.util

import groovy.transform.Immutable

@Immutable
class Time {
    static int getCurrentMilliseconds() {
        new Date().getTime()
    }
}
