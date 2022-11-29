package com.example.common.util

open class Event<out T>(private val content: T) {
    private var hasBeenHandled = false

    fun getContentIfNotHandnled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }
}