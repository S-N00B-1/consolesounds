package io.github.snoob.consolesounds.mixin;

import io.github.snoob.consolesounds.ConsoleSoundsClient;
import io.github.snoob.consolesounds.ConsoleSoundsConfig;
import net.minecraft.client.Minecraft;
import net.caffeinemc.mods.sodium.client.gui.widgets.AbstractWidget;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(AbstractWidget.class)
public class SodiumCompat {
    @Unique private Boolean selected = false;
    @Unique private Boolean mouseOver = false;
    @Unique private Boolean focused = false;

    @Inject(at = @At("RETURN"), method = "isMouseOver")
    private void isMouseOver(CallbackInfoReturnable<Boolean> cir) {
        mouseOver = cir.getReturnValue();
        tryPlaySound();
    }

    @Inject(at = @At("RETURN"), method = "isFocused")
    private void isFocused(CallbackInfoReturnable<Boolean> cir) {
        focused = cir.getReturnValue();
        tryPlaySound();
    }

    @Unique
    private void tryPlaySound() {
        if ((mouseOver || focused) && !selected) {
            selected = true;
            if (ConsoleSoundsConfig.enableHoverSounds) {
                float eventVolume = ConsoleSoundsConfig.hoverVolume;
                float volume = eventVolume / 100.0F;
                Minecraft.getInstance().getSoundManager().play(
                        new SimpleSoundInstance(ConsoleSoundsClient.id("ui_select"), SoundSource.MASTER, volume, 1.0F, SoundInstance.createUnseededRandom(), false, 0, SoundInstance.Attenuation.NONE, 0.0, 0.0, 0.0, true)
                );
            }
        } else {
            if (!mouseOver && !focused) {
                selected = false;
            }
        }
    }
}
