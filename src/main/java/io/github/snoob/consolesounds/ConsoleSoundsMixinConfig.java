package io.github.snoob.consolesounds;

import net.fabricmc.loader.api.FabricLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class ConsoleSoundsMixinConfig implements IMixinConfigPlugin {
    @Override
    public void onLoad(String mixinPackage) {

    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetName, String mixinName) {
        if (mixinName.equals("io.github.snoob.consolesounds.mixin.SodiumCompat") && !FabricLoader.getInstance().isModLoaded("sodium")) {return  false;}
        if (mixinName.equals("io.github.snoob.consolesounds.mixin.SodiumCompat") && FabricLoader.getInstance().isModLoaded("sodium")) {ConsoleSoundsClient.LOGGER.info("Sodium Mixin Enabled"); return true;}
        else return true;
    }

    @Override
    public void acceptTargets(Set<String> set, Set<String> set1) { }

    @Override
    public List<String> getMixins() {
        return List.of();
    }

    @Override
    public void preApply(String s, ClassNode classNode, String s1, IMixinInfo iMixinInfo) {}

    @Override
    public void postApply(String s, ClassNode classNode, String s1, IMixinInfo iMixinInfo) {}
}
