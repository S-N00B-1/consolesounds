package io.github.snoob.consolesounds;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.resources.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleSoundsClient implements ClientModInitializer {
    public static final String MOD_ID = "consolemod";
    public static final Logger LOGGER = LogManager.getLogger("Console Sounds");

	public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    @Override
    public void onInitializeClient() {
        ConsoleSoundsConfig.HANDLER.load();
        LOGGER.info("Loaded Console Sounds config");
    }
}
