package com.hattolo.consolesounds.mixin;

import com.hattolo.consolesounds.ConsoleSoundsConfig;
import com.hattolo.consolesounds.ConsoleSoundsSounds;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.sound.PositionedSoundInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public class CloseScreenMixin {
    @Inject(at = @At("HEAD"), method = "close()V")
    private void onClose(CallbackInfo ci) {
        if (AutoConfig.getConfigHolder(ConsoleSoundsConfig.class).getConfig().playSoundOnMenuExit) {
					MinecraftClient.getInstance().getSoundManager().play(
					new PositionedSoundInstance(ConsoleSoundsSounds.UI_BACK, SoundCategory.MASTER, (AutoConfig.getConfigHolder(ConsoleSoundsConfig.class).getConfig().menuExitVolume / 100), 1.0F, 0F, 0F, 0F));
				}
    }
}
