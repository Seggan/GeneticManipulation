package io.github.seggan.geneticmanipulation.genes;

import io.github.seggan.geneticmanipulation.GeneticManipulation;
import io.github.thebusybiscuit.slimefun4.libraries.dough.common.CommonPatterns;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;
import org.bukkit.persistence.PersistentDataHolder;

import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;

@Getter
public abstract class Gene {

    public static final NamespacedKey GENES_KEY = GeneticManipulation.createKey("stored_genes");

    @Getter
    private static final Map<String, Gene> allGenes = new HashMap<>();
    private final String name;
    private final String id;

    public Gene(@NonNull String name, @NonNull String id) {
        this.name = name;
        this.id = id;
        allGenes.put(this.id, this);
    }

    @Nonnull
    public static List<Gene> getGenes(@NonNull PersistentDataHolder holder) {
        List<Gene> genes = new ArrayList<>();
        String s = PersistentDataAPI.getString(holder, GENES_KEY);
        if (s != null) {
            for (String split : CommonPatterns.SEMICOLON.split(s)) {
                Gene gene = allGenes.get(split);
                if (gene != null) {
                    genes.add(gene);
                }
            }
        }

        return genes;
    }

    public static void setGenes(@NonNull PersistentDataHolder holder, @NonNull List<Gene> genes) {
        List<String> ids = new ArrayList<>(genes.size());
        genes.forEach(g -> ids.add(g.id));
        PersistentDataAPI.setString(holder, GENES_KEY, String.join(";", ids));
    }

    public static void copyGenes(@NonNull PersistentDataHolder source, @NonNull PersistentDataHolder destination) {
        String s = PersistentDataAPI.getString(source, GENES_KEY);
        if (s != null) {
            PersistentDataAPI.setString(destination, GENES_KEY, s);
        }
    }

    public static void addGenes(@NonNull PersistentDataHolder holder, @NonNull Gene... genes) {
        String s = PersistentDataAPI.getString(holder, GENES_KEY);
        if (s != null && genes.length > 0) {
            StringBuilder builder = new StringBuilder(s);
            for (Gene gene : genes) {
                builder.append(';');
                builder.append(gene.id);
            }
            PersistentDataAPI.setString(holder, GENES_KEY, builder.toString());
        }
    }

    public abstract void apply(@NonNull Mob mob, @NonNull EntityType type);
    public abstract boolean isFoundIn(@NonNull Mob mob, @NonNull EntityType type);
}
