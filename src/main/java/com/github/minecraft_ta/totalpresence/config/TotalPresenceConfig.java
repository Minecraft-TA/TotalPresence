package com.github.minecraft_ta.totalpresence.config;

import com.github.minecraft_ta.totalpresence.TotalPresence;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TotalPresenceConfig {

    private static final String CATEGORY_PRESENCE = "RichPresence";
    private final Configuration config;

    public String applicationID;
    public String startupText;
    public String titleText;
    public String worldText;

    public TotalPresenceConfig(Configuration config) {
        this.config = config;
        this.load();
    }

    @SubscribeEvent
    public void onChange(ConfigChangedEvent event) {
        if (event.getModID().equals(TotalPresence.MOD_ID)) {
            this.load();
        }
    }

    private void load() {
        this.config.load();

        Property propertyApplicationID = this.config.get(CATEGORY_PRESENCE, "Application-ID", "843108003023552532", "Application ID for custom icons");
        Property propertyStartupText = this.config.get(CATEGORY_PRESENCE, "StartupText", "Starting Minecraft", "Application ID for custom icons");
        Property propertyTitleText = this.config.get(CATEGORY_PRESENCE, "TitleText", "Playing Minecraft", "Application ID for custom icons");
        Property propertyWorldText = this.config.get(CATEGORY_PRESENCE, "WorldText", "Playing in world", "Application ID for custom icons");

        applicationID = propertyApplicationID.getString();
        startupText = propertyStartupText.getString();
        titleText = propertyTitleText.getString();
        worldText = propertyWorldText.getString();

        if (this.config.hasChanged()) {
            this.config.save();
        }
    }
}
