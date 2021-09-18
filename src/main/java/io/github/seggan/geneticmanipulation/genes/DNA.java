package io.github.seggan.geneticmanipulation.genes;

import io.github.seggan.geneticmanipulation.ItemGroups;
import io.github.seggan.geneticmanipulation.Items;
import io.github.seggan.geneticmanipulation.RecipeTypes;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.apache.commons.lang.Validate;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class DNA extends SlimefunItem {

    public DNA() {
        super(ItemGroups.GENERAL, Items.DNA, RecipeTypes.SYRINGE, new ItemStack[9]);
    }

    public static void setGenes(@NonNull ItemMeta meta, @NonNull List<Gene> genes) {
        Gene.setGenes(meta, genes);
        List<String> lore = meta.getLore();
        lore = lore == null ? new ArrayList<>() : lore;
        lore.clear();

        lore.add("The essence of life");
        lore.add("");

        for (Gene gene : genes) {
            lore.add(ChatColor.GRAY + gene.name());
        }

        meta.setLore(lore);
    }

    public static ItemStack generate(@NonNull Mob mob) {
        List<Gene> added = new ArrayList<>();
        EntityType type = mob.getType();
        for (Gene gene : Gene.allGenes().values()) {
            if (gene.isFoundIn(mob, type)) {
                added.add(gene);
            }
        }

        ItemStack stack = Items.DNA.clone();
        setGenes(stack, added);
        return stack;
    }

    public static void setGenes(@NonNull ItemStack stack, @NonNull List<Gene> genes) {
        ItemMeta meta = stack.getItemMeta();
        Validate.notNull(meta, "DNA has no meta");
        setGenes(meta, genes);
        stack.setItemMeta(meta);
    }
}
