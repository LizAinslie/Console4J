package dev.lizainslie.console.message.messages

import dev.lizainslie.console.message.ConsoleMessage
import dev.lizainslie.console.message.ConsoleMessageType
import javax.swing.JLabel

class StringConsoleMessage(
    value: String,
    type: ConsoleMessageType = ConsoleMessageType.OUT
) : ConsoleMessage<String>(value, type) {
    override fun build() = JLabel("${type.prefix} $value")
}