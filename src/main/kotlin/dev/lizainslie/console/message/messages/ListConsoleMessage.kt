package dev.lizainslie.console.message.messages

import dev.lizainslie.console.message.ConsoleMessage
import dev.lizainslie.console.message.ConsoleMessageType
import javax.swing.BoxLayout
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel

class ListConsoleMessage<T>(
    value: List<T>,
    type: ConsoleMessageType = ConsoleMessageType.OUT
) : ConsoleMessage<List<T>>(value, type) {
    constructor(value: List<T>) : this(value, ConsoleMessageType.OUT)

    override fun build(): JComponent {
        val out = JPanel()
        out.layout = BoxLayout(out, BoxLayout.Y_AXIS)
        for (item in value) out.add(JLabel("- $item"))
        out.isVisible = true
        return out
    }
}