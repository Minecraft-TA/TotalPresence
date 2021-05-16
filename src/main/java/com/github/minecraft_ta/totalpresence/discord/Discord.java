package com.github.minecraft_ta.totalpresence.discord;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;

public class Discord {

    private final DiscordRPC lib;
    private final DiscordRichPresence presence;
    private final DiscordEventHandlers handlers;

    private String applicationID;
    private String details;
    private String state;
    private long timestamp;
    private String imgKey;

    public Discord(String applicationID, String details, String state, String imgKey) {
        this.applicationID = applicationID;
        this.details = details;
        this.state = state;
        this.timestamp = System.currentTimeMillis() / 1000;
        this.imgKey = imgKey;

        this.handlers = new DiscordEventHandlers();
        this.lib = DiscordRPC.INSTANCE;
        this.presence = new DiscordRichPresence();

        init();
    }

    private void init() {
        lib.Discord_Initialize(this.applicationID, handlers, true, null);
        this.presence.details = this.details;
        this.presence.state = this.state;
        this.presence.startTimestamp = this.timestamp;

        lib.Discord_UpdatePresence(this.presence);
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getImgKey() {
        return imgKey;
    }

    public void setImgKey(String imgKey) {
        this.imgKey = imgKey;
    }
}
