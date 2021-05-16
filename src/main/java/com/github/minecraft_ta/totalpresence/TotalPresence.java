package com.github.minecraft_ta.totalpresence;


import com.github.minecraft_ta.totalpresence.config.TotalPresenceConfig;
import com.github.minecraft_ta.totalpresence.discord.Discord;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(
        modid = TotalPresence.MOD_ID,
        name = TotalPresence.MOD_NAME,
        version = TotalPresence.VERSION
)
public class TotalPresence {

    public static final String MOD_ID = "totalpresence";
    public static final String MOD_NAME = "TotalPresence";
    public static final String VERSION = "1.0-SNAPSHOT";

    @Mod.Instance(MOD_ID)
    public static TotalPresence INSTANCE;

    public static TotalPresenceConfig CONFIG;

    public static Discord DISCORD;

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        CONFIG = new TotalPresenceConfig(new Configuration(event.getSuggestedConfigurationFile()));
        DISCORD = new Discord(CONFIG.applicationID, CONFIG.startupText, "*Was passiert hier lol*", "test");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(CONFIG);
    }
}
