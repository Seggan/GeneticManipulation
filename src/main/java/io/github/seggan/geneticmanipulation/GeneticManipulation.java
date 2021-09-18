package io.github.seggan.geneticmanipulation;

import io.github.mooy1.infinitylib.core.AbstractAddon;
import io.github.seggan.geneticmanipulation.genes.DNA;
import io.github.seggan.geneticmanipulation.genes.impls.FlyingGene;
import io.github.seggan.geneticmanipulation.items.Syringe;

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
    }
}
