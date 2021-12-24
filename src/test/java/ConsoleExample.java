import dev.lizainslie.console.ConsolePanel;
import dev.lizainslie.console.message.messages.ListConsoleMessage;

import java.util.List;

public class ConsoleExample {
    public static void main(String[] args) {
        ConsolePanel consolePanel = new ConsolePanel();
        new Thread(consolePanel).start();

        String userInput = consolePanel.promptString("Enter some text");
        System.out.println(userInput);
        consolePanel.sendMessage(String.format("You said: %s", userInput));

        consolePanel.sendMessage(new ListConsoleMessage<>(List.of("List Item 1", "List Item 2", "List Item 3")));
    }
}
