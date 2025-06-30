package com.sammy.minersdelight.datagen;

import com.sammy.minersdelight.content.data.*;
import com.sammy.minersdelight.setup.*;
import net.minecraft.core.*;
import net.minecraft.data.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.common.data.*;
import net.neoforged.neoforge.registries.datamaps.builtin.*;
import vectorwing.farmersdelight.common.registry.*;

import java.util.concurrent.*;

public class MDDataMapDatagen extends DataMapProvider {

    protected MDDataMapDatagen(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather() {
        builder(MDDataMaps.CUP_VARIANT)
                .add(Items.BUCKET.builtInRegistryHolder(), new CupConversionDataMap(MDItems.COPPER_CUP), false)
                .add(Items.WATER_BUCKET.builtInRegistryHolder(), new CupConversionDataMap(MDItems.WATER_CUP), false)
                .add(Items.MILK_BUCKET.builtInRegistryHolder(), new CupConversionDataMap(MDItems.MILK_CUP), false)
                .add(Items.POWDER_SNOW_BUCKET.builtInRegistryHolder(), new CupConversionDataMap(MDItems.POWDERED_SNOW_CUP), false)

                // Vanilla soups
                .add(Items.BEETROOT_SOUP.builtInRegistryHolder(), new CupConversionDataMap(MDItems.BEETROOT_SOUP_CUP), false)
                .add(Items.MUSHROOM_STEW.builtInRegistryHolder(), new CupConversionDataMap(MDItems.MUSHROOM_STEW_CUP), false)
                .add(Items.RABBIT_STEW.builtInRegistryHolder(), new CupConversionDataMap(MDItems.RABBIT_STEW_CUP), false)

                // Modded soups
                .add(ModItems.BAKED_COD_STEW.get().builtInRegistryHolder(), new CupConversionDataMap(MDItems.BAKED_COD_STEW_CUP), false)
                .add(ModItems.NOODLE_SOUP.get().builtInRegistryHolder(), new CupConversionDataMap(MDItems.NOODLE_SOUP_CUP), false)
                .add(ModItems.BEEF_STEW.get().builtInRegistryHolder(), new CupConversionDataMap(MDItems.BEEF_STEW_CUP), false)
                .add(ModItems.CHICKEN_SOUP.get().builtInRegistryHolder(), new CupConversionDataMap(MDItems.CHICKEN_SOUP_CUP), false)
                .add(ModItems.FISH_STEW.get().builtInRegistryHolder(), new CupConversionDataMap(MDItems.FISH_STEW_CUP), false)
                .add(ModItems.PUMPKIN_SOUP.get().builtInRegistryHolder(), new CupConversionDataMap(MDItems.PUMPKIN_SOUP_CUP), false)
                .add(ModItems.VEGETABLE_SOUP.get().builtInRegistryHolder(), new CupConversionDataMap(MDItems.VEGETABLE_SOUP_CUP), false)

                // MD-specific soups
                .add(MDItems.CAVE_SOUP.get().builtInRegistryHolder(), new CupConversionDataMap(MDItems.CAVE_SOUP_CUP), false)
                .add(MDItems.BAT_SOUP.get().builtInRegistryHolder(), new CupConversionDataMap(MDItems.BAT_SOUP_CUP), false)
                .add(MDItems.INSECT_STEW.get().builtInRegistryHolder(), new CupConversionDataMap(MDItems.INSECT_STEW_CUP), false)
                .add(ModItems.BONE_BROTH.get().builtInRegistryHolder(), new CupConversionDataMap(MDItems.BONE_BROTH_CUP), false);


        builder(NeoForgeDataMaps.COMPOSTABLES)
                .add(MDItems.CAVE_CARROT, new Compostable(0.65f), false)
                .add(MDItems.BAKED_CAVE_CARROT, new Compostable(0.65f), false)
                .add(MDItems.GOSSYPIUM, new Compostable(0.3f), false);
    }
}