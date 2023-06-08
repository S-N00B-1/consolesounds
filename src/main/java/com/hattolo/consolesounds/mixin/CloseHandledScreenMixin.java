package com.hattolo.consolesounds.mixin;

import com.hattolo.consolesounds.ConsoleSoundsConfig;
import com.hattolo.consolesounds.ConsoleSoundsSounds;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.sound.PositionedSoundInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HandledScreen.class)
public class CloseHandledScreenMixin {
    @Inject(at = @At("HEAD"), method = "close()V")
    private void onClose(CallbackInfo ci) {
        //System.out.println("Menu closed");
        if (AutoConfig.getConfigHolder(ConsoleSoundsConfig.class).getConfig().playSoundOnInGameMenuExit) {
            SoundEvent eventSound = SoundEvents.UI_BACK.get();
            float eventVolume = AutoConfig.getConfigHolder(ConsoleSoundsConfig.class).getConfig().onInGameMenuExitVolume;
            float volume = eventVolume / 100.0F;
            MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(eventSound, 1.0F, volume));
        }

    }
}