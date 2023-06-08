package com.hattolo.consolesounds.mixin;

import com.hattolo.consolesounds.ConsoleSoundsConfig;
import com.hattolo.consolesounds.ConsoleSoundsSounds;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SliderWidget.class)
public class ScrollSliderWidgetMixin {
    @Shadow protected double value;

    @Inject(at = @At("HEAD"), method = "setValue")
    private void setValue(double newValue, CallbackInfo ci) {
        double v = MathHelper.clamp(roundDown2(newValue), 0.0D, 1.0D);
        double ov = MathHelper.clamp(roundDown2(value), 0.0D, 1.0D);

        if (v != ov && AutoConfig.getConfigHolder(ConsoleSoundsConfig.class).getConfig().enableSliderSounds) {
            float eventVolume = AutoConfig.getConfigHolder(ConsoleSoundsConfig.class).getConfig().sliderVolume;
            float volume = eventVolume / 100.0F;
            MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(ConsoleSoundsSounds.UI_SCROLL, 1.0F, volume));
        }

        //System.out.println("value: " + value + " oldvalue: " + oldValue + " v: " + v + " ov: " + ov);
    }

    // makes decimals not go beyond the hundredths place
    private static double roundDown2(double d) {
        return Math.floor(d * 1e2) / 1e2;
    }
}