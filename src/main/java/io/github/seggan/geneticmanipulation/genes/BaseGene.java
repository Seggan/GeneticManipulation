package io.github.seggan.geneticmanipulation.genes;

import io.github.thebusybiscuit.slimefun4.utils.ChatUtils;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;

import lombok.Getter;
import lombok.NonNull;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

public final class BaseGene extends Gene {

    public static final Set<EntityType> DISALLOWED_TYPES = EnumSet.of(
        EntityType.IRON_GOLEM,
        EntityType.SNOWMAN,
        EntityType.GUARDIAN,
        EntityType.ELDER_GUARDIAN,
        EntityType.PLAYER
    );

    @Getter
    private static final Map<EntityType, BaseGene> allBaseGenes = new EnumMap<>(EntityType.class);

    static {
        for (EntityType type : EntityType.values()) {
            if (!type.isAlive()) {
                DISALLOWED_TYPES.add(type);
            }
            if (!DISALLOWED_TYPES.contains(type)) {
                String name;
                if (type == EntityType.MUSHROOM_COW) {
                    name = "Mooshroom";
                } else {
                    name = ChatUtils.humanize(type.name());
                }

                allBaseGenes.put(type, new BaseGene(name, type));
            }
        }
    }

    @Getter
    private final EntityType entityType;

    private BaseGene(@NonNull String name, @NonNull EntityType type) {
        super("Base Gene: " + name, "BASE_GENE_" + type.name());
        entityType = type;
    }

    @Override
    public void apply(@NonNull Mob mob) {
    }

    @Override
    public boolean isFoundIn(@NonNull Mob mob) {
        return mob.getType() == this.entityType;
    }
}
