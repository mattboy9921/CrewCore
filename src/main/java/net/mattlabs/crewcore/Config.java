package net.mattlabs.crewcore;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Comment;
import org.spongepowered.configurate.objectmapping.meta.Setting;

import java.util.ArrayList;

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
@ConfigSerializable
public class Config {

    @SuppressWarnings("unused")
    @Setting(value = "_mattIsAwesome")
    @Comment("CrewCore Configuration\n" +
            "By mattboy9921\n" +
            "https://github.com/mattboy9921/CrewChat")
    private boolean _mattIsAwesome = true;

    @Comment("\nEnable/disable the /ender command")
    private boolean ender = false;

    public boolean getEnder() {
        return ender;
    }

    @Comment("\nGroups to ignore for Discord reminder")
    private ArrayList<String> discordReminderIgnoredGroups = new ArrayList<>();

    public ArrayList<String> getDiscordReminderIgnoredGroups() {
        return discordReminderIgnoredGroups;
    }
}
