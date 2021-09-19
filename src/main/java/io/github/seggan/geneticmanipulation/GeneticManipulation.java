package io.github.seggan.geneticmanipulation;

import io.github.mooy1.infinitylib.core.AbstractAddon;
import io.github.seggan.geneticmanipulation.genes.DNA;
import io.github.seggan.geneticmanipulation.genes.EffectGene;
import io.github.seggan.geneticmanipulation.genes.impls.FlyingGene;
import io.github.seggan.geneticmanipulation.items.Syringe;
import org.bukkit.entity.EntityType;
import org.bukkit.potion.PotionEffectType;

public class GeneticManipulation extends AbstractAddon {

    public GeneticManipulation() {
        super("Seggan", "GeneticManipulation", "master", "auto-update");
    }

    @Override
    protected void enable() {
        setup();
        setupGenes();
        ItemGroups.setup();
    }

    @Override
    protected void disable() {

    }

    private void setup() {
        new DNA().register(this);
        new Syringe().register(this);
    }

    private void setupGenes() {
        new FlyingGene();
        new EffectGene("Fireproof", "FIREPROOF", PotionEffectType.FIRE_RESISTANCE,
            EntityType.BLAZE,
            EntityType.GHAST,
            EntityType.ZOMBIFIED_PIGLIN,
            EntityType.WITHER_SKELETON,
            EntityType.ZOGLIN,
            EntityType.STRIDER
        );
    }
}
