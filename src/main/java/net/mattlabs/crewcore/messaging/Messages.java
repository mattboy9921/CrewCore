package net.mattlabs.crewcore.messaging;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;

import static net.kyori.adventure.text.format.NamedTextColor.*;
import static net.kyori.adventure.text.format.TextDecoration.BOLD;
import static net.kyori.adventure.text.format.TextDecoration.ITALIC;

public class Messages {

    private Messages() {

    }

    public static Component ender() {
        // &7--------------------------&5[]&7--------------------------
        // &5The Ender Dragon was courageously defeated on January 6th, 2017. Took us long enough!
        // &5Special thanks to Animage7, ItzYuno, Keitorin_, Kitsune376, mattboy9921, shawgirl05, _TheBoxman_ and Zingdos!
        // &7--------------------------&5[]&7--------------------------
        return Component.text()
                .append(Component.text("--------------------------", GRAY))
                .append(Component.text("[]", DARK_PURPLE))
                .append(Component.text("--------------------------\n", GRAY))
                .append(Component.text("The Ender Dragon was courageously defeated on January 6th, 2017. Took us long enough!\n", DARK_PURPLE))
                .append(Component.text("Special thanks to Animage7, ItzYuno, Keitorin_, Kitsune376, mattboy9921, shawgirl05, _TheBoxman_ and Zingdos!\n", DARK_PURPLE))
                .append(Component.text("--------------------------", GRAY))
                .append(Component.text("[]", DARK_PURPLE))
                .append(Component.text("--------------------------", GRAY))
                .build();
    }

    public static Component greyJoin(String name) {
        //   &8[&7&l+&r&8]  &7%player%
        return Component.text()
                .append(Component.text("  [", DARK_GRAY))
                .append(Component.text("+", GRAY, BOLD))
                .append(Component.text("]  ", DARK_GRAY))
                .append(Component.text(name, GRAY))
                .build();
    }

    public static Component memberJoin(String name) {
        //   &8[&7&l+&r&8]  &7%player%
        return Component.text()
                .append(Component.text("  [", DARK_GRAY))
                .append(Component.text("+", GREEN, BOLD))
                .append(Component.text("]  ", DARK_GRAY))
                .append(Component.text(name, GRAY))
                .build();
    }

    public static Component moderatorJoin(String name) {
        //   &8[&7&l+&r&8]  &7%player%
        return Component.text()
                .append(Component.text("  [", DARK_GRAY))
                .append(Component.text("+", AQUA, BOLD))
                .append(Component.text("]  ", DARK_GRAY))
                .append(Component.text(name, GRAY))
                .build();
    }

    public static Component mattJoin(String name) {
        //   &8[&7&l+&r&8]  &7%player%
        return Component.text()
                .append(Component.text("  [", DARK_GRAY))
                .append(Component.text("+", DARK_RED, BOLD))
                .append(Component.text("]  ", DARK_GRAY))
                .append(Component.text(name, GRAY))
                .build();
    }

    public static Component greyQuit(String name) {
        //   &8[&7&l+&r&8]  &7%player%
        return Component.text()
                .append(Component.text("  [", DARK_GRAY))
                .append(Component.text("-", GRAY, BOLD))
                .append(Component.text("]  ", DARK_GRAY))
                .append(Component.text(name, GRAY))
                .build();
    }

    public static Component allQuit(String name) {
        //   &8[&7&l+&r&8]  &7%player%
        return Component.text()
                .append(Component.text("  [", DARK_GRAY))
                .append(Component.text("-", DARK_RED, BOLD))
                .append(Component.text("]  ", DARK_GRAY))
                .append(Component.text(name, GRAY))
                .build();
    }

    public static Component greyInfo(String name) {
        // &8-----------------------------------------------------
        // &7Welcome to the Crew Craft Server, %player%&7!
        // &7This is a &lvanilla&r&7, &lsurvival&r&7 server.
        // &7No game mechanics are altered although plugins are used.
        // &7You are currently not a member. Feel free to look around.
        // %player%", "&7If you'd like to join the server, please click here: || &9&l[Apply]||url:https://crewcraftserver.net/apply||ttp:&b&lClick &rhere to apply.
        // &8-----------------------------------------------------
        return Component.text()
                .append(Component.text("-----------------------------------------------------\n", DARK_GRAY))
                .append(Component.text("Welcome to the Crew Craft Server, " + name + "!\n", GRAY))
                .append(Component.text("This is a ", GRAY))
                .append(Component.text("vanilla", GRAY, BOLD))
                .append(Component.text(", ", GRAY))
                .append(Component.text("survival", GRAY, BOLD))
                .append(Component.text(" server.\n", GRAY))
                .append(Component.text("No game mechanics are altered although plugins are used.\n", GRAY))
                .append(Component.text("You are currently not a member. Feel free to look around.\n", GRAY))
                .append(Component.text("If you'd like to join the server, please click here: ", GRAY))
                .append(Component.text("[Apply]\n", BLUE, BOLD)
                        .hoverEvent(HoverEvent.showText(Component.text().append(Component.text("Click here to apply."))))
                        .clickEvent(ClickEvent.openUrl("https://crewcraftserver.net/apply")))
                .append(Component.text("-----------------------------------------------------", DARK_GRAY))
                .build();
    }

    public static Component pressedFToPayRespects(String name) {
        // %name% Pressed &e&lF&r to Pay Respects
        return Component.text()
                .append(Component.text(name + " Pressed ", WHITE))
                .append(Component.text("F", YELLOW, BOLD))
                .append(Component.text(" to Pay Respects", WHITE))
                .build();
    }

    public static Component canIGetAnF() {
        // Can I get an &e&lF
        return Component.text()
                .append(Component.text("Can I get an ", WHITE))
                .append(Component.text("F", YELLOW, BOLD))
                .build();
    }

    public static Component inTheChatBois() {
        // in the chat bois?
        return Component.text()
                .append(Component.text("in the chat bois?", WHITE))
                .build();
    }

    public static Component discordReminder() {
        // &aPlease link your Discord and MC accounts! [Discord Link]
        return Component.text()
                .append(Component.text("Please link your Discord and MC accounts! ", GREEN, ITALIC))
                .append(Component.text("[Discord Link]", BLUE, BOLD)
                        .hoverEvent(HoverEvent.showText(Component.text("Click here to link your Discord.")))
                        .clickEvent(ClickEvent.suggestCommand("/discord link")))
                .build();
    }
}
