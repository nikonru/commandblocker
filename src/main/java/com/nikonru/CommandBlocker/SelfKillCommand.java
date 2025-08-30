package com.nikonru.CommandBlocker;

import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import com.mojang.brigadier.Command;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "commandblocker")
public class SelfKillCommand {

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(
            Commands.literal("selfkill")
                .executes(context -> {
                    ServerPlayer player = context.getSource().getPlayerOrException();

                    player.kill();
                    player.sendSystemMessage(net.minecraft.network.chat.Component.literal("You killed yourself!"));

                    return Command.SINGLE_SUCCESS;
                })
        );
    }

}
