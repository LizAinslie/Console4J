package dev.lizainslie.console.message.messages

import dev.lizainslie.console.message.ConsoleMessage
import dev.lizainslie.console.message.ConsoleMessageType

class ListConsoleMessage<T>(
    value: List<T>,
    type: ConsoleMessageType = ConsoleMessageType.OUT
) : ConsoleMessage<List<T>>(value, type) {
    override fun buildString(): String {
        var out = ""
        for (item in value) out += "- $item\n"
        return out
    }
}