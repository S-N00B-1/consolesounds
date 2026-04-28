package io.github.snoob.consolesounds.mixin;

import io.github.snoob.consolesounds.ConsoleSoundsConfig;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(ItemStack.class)
public class CraftItemStackMixin {
    @Inject(at = @At("HEAD"), method = "onCraftedBy")
    private void init(Player player, int i, CallbackInfo ci) {
        if (player.equals(Minecraft.getInstance().player)) {
            if (ConsoleSoundsConfig.enableCraftingSounds) {
                float eventVolume = ConsoleSoundsConfig.craftingVolume;
                float volume = eventVolume / 100.0F;
                Minecraft.getInstance().getSoundManager().play(
                        new SimpleSoundInstance(Identifier.withDefaultNamespace("entity.item.pickup"), SoundSource.MASTER, volume, 1.0F, SoundInstance.createUnseededRandom(), false, 0, SoundInstance.Attenuation.NONE, 0.0, 0.0, 0.0, true)
                );
            }
        }
    }
}