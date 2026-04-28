package io.github.snoob.consolesounds.mixin;

import io.github.snoob.consolesounds.ConsoleSoundsClient;
import io.github.snoob.consolesounds.ConsoleSoundsConfig;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundSource;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Environment(EnvType.CLIENT)
@Mixin(AbstractWidget.class)
public class SelectClickableWidgetMixin {
	@Unique private Boolean selected = false;

	@Inject(at = @At("RETURN"), method = "isHoveredOrFocused")
	private void isHoveredOrFocused(CallbackInfoReturnable<Boolean> cir) {
		var widget = (AbstractWidget) (Object) this;
		if (cir.getReturnValue()) {
			if (!widget.active) return;
			if (!selected) {
				selected = true;
				if (ConsoleSoundsConfig.enableHoverSounds) {
					float eventVolume = ConsoleSoundsConfig.hoverVolume;
					float volume = eventVolume / 100.0F;
					Minecraft.getInstance().getSoundManager().play(
							new SimpleSoundInstance(ConsoleSoundsClient.id("ui_select"), SoundSource.MASTER, volume, 1.0F, SoundInstance.createUnseededRandom(), false, 0, SoundInstance.Attenuation.NONE, 0.0, 0.0, 0.0, true)
					);
				}
			}
		} else {
			selected = false;
		}
	}

}