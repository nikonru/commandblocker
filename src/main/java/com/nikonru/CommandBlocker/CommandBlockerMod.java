package com.nikonru.CommandBlocker;

import com.nikonru.CommandBlocker.config.CommandBlockerConfig;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod("commandblocker")
public class CommandBlockerMod {
    public CommandBlockerMod() {
        CommandBlockerConfig.load();

        MinecraftForge.EVENT_BUS.register(new CommandBlockerHandler());
    }
}