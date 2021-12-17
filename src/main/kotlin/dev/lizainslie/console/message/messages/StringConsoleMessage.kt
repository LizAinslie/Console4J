package dev.lizainslie.console.message.messages

import dev.lizainslie.console.message.ConsoleMessage
import dev.lizainslie.console.message.ConsoleMessageType

class StringConsoleMessage(
    value: String,
    type: ConsoleMessageType = ConsoleMessageType.OUT
) : ConsoleMessage<String>(value, type) {
    override fun buildString() = this.value
}