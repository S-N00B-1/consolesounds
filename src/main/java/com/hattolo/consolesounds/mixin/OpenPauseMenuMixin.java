package com.hattolo.consolesounds.mixin;

import com.hattolo.consolesounds.ConsoleSoundsConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class OpenPauseMenuMixin {
    @Inject(at = @At("TAIL"), method = "openPauseMenu")
    private void init(boolean pause, CallbackInfo ci) {
        if (MinecraftClient.getInstance().currentScreen instanceof GameMenuScreen) {
            if (AutoConfig.getConfigHolder(ConsoleSoundsConfig.class).getConfig().playSoundOnPauseMenu) MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1.0F));
        }
    }
}
