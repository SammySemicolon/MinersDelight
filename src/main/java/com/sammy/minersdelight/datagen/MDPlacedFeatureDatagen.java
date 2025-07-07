package com.sammy.minersdelight.datagen;

import com.google.common.collect.*;
import com.sammy.minersdelight.setup.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.data.worldgen.*;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.placement.*;

public class MDPlacedFeatureDatagen {
    @SuppressWarnings("deprecation")
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> features = context.lookup(Registries.CONFIGURED_FEATURE);
        context.register(MDWorldgen.PlacedFeatures.WILD_CAVE_CARROT,
                new PlacedFeature(features.getOrThrow(MDWorldgen.ConfiguredFeatures.CONFIGURED_WILD_CAVE_CARROT),
                        ImmutableList.<PlacementModifier>builder().add(
                                        RarityFilter.onAverageOnceEvery(24),
                                        HeightRangePlacement.uniform(
                                                VerticalAnchor.aboveBottom(12),
                                                VerticalAnchor.absolute(48)),
                                        CountOnEveryLayerPlacement.of(5),
                                        BiomeFilter.biome())
                                .build()
                ));
    }
}