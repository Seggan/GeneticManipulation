package io.github.seggan.geneticmanipulation.genes;

import io.github.thebusybiscuit.slimefun4.utils.ChatUtils;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;

import lombok.Getter;
import lombok.NonNull;

import java.util.EnumMap;
import java.util.Map;

public final class BaseGene extends Gene {

    @Getter
    private static final Map<EntityType, BaseGene> allBaseGenes = new EnumMap<>(EntityType.class);

    static {
        for (EntityType type : EntityType.values()) {
            if (type.isAlive() && type != EntityType.IRON_GOLEM && type != EntityType.SNOWMAN &&
                type != EntityType.GUARDIAN && type != EntityType.ELDER_GUARDIAN) {
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
        super("Base Gene: " + name);
        entityType = type;
    }

    @Override
    public void apply(@NonNull Mob mob) {

    }
}
