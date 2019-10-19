package net.mattlabs.crewcore.messaging;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;

import static net.md_5.bungee.api.ChatColor.*;

public class Messages {

    private Messages() {

    }

    public static BaseComponent[] ender() {
        // &7--------------------------&5[]&7--------------------------
        // &5The Ender Dragon was courageously defeated on January 6th, 2017. Took us long enough!
        // &5Special thanks to Animage7, ItzYuno, Keitorin_, Kitsune376, mattboy9921, shawgirl05, _TheBoxman_ and Zingdos!
        // &7--------------------------&5[]&7--------------------------
        return new ComponentBuilder("--------------------------")
                    .color(GRAY)
                .append("[]")
                    .color(DARK_PURPLE)
                .append("--------------------------\n")
                    .color(GRAY)
                .append("The Ender Dragon was courageously defeated on January 6th, 2017. Took us long enough!\n")
                    .color(DARK_PURPLE)
                .append("Special thanks to Animage7, ItzYuno, Keitorin_, Kitsune376, mattboy9921, shawgirl05, _TheBoxman_ and Zingdos!\n")
                    .color(DARK_PURPLE)
                .append("--------------------------")
                    .color(GRAY)
                .append("[]")
                    .color(DARK_PURPLE)
                .append("--------------------------")
                    .color(GRAY)
                .create();
    }

    public static BaseComponent[] greyJoin(String name) {
        //   &8[&7&l+&r&8]  &7%player%
        return new ComponentBuilder("  [")
                    .color(DARK_GRAY)
                .append("+")
                    .color(GRAY)
                    .bold(true)
                .append("]  ")
                    .reset()
                    .color(DARK_GRAY)
                .append(name)
                    .color(GRAY)
                .create();
    }

    public static BaseComponent[] memberJoin(String name) {
        //   &8[&7&l+&r&8]  &7%player%
        return new ComponentBuilder("  [")
                    .color(DARK_GRAY)
                .append("+")
                    .color(GREEN)
                    .bold(true)
                .append("]  ")
                    .reset()
                    .color(DARK_GRAY)
                .append(name)
                    .color(GRAY)
                .create();
    }

    public static BaseComponent[] moderatorJoin(String name) {
        //   &8[&7&l+&r&8]  &7%player%
        return new ComponentBuilder("  [")
                    .color(DARK_GRAY)
                .append("+")
                    .color(AQUA)
                    .bold(true)
                .append("]  ")
                    .reset()
                    .color(DARK_GRAY)
                .append(name)
                    .color(GRAY)
                .create();
    }

    public static BaseComponent[] mattJoin(String name) {
        //   &8[&7&l+&r&8]  &7%player%
        return new ComponentBuilder("  [")
                    .color(DARK_GRAY)
                .append("+")
                    .color(DARK_RED)
                    .bold(true)
                .append("]  ")
                    .reset()
                    .color(DARK_GRAY)
                .append(name)
                    .color(GRAY)
                .create();
    }

    public static BaseComponent[] greyQuit(String name) {
        //   &8[&7&l+&r&8]  &7%player%
        return new ComponentBuilder("  [")
                    .color(DARK_GRAY)
                .append("-")
                    .color(GRAY)
                    .bold(true)
                .append("]  ")
                    .reset()
                    .color(DARK_GRAY)
                .append(name)
                    .color(GRAY)
                .create();
    }

    public static BaseComponent[] allQuit(String name) {
        //   &8[&7&l+&r&8]  &7%player%
        return new ComponentBuilder("  [")
                    .color(DARK_GRAY)
                .append("-")
                    .color(DARK_RED)
                    .bold(true)
                .append("]  ")
                    .reset()
                    .color(DARK_GRAY)
                .append(name)
                    .color(GRAY)
                .create();
    }

    public static BaseComponent[] greyInfo(String name) {
        // &8-----------------------------------------------------
        // &7Welcome to the Crew Craft Server, %player%&7!
        // &7This is a &lvanilla&r&7, &lsurvival&r&7 server.
        // &7No game mechanics are altered although plugins are used.
        // &7You are currently not a member. Feel free to look around.
        // %player%", "&7If you'd like to join the server, please click here: || &9&l[Apply]||url:https://crewcraftserver.net/contact.html||ttp:&b&lClick &rhere to apply.
        // &8-----------------------------------------------------
        return new ComponentBuilder("-----------------------------------------------------\n")
                    .color(DARK_GRAY)
                .append("Welcome to the Crew Craft Server, " + name + "!\n")
                    .color(GRAY)
                .append("This is a ")
                    .color(GRAY)
                .append("vanilla")
                    .color(GRAY)
                    .bold(true)
                .append(", ")
                    .reset()
                    .color(GRAY)
                .append("survival")
                    .color(GRAY)
                    .bold(true)
                .append(" server.\n")
                    .reset()
                    .color(GRAY)
                .append("No game mechanics are altered although plugins are used.\n")
                    .color(GRAY)
                .append("You are currently not a member. Feel free to look around.\n")
                    .color(GRAY)
                .append("If you'd like to join the server, please click here: ")
                    .color(GRAY)
                .append("[Apply]\n")
                    .color(BLUE)
                    .bold(true)
                    .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                            new ComponentBuilder("Click")
                                        .color(AQUA)
                                        .bold(true)
                                    .append(" here to apply.")
                                        .reset()
                                    .create()))
                    .event(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://crewcraftserver.net/contact.html"))
                .append("-----------------------------------------------------")
                    .reset()
                    .color(DARK_GRAY)
                .create();
    }

    public static BaseComponent[] pressedFToPayRespects(String name) {
        // %name% Pressed &e&lF&r to Pay Respects
        return new ComponentBuilder(name + " Pressed ")
                .append("F")
                    .color(YELLOW)
                    .bold(true)
                .append(" to Pay Respects")
                    .reset()
                .create();
    }

    public static BaseComponent[] canIgetAnF() {
        // Can I get an &e&lF
        return new ComponentBuilder("Can I get an ")
                .append("F")
                    .color(YELLOW)
                    .bold(true)
                .create();
    }

    public static BaseComponent[] inTheChatBois() {
        // in the chat bois?
        return new ComponentBuilder("in the chat bois?")
                .create();
    }
}
