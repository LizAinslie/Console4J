import dev.lizainslie.console.ConsolePanel
import dev.lizainslie.console.message.messages.ListConsoleMessage

fun main() {
    val console = ConsolePanel(welcome = "uwu")
    Thread(console).start()

    val userInput = console.promptString("Enter some text")
    println(userInput)
    console.sendMessage("You said: $userInput")

    console.sendMessage(ListConsoleMessage(listOf(
        "List Item 1",
        "List Item 2",
        "List Item 3"
    )))
}