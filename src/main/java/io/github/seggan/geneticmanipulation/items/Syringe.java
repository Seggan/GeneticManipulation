package io.github.seggan.geneticmanipulation.items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.EntityInteractHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class Syringe extends SimpleSlimefunItem<EntityInteractHandler> {

    protected Syringe(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Nonnull
    @Override
    public EntityInteractHandler getItemHandler() {
        return null;
    }
}
