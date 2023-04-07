package com.hattolo.consolesounds;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "consolesounds")
public class ConsoleSoundsConfig implements ConfigData {
    @Comment("Enables the sound you hear when you hover over buttons.")
    public boolean enableHoverSounds = true;
    @Comment("Enables the click sound you hear when you click on a slot on an in-game menu, like a crafting table or your inventory.")
    public boolean enableInGameClickSounds = true;
    @Comment("Enables the pop sound you hear when you craft something.")
    public boolean enableCraftingSounds = true;
    @Comment("Enables the sound you hear when you use a slider.")
    public boolean enableSliderSounds = true;
    @Comment("Plays the click sound whenever you open the pause menu. You might want to disable this if you set the game to auto pause on focus loss.")
    public boolean playSoundOnPauseMenu = true;
    @Comment("Plays the click sound whenever you open an in-game menu, like a crafting table or your inventory.")
    public boolean playSoundOnInGameMenu = true;
    @Comment("Plays the back sound whenever you close a menu.")
    public boolean playSoundOnMenuExit = true;
    @Comment("Plays the back sound whenever you close an in-game menu, like a crafting table or your inventory.")
    public boolean playSoundOnInGameMenuExit = true;
}