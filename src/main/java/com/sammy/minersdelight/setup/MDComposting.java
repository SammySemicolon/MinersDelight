package com.sammy.minersdelight.setup;

import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraftforge.fml.event.lifecycle.*;

public class MDComposting {

    public static void addCompostValues(FMLCommonSetupEvent event) {
        registerCompostable(MDItems.CAVE_CARROT.get(), 0.65f);
        registerCompostable(MDItems.BAKED_CAVE_CARROT.get(), 0.85f);
        registerCompostable(MDBlocks.GOSSYPIUM.get().asItem(), 0.3f);
        registerCompostable(MDItems.SPIDER_LEG.get(), 0.5f);
        registerCompostable(MDItems.CRUNCHY_BAR.get(), 0.65f);
        registerCompostable(MDItems.NUTRITIONAL_BAR.get(), 0.65f);
        registerCompostable(MDItems.GOLDEN_NUTRITIONAL_BAR.get(), 0.65f);
    }

    public static void registerCompostable(Item item, float chance) {
        ComposterBlock.COMPOSTABLES.put(item, chance);
    }
}
