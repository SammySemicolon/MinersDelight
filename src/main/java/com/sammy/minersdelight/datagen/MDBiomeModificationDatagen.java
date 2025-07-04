package com.sammy.minersdelight.datagen;

import com.sammy.minersdelight.*;
import com.sammy.minersdelight.setup.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.data.worldgen.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.placement.*;
import net.neoforged.neoforge.common.world.*;
import net.neoforged.neoforge.registries.*;

import java.util.*;
import java.util.function.*;

public class MDBiomeModificationDatagen {
    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        register(context, "wild_cave_carrot", () ->
                addFeatureModifier(context,
                        getPlacedHolderSet(context, MDWorldgen.PlacedFeatures.WILD_CAVE_CARROT),
                        MDTags.HAS_CAVE_CARROTS, GenerationStep.Decoration.VEGETAL_DECORATION));
    }

    @SafeVarargs
    public static HolderSet<PlacedFeature> getPlacedHolderSet(BootstrapContext<?> context, ResourceKey<PlacedFeature>... placedFeatures) {
        List<Holder<PlacedFeature>> holders = new ArrayList<>();
        for (ResourceKey<PlacedFeature> feature : placedFeatures) {
            holders.add(context.lookup(Registries.PLACED_FEATURE).getOrThrow(feature));
        }
        return HolderSet.direct(holders);
    }

    private static BiomeModifiers.AddFeaturesBiomeModifier addFeatureModifier(BootstrapContext<BiomeModifier> context, HolderSet<PlacedFeature> placedSet, TagKey<Biome> biomeTag, GenerationStep.Decoration decoration) {
        return new BiomeModifiers.AddFeaturesBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(biomeTag), placedSet, decoration);
    }

    private static void register(BootstrapContext<BiomeModifier> context, String name, Supplier<? extends BiomeModifier> modifier) {
        context.register(ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, MinersDelightMod.path(name)), modifier.get());
    }
}