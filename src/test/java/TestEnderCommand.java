import net.mattlabs.crewcore.messaging.Messages;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestEnderCommand extends TestCommand {

    @Test
    @DisplayName("Test ender command")
    public void enderCommand() {
        onePlayerSetup();

        commandManager.commandExecutor().executeCommand(player1, "ender").join();

        Assertions.assertEquals(
                plain.serialize(Messages.ender()),
                plain.serialize(player1.nextComponentMessage())
        );
    }
}
