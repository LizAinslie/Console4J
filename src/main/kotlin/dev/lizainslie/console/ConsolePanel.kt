package dev.lizainslie.console

import dev.lizainslie.console.message.ConsoleMessage
import dev.lizainslie.console.message.ConsoleMessageType
import dev.lizainslie.console.message.messages.StringConsoleMessage
import java.awt.BorderLayout
import java.awt.Component
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.*

class ConsolePanel(columns: Int = 20, welcome: String? = null) : JPanel(BorderLayout()), KeyListener, Runnable {
    // Not sure if we need this, but I can't be assed to actually test this right now
    private class ComponentListRenderer : ListCellRenderer<JComponent> {
        override fun getListCellRendererComponent(
            list: JList<out JComponent>?,
            value: JComponent,
            index: Int,
            isSelected: Boolean,
            cellHasFocus: Boolean
        ): Component = value
    }

    private val messagesModel = DefaultListModel<JComponent>()
    private val messages = JList(messagesModel)

    private val textInputField = JTextField(columns)
    private var text: String? = null

    init {
        textInputField.addKeyListener(this)
        textInputField.isEnabled = false
        this.add(this.textInputField, BorderLayout.PAGE_END)

        this.messages.cellRenderer = ComponentListRenderer()
        val pane = JScrollPane(this.messages)
        pane.horizontalScrollBarPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        pane.verticalScrollBarPolicy = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        this.add(pane, BorderLayout.CENTER)

        this.setSize(textInputField.columns * 10, 400)

        if (welcome != null) this.sendMessage(welcome)
    }

    fun sendMessage(message: ConsoleMessage<*>) {
        this.messagesModel.addElement(message.build())
        this.messages.selectedIndex = this.messages.model.size - 1
        this.messages.ensureIndexIsVisible(this.messages.selectedIndex)
    }

    fun sendMessage(s: String) {
        sendMessage(StringConsoleMessage(s))
    }

    fun sendMessage() {
        this.messagesModel.addElement(JLabel(""))
        this.messages.selectedIndex = this.messages.model.size - 1
        this.messages.ensureIndexIsVisible(this.messages.selectedIndex)
    }

    fun promptString(prompt: String): String {
        try {
            this.sendMessage(prompt)

            textInputField.isEnabled = true
            textInputField.text = null
            textInputField.grabFocus()

            while (text == null) Thread.sleep(500)

            val cacheText: String = text!!
            text = null

            return cacheText
        } catch (e: InterruptedException) {
            return ""
        }
    }

    override fun keyPressed(keyEvent: KeyEvent) {}
    override fun keyTyped(e: KeyEvent) {}

    override fun keyReleased(keyEvent: KeyEvent) {
        if (keyEvent.keyCode == KeyEvent.VK_ENTER) {
            val inputText = textInputField.text
            text = inputText

            sendMessage(StringConsoleMessage(
                inputText,
                ConsoleMessageType.IN
            ))

            textInputField.isEnabled = false
            textInputField.text = null
        }
    }

    override fun run() {
        val frame = JFrame("Console")
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE

        frame.contentPane.layout = BorderLayout()
        frame.contentPane.add(this, BorderLayout.CENTER)
        frame.pack()
        frame.setSize(textInputField.columns * 10, 400)
        frame.isVisible = true
    }
}