package org.me.pyke.specificbreakplace;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.SetFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

public final class SpecificBreakPlace extends JavaPlugin {

    public static SetFlag<Material> CUSTOM_BLOCK_PLACE;
    public static SetFlag<Material> CUSTOM_BLOCK_BREAK;

    @Override
    public void onLoad() {
        FlagRegistry registry = WorldGuard.getInstance().getFlagRegistry();
        try {
            CUSTOM_BLOCK_PLACE = new SetFlag<>("custom-block-place", new MaterialFlag());
            CUSTOM_BLOCK_BREAK = new SetFlag<>("custom-block-break", new MaterialFlag());
            registry.register(CUSTOM_BLOCK_PLACE);
            registry.register(CUSTOM_BLOCK_BREAK);
        } catch (FlagConflictException e) {
            Flag<?> existingPlace = registry.get("custom-block-place");
            if (existingPlace instanceof SetFlag) {
                CUSTOM_BLOCK_PLACE = (SetFlag<Material>) existingPlace;
            }
            Flag<?> existingBreak = registry.get("custom-block-break");
            if (existingBreak instanceof SetFlag) {
                CUSTOM_BLOCK_BREAK = (SetFlag<Material>) existingBreak;
            } else {
                getLogger().warning("Conflict detected with the custom flags.");
            }
        }
    }

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("SpecificBreakPlace enabled successfully");

        getServer().getPluginManager().registerEvents(new SpecificBreakPlaceListener(), this);
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("SpecificBreakPlace disabled successfully");
    }
}
