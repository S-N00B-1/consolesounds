package com.hattolo.consolesounds;

import com.hattolo.consolesounds.ConsoleSoundsSounds;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleSoundsClient implements ClientModInitializer {
    public static final String MOD_ID = "consolemod";
    public static final Logger LOGGER = LogManager.getLogger("Console Sounds");

	public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }

    @Override
    public void onInitializeClient() {
		ConsoleSoundsSounds.init();
        // register config
        AutoConfig.register(ConsoleSoundsConfig.class, GsonConfigSerializer::new);
        LOGGER.info("Registered config...");
    }
}
