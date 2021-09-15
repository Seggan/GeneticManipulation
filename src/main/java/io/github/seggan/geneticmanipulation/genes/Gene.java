package io.github.seggan.geneticmanipulation.genes;

import org.bukkit.entity.Mob;

import lombok.Getter;
import lombok.NonNull;

import java.util.HashSet;
import java.util.Set;

public abstract class Gene {

    @Getter
    private static final Set<Gene> allGenes = new HashSet<>();
    @Getter
    private final String name;

    public Gene(@NonNull String name) {
        this.name = name;
        allGenes.add(this);
    }


    public abstract void apply(@NonNull Mob mob);
}
