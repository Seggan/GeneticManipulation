package io.github.seggan.geneticmanipulation;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import org.bukkit.Material;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class Items {

    public static final SlimefunItemStack DNA = new SlimefunItemStack(
        "DNA",
        Material.WHITE_DYE,
        "&fDNA",
        "",
        "&7The essence of life"
    );

    public static final SlimefunItemStack SYRINGE = new SlimefunItemStack(
        "SYRINGE",
        Material.STICK,
        "&fSyringe",
        "",
        "&7Obtains DNA and Enzymes from mobs",
        LoreBuilder.RIGHT_CLICK_TO_USE
    );
}
