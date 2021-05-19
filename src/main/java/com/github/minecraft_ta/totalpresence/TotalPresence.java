package com.github.minecraft_ta.totalpresence;


import com.github.minecraft_ta.totalpresence.config.TotalPresenceConfig;
import com.github.minecraft_ta.totalpresence.discord.Discord;
import com.github.minecraft_ta.totalpresence.handler.DiscordHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;

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

    private TotalPresenceConfig config;

    private Discord discord;

    @Mod.EventHandler
    public void onModConstruction(FMLConstructionEvent event) {
        if (event.getSide().isClient()) {
            this.config = new TotalPresenceConfig(new Configuration(new File(Loader.instance().getConfigDir(), MOD_ID + ".cfg")));
            this.discord = new Discord(config.applicationID, config.startupState, config.startupDetails, "starting", config.imgText, "small", config.smallImgText);

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                this.discord.shutdown();
            }));
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(config);
        MinecraftForge.EVENT_BUS.register(new DiscordHandler());
    }

    public TotalPresenceConfig getConfig() {
        return config;
    }

    public Discord getDiscord() {
        return discord;
    }
}
