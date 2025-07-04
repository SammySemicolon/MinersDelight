package com.sammy.minersdelight.datagen;

import com.sammy.minersdelight.content.worldgen.*;
import com.sammy.minersdelight.setup.*;
import net.minecraft.data.worldgen.*;
import net.minecraft.util.random.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.*;

public class MDConfiguredFeatureDatagen {
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        WeightedStateProvider weightedstateprovider = new WeightedStateProvider(
                SimpleWeightedRandomList.<BlockState>builder()
                        .add(Blocks.CAVE_AIR.defaultBlockState(), 4)
                        .add(MDBlocks.WILD_CAVE_CARROTS.get().defaultBlockState(), 3)
                        .add(MDBlocks.GOSSYPIUM.get().defaultBlockState(), 1)
        );
        context.register(MDWorldgen.ConfiguredFeatures.CONFIGURED_WILD_CAVE_CARROT,
                new ConfiguredFeature<>(MDWorldgen.WILD_CAVE_CROP.get(), new WildCaveCropFeatureConfiguration(weightedstateprovider, 6, 4)));
    }
}