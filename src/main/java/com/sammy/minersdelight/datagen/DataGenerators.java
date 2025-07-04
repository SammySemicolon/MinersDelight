package com.sammy.minersdelight.datagen;

import com.sammy.minersdelight.*;
import net.minecraft.core.*;
import net.minecraft.data.*;
import net.neoforged.bus.api.*;
import net.neoforged.fml.common.*;
import net.neoforged.neoforge.common.data.*;
import net.neoforged.neoforge.data.event.*;

import java.util.concurrent.*;

@EventBusSubscriber(modid = MinersDelightMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();

        boolean includeClient = event.includeClient();
        boolean includeServer = event.includeServer();

        var itemModels = new MDItemModels(output, helper);
        var blockStates = new MDBlockStateDatagen(output, helper, itemModels);
        var langDatagen = new MDLangDatagen(output);

        var registryDataDatagen = new RegistryDataGenerator(output, provider);
        var registryProvider = registryDataDatagen.getRegistryProvider();

        generator.addProvider(includeServer, registryDataDatagen);

        var dataMapsDatagen = new MDDataMapDatagen(output, registryProvider);
        var blockLootDatagen = new MDBlockLootTables(output, registryProvider);
        var blockTagDatagen = new MDBlockTagDatagen(output, registryProvider, helper);
        var itemTagDatagen = new MDItemTags(output, registryProvider, blockTagDatagen.contentsGetter(), helper);
        var biomeTagDatagen = new MDBiomeTagDatagen(output, registryProvider, helper);
        var recipeDatagen = new MDRecipeProvider(output, registryProvider);

        generator.addProvider(includeClient, itemModels);
        generator.addProvider(includeClient, blockStates);
        generator.addProvider(includeClient, langDatagen);


        generator.addProvider(includeServer, dataMapsDatagen);
        generator.addProvider(includeServer, blockLootDatagen);
        generator.addProvider(includeServer, blockTagDatagen);
        generator.addProvider(includeServer, itemTagDatagen);
        generator.addProvider(includeServer, biomeTagDatagen);
        generator.addProvider(includeServer, recipeDatagen);
    }
}
