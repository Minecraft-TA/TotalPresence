package com.github.minecraft_ta.totalpresence.handler;

import com.github.minecraft_ta.totalpresence.TotalPresence;
import com.github.minecraft_ta.totalpresence.config.TotalPresenceConfig;
import com.github.minecraft_ta.totalpresence.discord.Discord;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DiscordHandler {

    @SubscribeEvent
    public void onWorldJoin(EntityJoinWorldEvent event) {
        if (event.getWorld().isRemote) {
            if (event.getEntity() instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) event.getEntity();
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onMenuLoad(GuiOpenEvent event) {
        if (event.getGui() instanceof GuiMainMenu) {
            Discord discord = TotalPresence.INSTANCE.getDiscord();
            TotalPresenceConfig config = TotalPresence.INSTANCE.getConfig();
        }
    }
}
