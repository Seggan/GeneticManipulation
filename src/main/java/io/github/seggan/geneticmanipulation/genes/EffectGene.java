package io.github.seggan.geneticmanipulation.genes;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import lombok.NonNull;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

public class EffectGene extends Gene {

    private final PotionEffectType effect;
    private final Set<EntityType> types;

    public EffectGene(@NonNull String name, @NonNull String id, @NonNull PotionEffectType effect, @NonNull EntityType... types) {
        super(name, id);
        this.effect = effect;
        this.types = EnumSet.noneOf(EntityType.class);
        Collections.addAll(this.types, types);
    }

    @Override
    public void apply(@NonNull Mob mob, @NonNull EntityType type) {
        mob.addPotionEffect(new PotionEffect(
            effect,
            Integer.MAX_VALUE,
            0,
            false,
            false
        ));
    }

    @Override
    public boolean isFoundIn(@NonNull Mob mob, @NonNull EntityType type) {
        return types.contains(type);
    }
}
