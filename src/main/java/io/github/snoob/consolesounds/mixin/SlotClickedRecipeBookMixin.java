package io.github.snoob.consolesounds.mixin;

import io.github.snoob.consolesounds.ConsoleSoundsClient;
import io.github.snoob.consolesounds.ConsoleSoundsConfig;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeCollection;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.world.item.crafting.display.RecipeDisplayId;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.sounds.SoundSource;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(RecipeBookComponent.class)
public abstract class SlotClickedRecipeBookMixin {

    @Shadow @Nullable private RecipeDisplayId lastPlacedRecipe;

    @Inject(at = @At("HEAD"), method = "fillGhostRecipe(Lnet/minecraft/world/item/crafting/display/RecipeDisplay;)V")
    private void onCraftFailed(RecipeDisplay display, CallbackInfo ci) {
        if (ConsoleSoundsConfig.enableCraftingSounds) {
            float eventVolume = ConsoleSoundsConfig.craftingVolume;
            float volume = eventVolume / 100.0F;
            Minecraft.getInstance().getSoundManager().play(
                    new SimpleSoundInstance(ConsoleSoundsClient.id("ui_fail"), SoundSource.MASTER, volume, 1.0F, SoundInstance.createUnseededRandom(), false, 0, SoundInstance.Attenuation.NONE, 0.0, 0.0, 0.0, true)
            );
        }
    }

    @Inject(at = @At("HEAD"), method = "tryPlaceRecipe")
    private void select(RecipeCollection results, RecipeDisplayId recipeId, boolean bl, CallbackInfoReturnable<Boolean> cir) {
        if (ConsoleSoundsConfig.enableCraftingSounds) {
            if (!results.isCraftable(recipeId) && recipeId.equals(lastPlacedRecipe)) {
                float eventVolume = ConsoleSoundsConfig.craftingVolume;
                float volume = eventVolume / 100.0F;
                Minecraft.getInstance().getSoundManager().play(
                        new SimpleSoundInstance(ConsoleSoundsClient.id("ui_fail"), SoundSource.MASTER, volume, 1.0F, SoundInstance.createUnseededRandom(), false, 0, SoundInstance.Attenuation.NONE, 0.0, 0.0, 0.0, true)
                );
            }
        }
    }
}