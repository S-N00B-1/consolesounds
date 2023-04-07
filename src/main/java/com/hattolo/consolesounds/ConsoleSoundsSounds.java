package com.hattolo.consolesounds;

import com.hattolo.consolesounds.ConsoleSoundsClient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.sound.SoundEvent;


public final class ConsoleSoundsSounds {

    public static final SoundEvent UI_SELECT = register("ui_select");

	public static final SoundEvent UI_BACK = register("ui_back");

	public static final SoundEvent UI_SCROLL = register("ui_scroll");

	public static final SoundEvent UI_FAIL = register("ui_fail");

	private static SoundEvent register(String name) {
        Identifier id = ConsoleSoundsClient.id(name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }
    
    public static void init() {
    }
}
