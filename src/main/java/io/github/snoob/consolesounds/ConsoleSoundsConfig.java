package io.github.snoob.consolesounds;

import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.Identifier;

public class ConsoleSoundsConfig {
    private static Object GsonBuilder;
    public static ConfigClassHandler<ConsoleSoundsConfig> HANDLER = ConfigClassHandler.createBuilder(ConsoleSoundsConfig.class)
            .id(Identifier.fromNamespaceAndPath(ConsoleSoundsClient.MOD_ID, "consolesounds"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(FabricLoader.getInstance().getConfigDir().resolve("consolesounds.json"))
                    .setJson5(false)
                    .build())
            .build();

    @SerialEntry
    public static boolean enableHoverSounds = true;

    @SerialEntry
    public static boolean enableInGameClickSounds = true;

    @SerialEntry
    public static boolean enableCraftingSounds = true;

    @SerialEntry
    public static boolean enableSliderSounds = true;

    @SerialEntry
    public static boolean playSoundOnPauseMenu = true;

    @SerialEntry
    public static boolean playSoundOnInGameMenu = true;

    @SerialEntry
    public static boolean playSoundOnMenuExit = true;

    @SerialEntry
    public static boolean playSoundOnInGameMenuExit = true;

    @SerialEntry
    public static int hoverVolume = 100;

    @SerialEntry
    public static int  inGameClickVolume = 100;

    @SerialEntry
    public static int craftingVolume = 100;

    @SerialEntry
    public static int sliderVolume = 100;

    @SerialEntry
    public static int onPauseMenuVolume = 100;

    @SerialEntry
    public static int inGameMenuVolume = 100;

    @SerialEntry
    public static int menuExitVolume = 100;

    @SerialEntry
    public static int onInGameMenuExitVolume = 100;
}

