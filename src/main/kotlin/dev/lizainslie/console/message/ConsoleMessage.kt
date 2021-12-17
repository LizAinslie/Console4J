package dev.lizainslie.console.message

abstract class ConsoleMessage<T>(val value: T, val type: ConsoleMessageType) {
    abstract fun buildString(): String
}