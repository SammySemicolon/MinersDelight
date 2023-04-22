package com.sammy.minersdelight.setup;

import com.sammy.minersdelight.*;
import net.minecraft.core.*;
import net.minecraft.data.*;
import net.minecraft.data.worldgen.features.*;
import net.minecraft.resources.*;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.*;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.event.lifecycle.*;

import java.util.*;

@Mod.EventBusSubscriber(modid= MinersDelightMod.MODID, bus= Mod.EventBusSubscriber.Bus.MOD)
public class MDWorldgen {

    public static Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_CAVE_CARROT;
    public static Holder<PlacedFeature> CAVE_CARROT;


    public static void addWorldgen(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            PATCH_CAVE_CARROT = register(MinersDelightMod.path("patch_cave_carrot"), Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(MDBlocks.WILD_CAVE_CARROTS.getDefaultState()))));

            CAVE_CARROT = register(MinersDelightMod.path("patch_cave_carrot"), PATCH_CAVE_CARROT, List.of(
                    RarityFilter.onAverageOnceEvery(32),
                    CountPlacement.of(6),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(10), VerticalAnchor.absolute(48)),
                    BiomeFilter.biome()));

        });
    }

    public static Holder<PlacedFeature> register(ResourceLocation pName, Holder<? extends ConfiguredFeature<?, ?>> pFeature, List<PlacementModifier> pPlacements) {
        return BuiltinRegistries.register(BuiltinRegistries.PLACED_FEATURE, pName, new PlacedFeature(Holder.hackyErase(pFeature), List.copyOf(pPlacements)));
    }

    protected static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<FC, ?>> register(ResourceLocation id, F feature, FC featureConfig) {
        return register(BuiltinRegistries.CONFIGURED_FEATURE, id, new ConfiguredFeature<>(feature, featureConfig));
    }

    private static <V extends T, T> Holder<V> register(Registry<T> registry, ResourceLocation id, V value) {
        return (Holder<V>) BuiltinRegistries.register(registry, id, value);
    }
}