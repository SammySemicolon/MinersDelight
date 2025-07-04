package com.sammy.minersdelight.setup;

import com.sammy.minersdelight.*;
import com.sammy.minersdelight.content.worldgen.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.placement.*;
import net.neoforged.neoforge.registries.*;

public class MDWorldgen {

    public static final DeferredRegister<Feature<?>> FEATURE_TYPES = DeferredRegister.create(BuiltInRegistries.FEATURE, MinersDelightMod.MODID);

    public static final DeferredHolder<Feature<?>, WildCaveCropFeature> WILD_CAVE_CROP = FEATURE_TYPES.register("wild_crop", WildCaveCropFeature::new);

    public static class ConfiguredFeatures {

        public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_WILD_CAVE_CARROT = registerKey("wild_cave_carrot");

        public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
            return ResourceKey.create(Registries.CONFIGURED_FEATURE, MinersDelightMod.path(name));
        }
    }

    public static class PlacedFeatures {

        public static final ResourceKey<PlacedFeature> WILD_CAVE_CARROT = registerKey("wild_cave_carrot");

        public static ResourceKey<PlacedFeature> registerKey(String name) {
            return ResourceKey.create(Registries.PLACED_FEATURE, MinersDelightMod.path(name));
        }
    }
}