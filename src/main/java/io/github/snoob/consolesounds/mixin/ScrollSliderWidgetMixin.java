package io.github.snoob.consolesounds.mixin;

import io.github.snoob.consolesounds.ConsoleSoundsClient;
import io.github.snoob.consolesounds.ConsoleSoundsConfig;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(AbstractSliderButton.class)
public class ScrollSliderWidgetMixin {
    @Shadow protected double value;

    @Inject(at = @At("HEAD"), method = "setValue")
    private void setValue(double newValue, CallbackInfo ci) {
        double v = Mth.clamp(roundDown2(newValue), 0.0D, 1.0D);
        double ov = Mth.clamp(roundDown2(value), 0.0D, 1.0D);

        if (v != ov && ConsoleSoundsConfig.enableSliderSounds) {
            float eventVolume = ConsoleSoundsConfig.sliderVolume;
            float volume = eventVolume / 100.0F;
            Minecraft.getInstance().getSoundManager().play(
                    new SimpleSoundInstance(ConsoleSoundsClient.id("ui_scroll"), SoundSource.MASTER, volume, 1.0F, SoundInstance.createUnseededRandom(), false, 0, SoundInstance.Attenuation.NONE, 0.0, 0.0, 0.0, true)
            );
        }
    }

    // makes decimals not go beyond the hundredths place
    @Unique
    private static double roundDown2(double d) {
        return Math.floor(d * 1e2) / 1e2;
    }
}