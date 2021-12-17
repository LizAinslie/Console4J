package dev.lizainslie.console.message

import javax.swing.JComponent

abstract class ConsoleMessage<T>(val value: T, val type: ConsoleMessageType) {
    abstract fun build(): JComponent
}