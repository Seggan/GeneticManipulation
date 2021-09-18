package io.github.seggan.geneticmanipulation.genes.impls;

import io.github.seggan.geneticmanipulation.genes.Gene;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import lombok.NonNull;

import java.util.EnumSet;
import java.util.Set;

public final class FlyingGene extends Gene {

    private static final Set<EntityType> FLYING = EnumSet.of(
        EntityType.WITHER,
        EntityType.ENDER_DRAGON,
        EntityType.BEE,
        EntityType.BAT,
        EntityType.PARROT,
        EntityType.CHICKEN,
        EntityType.PHANTOM,
        EntityType.VEX,
        EntityType.BLAZE,
        EntityType.GHAST
    );

    public FlyingGene() {
        super("Flight", "FLIGHT");
    }

    @Override
    public void apply(@NonNull Mob mob, @NonNull EntityType type) {
        if (!FLYING.contains(type)) {
            mob.addPotionEffect(new PotionEffect(
                PotionEffectType.LEVITATION,
                Integer.MAX_VALUE,
                0,
                false,
                false
            ));
        }
    }

    @Override
    public boolean isFoundIn(@NonNull Mob mob, @NonNull EntityType type) {
        return FLYING.contains(type);
    }
}
