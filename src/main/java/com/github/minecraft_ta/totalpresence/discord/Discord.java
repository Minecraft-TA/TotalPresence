package com.github.minecraft_ta.totalpresence.discord;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;

public class Discord {

    private final DiscordRPC lib;
    private final DiscordRichPresence presence;

    public Discord(String applicationID, String state, String details, String imgKey) {
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        this.lib = DiscordRPC.INSTANCE;
        this.presence = new DiscordRichPresence();

        this.lib.Discord_Initialize(applicationID, handlers, true, null);
        this.presence.startTimestamp = System.currentTimeMillis() / 1000;
        setPresence(state, details, imgKey);
    }

    public void setPresence(String details, String state, String imgKey) {
        this.presence.state = state;
        this.presence.details = details;
        this.presence.largeImageKey = imgKey;

        lib.Discord_UpdatePresence(this.presence);
    }

    public void shutdown() {
        this.lib.Discord_Shutdown();
    }
}
