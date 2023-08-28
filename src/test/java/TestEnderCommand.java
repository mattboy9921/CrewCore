import cloud.commandframework.execution.CommandResult;
import net.mattlabs.crewcore.messaging.Messages;
import org.bukkit.command.CommandSender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

public class TestEnderCommand extends TestCommand {

    @Test
    @DisplayName("Test ender command")
    public void enderCommand() {
        onePlayerSetup();

        commandManager.executeCommand(player1, "ender").join();

        Assertions.assertEquals(
                plain.serialize(Messages.ender()),
                plain.serialize(player1.nextComponentMessage())
        );
    }
}
