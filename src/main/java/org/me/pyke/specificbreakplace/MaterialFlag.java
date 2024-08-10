package org.me.pyke.specificbreakplace;

import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.InvalidFlagFormat;
import com.sk89q.worldguard.protection.flags.FlagContext;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class MaterialFlag extends Flag<Material> {

    public MaterialFlag() {
        super("material");
    }

    @Override
    public Material parseInput(@NotNull FlagContext context) throws InvalidFlagFormat {
        String input = context.getUserInput();
        try {
            return Material.valueOf(input.toUpperCase(Locale.ENGLISH));
        } catch (IllegalArgumentException e) {
            throw new InvalidFlagFormat("Invalid material: " + input);
        }
    }

    @Override
    public Material unmarshal(@NotNull Object o) {
        return Material.valueOf(o.toString());
    }

    @Override
    public Object marshal(Material material) {
        return material.name();
    }
}
