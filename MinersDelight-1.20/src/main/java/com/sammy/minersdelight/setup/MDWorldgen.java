package com.sammy.minersdelight.setup;

import com.sammy.minersdelight.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.placement.*;

public class MDWorldgen {

    public static ResourceKey<ConfiguredFeature<?, ?>> FEATURE_PATCH_CAVE_CARROTS = ResourceKey.create(Registries.CONFIGURED_FEATURE, MinersDelightMod.path("patch_cave_carrots"));
    public static ResourceKey<PlacedFeature> PATCH_CAVE_CARROTS = ResourceKey.create(Registries.PLACED_FEATURE, MinersDelightMod.path("patch_cave_carrots"));

    public static void register() {

    }
}
