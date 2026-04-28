package io.github.snoob.consolesounds.mixin;

import io.github.snoob.consolesounds.ConsoleSoundsConfig;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundSource;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(Minecraft.class)
public class OpenPauseMenuMixin {
    @Inject(at = @At("TAIL"), method = "pauseGame")
    private void init(boolean pause, CallbackInfo ci) {
        if (Minecraft.getInstance().screen instanceof PauseScreen) {
            if (ConsoleSoundsConfig.playSoundOnPauseMenu) {
                float eventVolume = ConsoleSoundsConfig.onPauseMenuVolume;
                float volume = eventVolume / 100.0F;
                Minecraft.getInstance().getSoundManager().play(
                        new SimpleSoundInstance(Identifier.withDefaultNamespace("ui.button.click"), SoundSource.MASTER, volume, 1.0F, SoundInstance.createUnseededRandom(), false, 0, SoundInstance.Attenuation.NONE, 0.0, 0.0, 0.0, true)
                );
            }
        }
    }
}