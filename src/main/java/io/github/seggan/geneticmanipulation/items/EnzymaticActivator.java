package io.github.seggan.geneticmanipulation.items;

import io.github.mooy1.infinitylib.machines.AbstractMachineBlock;
import io.github.mooy1.infinitylib.machines.MachineLayout;
import io.github.mooy1.infinitylib.machines.MenuBlock;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineProcessHolder;
import io.github.thebusybiscuit.slimefun4.core.machines.MachineOperation;
import io.github.thebusybiscuit.slimefun4.core.machines.MachineProcessor;
import io.github.thebusybiscuit.slimefun4.implementation.operations.CraftingOperation;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class EnzymaticActivator extends AbstractMachineBlock implements MachineProcessHolder<CraftingOperation> {

    private static final int[] BACKGROUND = new int[] {4, 8, 17};
    private static final int[] INPUT_SUGAR = new int[] {3, 9, 10, 11, 12};
    private static final int[] INPUT_MATERIALS = new int[] {18, 19, 20, 21, 30, 39, 48};
    private static final int[] INPUT_ENZYME = new int[] {5, 7, 14, 15, 16};
    private static final int[] OUTPUT = new int[] {22, 23, 24, 25, 26, 31, 35, 40, 44, 49, 53};

    private static final int[] SUGAR_SLOTS = new int[] {0, 1, 2};
    private static final int[] INPUT = new int[] {27, 28, 29, 36, 37, 38, 45, 46, 47};
    private static final int[] OUTPUT_SLOTS = new int[] {32, 33, 34, 41, 42, 43, 50, 51, 52};
    private static final int ENZYME_SLOT = 6;

    private final MachineProcessor<CraftingOperation> processor = new MachineProcessor<>(this);

    public EnzymaticActivator(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
        this.energyPerTick = 16;
        this.energyCapacity = 64;
        processor.setProgressBar(PROCESSING_ITEM);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected boolean process(Block b, BlockMenu menu) {
        CraftingOperation operation = processor.getOperation(b);
        if (operation == null) {
            SlimefunItem item = SlimefunItem.getByItem(menu.getItemInSlot(ENZYME_SLOT));
            if (item instanceof Enzyme enzyme) {
                List<ItemStack> inputs = new ArrayList<>();
                for (int slot : INPUT) {
                    ItemStack stack = menu.getItemInSlot(slot);
                    if (stack == null || stack.getType().isAir()) continue;
                    inputs.add(stack);
                }
                if (inputs.isEmpty()) return false;

                List<ItemStack> result = enzyme.getOutput(inputs);
                if (result == null || result.isEmpty()) return false;

                processor.startOperation(b, new CraftingOperation(
                    inputs.toArray(ItemStack[]::new),
                    result.toArray(ItemStack[]::new),
                    enzyme.getTime()
                ));

                return true;
            }

            return false;
        } else {
            if (operation.isFinished()) {
                menu.replaceExistingItem(getStatusSlot(), IDLE_ITEM);
                for (ItemStack item : operation.getResults()) {
                    ItemStack notFit = menu.pushItem(item.clone(), OUTPUT_SLOTS);
                    if (notFit != null) {
                        b.getWorld().dropItemNaturally(b.getLocation().add(0, 1, 0), notFit);
                    }
                }
                processor.endOperation(b);
                return false;
            } else {
                processor.updateProgressBar(menu, getStatusSlot(), operation);
                operation.addProgress(1);
                return true;
            }
        }
    }

    @Override
    protected int getStatusSlot() {
        return 13;
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void setup(BlockMenuPreset preset) {
        preset.drawBackground(BACKGROUND);
        drawItems(preset, INPUT_BORDER, INPUT_MATERIALS);
        drawItems(preset, new CustomItemStack(
            Material.CYAN_STAINED_GLASS_PANE,
            "&9Input Sugar"
        ), INPUT_SUGAR);
        drawItems(preset, new CustomItemStack(
            Material.CYAN_STAINED_GLASS_PANE,
            "&9Input Enzyme"
        ), INPUT_ENZYME);
        drawItems(preset, OUTPUT_BORDER, OUTPUT);
    }



    @Override
    protected int[] getInputSlots() {
        return new int[0];
    }

    @Override
    protected int[] getOutputSlots() {
        return new int[0];
    }

    private static void drawItems(@Nonnull BlockMenuPreset preset, @Nonnull ItemStack item, @Nonnull int[] slots) {
        for (int slot : slots) {
            preset.addItem(slot, item, ChestMenuUtils.getEmptyClickHandler());
        }
    }

    @Nonnull
    @Override
    public MachineProcessor<CraftingOperation> getMachineProcessor() {
        return processor;
    }
}
