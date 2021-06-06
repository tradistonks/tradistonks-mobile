package com.tradistonks.app.utilities

data class Events<out T> (private val content: T) {
    var isTreated = false
        private set
    fun getNonTreatedContent(): T? {
        return if (isTreated) {
            null
        } else {
            isTreated = true
            content
        }
    }
}