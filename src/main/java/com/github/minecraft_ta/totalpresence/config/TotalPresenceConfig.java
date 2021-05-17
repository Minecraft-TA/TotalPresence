package com.github.minecraft_ta.totalpresence.config;

import com.github.minecraft_ta.totalpresence.TotalPresence;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TotalPresenceConfig {

    private static final String CATEGORY_PRESENCE = "RichPresence";
    private final Configuration config;

    public String applicationID;
    public String startupState;
    public String startupDetails;
    public String titleState;
    public String titleDetails;
    public String worldState;
    public String worldDetails;

    public TotalPresenceConfig(Configuration config) {
        this.config = config;
        this.load();
    }

    @SubscribeEvent
    public void onChange(ConfigChangedEvent event) {
        if (event.getModID().equals(TotalPresence.MOD_ID)) {
            this.load();
            TotalPresence.INSTANCE.getDiscord().setPresence(worldState, worldDetails, "world");
        }
    }

    private void load() {
        this.config.load();

        this.applicationID = this.config.getString("Application-ID", CATEGORY_PRESENCE, "843108003023552532", "Application ID for custom icons");

        this.startupState = this.config.getString("StartupState", CATEGORY_PRESENCE , "Starting Minecraft", "First line of text for rich presence when starting the game");
        this.startupDetails = this.config.getString("StartupDetails", CATEGORY_PRESENCE, "", "Second line of text for rich presence when starting the game");

        this.titleState = this.config.getString("TitleState", CATEGORY_PRESENCE, "In main menu", "First line of text for rich presence when in the title screen");
        this.titleDetails = this.config.getString("TitleDetails",CATEGORY_PRESENCE , "", "Second line of text for rich presence when in the title screen");

        this.worldState = this.config.getString("WorldState", CATEGORY_PRESENCE , "Playing in a world", "First line of text for rich presence when playing in a world");
        this.worldDetails = this.config.getString("WorldDetails", CATEGORY_PRESENCE, "", "Second line line of text for rich presence when playing in a world");

        if (this.config.hasChanged()) {
            this.config.save();
        }
    }
}
