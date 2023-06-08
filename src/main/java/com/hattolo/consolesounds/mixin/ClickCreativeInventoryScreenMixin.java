package com.hattolo.consolesounds.mixin;

import com.hattolo.consolesounds.ConsoleSoundsConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreativeInventoryScreen.class)
public class ClickCreativeInventoryScreenMixin {
    @Inject(at = @At("HEAD"), method = "onMouseClick(Lnet/minecraft/screen/slot/Slot;IILnet/minecraft/screen/slot/SlotActionType;)V")
    private void onMouseClick(CallbackInfo ci) {
        if (AutoConfig.getConfigHolder(ConsoleSoundsConfig.class).getConfig().enableInGameClickSounds) {
            SoundEvent eventSound = SoundEvents.UI_BUTTON_CLICK.get();
            float eventVolume = AutoConfig.getConfigHolder(ConsoleSoundsConfig.class).getConfig().inGameClickVolume;
            float volume = eventVolume / 100.0F;
            MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(eventSound, 1.0F, volume));
        }
    }
}