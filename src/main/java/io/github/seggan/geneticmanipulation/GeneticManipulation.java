package io.github.seggan.geneticmanipulation;

import io.github.mooy1.infinitylib.core.AbstractAddon;
import io.github.seggan.geneticmanipulation.genes.DNA;

public class GeneticManipulation extends AbstractAddon {

    public GeneticManipulation() {
        super("Seggan", "GeneticManipulation", "master", "auto-update");
    }

    @Override
    protected void enable() {
        setup();
    }

    @Override
    protected void disable() {

    }

    private void setup() {
        new DNA().register(this);
    }
}
