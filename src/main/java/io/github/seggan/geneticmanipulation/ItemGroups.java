package io.github.seggan.geneticmanipulation;

import io.github.mooy1.infinitylib.groups.MultiGroup;
import io.github.mooy1.infinitylib.groups.SubGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class ItemGroups {

    public static final ItemGroup GENES = new SubGroup(
        "genes",
        new CustomItemStack(Material.RED_DYE, "&fGenetic Manipulation - Genes")
    );

    public static final ItemGroup GENERAL = new SubGroup(
        "general",
        new CustomItemStack(Material.STICK, "&fGenetic Manipulation - General")
    );

    void setup() {
        MultiGroup group = new MultiGroup(
            "genetic_manipulation",
            new CustomItemStack(Material.PRISMARINE_CRYSTALS, "&fGenetic Manipulation"),
            GENERAL, GENES
        );
        group.register(GeneticManipulation.instance());
    }
}
