package io.github.seggan.geneticmanipulation;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class RecipeTypes {

    public static final RecipeType SYRINGE = new RecipeType(
        GeneticManipulation.createKey("syringe"),
        Items.SYRINGE
    );
}
