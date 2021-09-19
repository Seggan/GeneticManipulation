package io.github.seggan.geneticmanipulation.items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class Enzyme extends SlimefunItem {

    public Enzyme(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Nullable
    public abstract List<ItemStack> getOutput(@Nonnull List<ItemStack> input);
    public abstract int getTime();
}
