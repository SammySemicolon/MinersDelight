package com.sammy.minersdelight.datagen;

import com.sammy.minersdelight.*;
import net.minecraft.data.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.common.data.*;
import team.lodestar.lodestone.systems.datagen.*;
import team.lodestar.lodestone.systems.datagen.itemsmith.*;
import team.lodestar.lodestone.systems.datagen.providers.*;

import java.util.*;
import java.util.function.*;

import static com.sammy.minersdelight.setup.MDItems.*;

public class MDItemModels extends LodestoneItemModelProvider {

    public MDItemModels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MinersDelightMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        Set<Supplier<? extends Item>> items = new HashSet<>(ITEMS.getEntries());
        items.removeIf(i -> i.get() instanceof BlockItem);
        ItemModelSmithData data = new ItemModelSmithData(this, items::remove);

        ItemModelSmithTypes.BLOCK_MODEL_ITEM.act(data, COPPER_POT, STICKY_BASKET);
        ItemModelSmithTypes.GENERATED_ITEM.act(data, POWDERED_SNOW_CUP, CAVE_CARROT, STUFFED_SQUID);
        ItemModelSmithTypes.GENERATED_ITEM.act(data, items);
    }

    @Override
    public String getName() {
        return "Miner's Delight Item Models";
    }
}
