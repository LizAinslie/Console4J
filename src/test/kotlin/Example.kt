import dev.lizainslie.console.ConsolePanel
import dev.lizainslie.console.message.messages.ListConsoleMessage

fun main() {
    val console = ConsolePanel(welcome = "uwu")
    Thread(console).start()

    val userInput = console.promptString("uwu?")
    println(userInput)
    console.sendMessage("You said: $userInput")

    console.sendMessage(ListConsoleMessage(listOf(
        "owo",
        "uwu",
        "nya"
    )))
}