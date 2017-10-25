package com.groovygame.util

import groovy.transform.Immutable

@Immutable
class Time {
    static long getCurrentMilliseconds() {
        new Date().getTime()
    }
}
