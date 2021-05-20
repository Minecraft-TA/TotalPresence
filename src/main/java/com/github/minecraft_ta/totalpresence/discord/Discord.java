package com.github.minecraft_ta.totalpresence.discord;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;

public class Discord {

    private final DiscordRPC lib;
    private final DiscordRichPresence presence;

    public Discord(String applicationID) {
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        this.lib = DiscordRPC.INSTANCE;
        this.presence = new DiscordRichPresence();

        this.lib.Discord_Initialize(applicationID, handlers, true, null);
        this.presence.startTimestamp = System.currentTimeMillis() / 1000;
    }

    public void setPresence(String state, String details) {
        this.presence.state = state;
        this.presence.details = details;

        lib.Discord_UpdatePresence(this.presence);
    }

    public void setPresence(String state, String details, String imgKey, String imgText) {
        this.presence.largeImageKey = imgKey;
        this.presence.largeImageText = imgText;

        setPresence(state, details);
    }

    public void setPresence(String details, String state, String imgKey, String imgText, String smallImgKey, String smallImgText) {
        this.presence.smallImageKey = smallImgKey;
        this.presence.smallImageText = smallImgText;

        setPresence(state, details, imgKey, imgText);
    }

    public void shutdown() {
        this.lib.Discord_Shutdown();
    }
}
