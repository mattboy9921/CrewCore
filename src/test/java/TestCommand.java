import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import cloud.commandframework.paper.PaperCommandManager;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.mattlabs.crewcore.CrewCore;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.junit.jupiter.api.*;

public class TestCommand {

    ServerMock server;
    CrewCore plugin;
    PaperCommandManager<CommandSender> commandManager;
    World world;
    PlayerMock player1;
    PlainTextComponentSerializer plain = PlainTextComponentSerializer.plainText();

    @BeforeEach
    public void setUp() {
        server = MockBukkit.mock();
        CrewCore.testEnabled = true;
        plugin = MockBukkit.load(CrewCore.class);
        commandManager = plugin.getCommandManager();
    }

    @AfterEach
    public void tearDown() {
        MockBukkit.unmock();
    }

    // Set up one player with permissions in the default world
    void onePlayerSetup() {
        player1 = server.addPlayer("Player1");
        world = server.getWorld("world");
    }
}
