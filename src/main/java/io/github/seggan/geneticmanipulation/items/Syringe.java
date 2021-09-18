package io.github.seggan.geneticmanipulation.items;

import io.github.seggan.geneticmanipulation.GeneticManipulation;
import io.github.seggan.geneticmanipulation.ItemGroups;
import io.github.seggan.geneticmanipulation.Items;
import io.github.seggan.geneticmanipulation.genes.BaseGene;
import io.github.seggan.geneticmanipulation.genes.DNA;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.EntityInteractHandler;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nonnull;

public class Syringe extends SimpleSlimefunItem<EntityInteractHandler> {

    private static final NamespacedKey TAKEN = GeneticManipulation.createKey("taken");

    public Syringe() {
        super(ItemGroups.GENERAL, Items.SYRINGE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            null, new ItemStack(Material.GLASS), null,
            null, SlimefunItems.DAMASCUS_STEEL_INGOT, null,
            null, SlimefunItems.DAMASCUS_STEEL_INGOT, null
        });
    }

    @Nonnull
    @Override
    public EntityInteractHandler getItemHandler() {
        return (e, item, offHand) -> {
            Entity entity = e.getRightClicked();
            if (BaseGene.DISALLOWED_TYPES.contains(entity.getType()) || !(entity instanceof Mob mob)) {
                e.getPlayer().sendMessage(ChatColor.RED + "Invalid mob");
            } else if (entity.getPersistentDataContainer().has(TAKEN, PersistentDataType.BYTE)) {
                e.getPlayer().sendMessage(ChatColor.RED + "You have already taken blood from this mob");
            } else {
                entity.getWorld().dropItemNaturally(entity.getLocation(), DNA.generate(mob));
                PersistentDataAPI.setByte(entity, TAKEN, (byte) 1);
            }
        };
    }
}
