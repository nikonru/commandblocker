package com.nikonru.CommandBlocker;

import com.nikonru.CommandBlocker.config.CommandBlockerConfig;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "commandblocker")
public class CommandBlockerHandler {

    @SubscribeEvent
    public static void onCommand(net.minecraftforge.event.CommandEvent event) {
        CommandSourceStack source = event.getParseResults().getContext().getSource();

        if (source.getEntity() instanceof ServerPlayer player) {
            String playerName = player.getName().getString();
            
            String commandName = event.getParseResults().getReader().getString().split(" ")[0].replace("/", "");
            if (commandName.equalsIgnoreCase("selfkill") && CommandBlockerConfig.selfKillAllowedForAll.get()) {
                return;
            }

            if (!CommandBlockerConfig.isAllowed(playerName)) {
                player.sendSystemMessage(net.minecraft.network.chat.Component.translatable("You are not allowed to use commands!"));
                event.setCanceled(true);
            }
        }
    }
}
