package com.sammy.minersdelight.datagen;

import com.sammy.minersdelight.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.data.*;
import net.minecraft.data.registries.*;
import net.neoforged.neoforge.common.data.*;
import net.neoforged.neoforge.registries.*;

import java.util.*;
import java.util.concurrent.*;

public class RegistryDataGenerator extends DatapackBuiltinEntriesProvider {

    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, MDConfiguredFeatureDatagen::bootstrap)
            .add(Registries.PLACED_FEATURE, MDPlacedFeatureDatagen::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, MDBiomeModificationDatagen::bootstrap);

    public RegistryDataGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, RegistryPatchGenerator.createLookup(registries, BUILDER), Set.of("minecraft", MinersDelightMod.MODID));
    }
}