package com.sammy.minersdelight.setup;

import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraftforge.fml.event.lifecycle.*;

public class MDComposting {

    public static void addCompostValues(FMLCommonSetupEvent event) {
        registerCompostable(MDItems.CAVE_CARROT.get(), 0.65f);
        registerCompostable(MDItems.BAKED_CAVE_CARROT.get(), 0.85f);
        registerCompostable(MDBlocks.GOSSYPIUM.get().asItem(), 0.3f);
    }

    public static void registerCompostable(Item item, float chance) {
        ComposterBlock.COMPOSTABLES.put(item, chance);
    }
}
