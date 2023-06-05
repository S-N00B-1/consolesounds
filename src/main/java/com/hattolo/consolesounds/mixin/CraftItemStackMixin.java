package com.hattolo.consolesounds.mixin;

import com.hattolo.consolesounds.ConsoleSoundsConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStack.class)
public class CraftItemStackMixin {
    @Inject(at = @At("HEAD"), method = "onCraft")
    private void init(CallbackInfo ci) {
        if (AutoConfig.getConfigHolder(ConsoleSoundsConfig.class).getConfig().enableCraftingSounds) MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(SoundEvents.ENTITY_ITEM_PICKUP, (AutoConfig.getConfigHolder(ConsoleSoundsConfig.class).getConfig().craftingVolume / 100)));
    }
}
