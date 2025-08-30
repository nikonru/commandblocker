package com.nikonru.CommandBlocker.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

import java.util.List;

public class CommandBlockerConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    private static ForgeConfigSpec SPEC;

    public static ForgeConfigSpec.ConfigValue<List<? extends String>> allowedPlayers;
    public static ForgeConfigSpec.BooleanValue selfKillAllowedForAll;

    public static void load() {
        BUILDER.comment("CommandBlocker mod settings").push("command_blocker");

        allowedPlayers = BUILDER
                .comment("List of players (username) who are allowed to use commands")
                .defineList("allowedPlayers", List.of("Admin1", "Admin2"), o -> o instanceof String);

        selfKillAllowedForAll = BUILDER
                .comment("Allow /selfkill for all players even if they are not in whitelist")
                .define("selfKillAllowedForAll", true);

        BUILDER.pop();
        SPEC = BUILDER.build();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SPEC, "CommandBlocker.toml");
    }

    public static boolean isAllowed(String playerName) {
        return allowedPlayers.get().contains(playerName);
    }
}
