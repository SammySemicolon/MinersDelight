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

        var blockTagsProvider = new MDBlockTags(output, provider, helper);
        generator.addProvider(includeServer, blockTagsProvider);
        generator.addProvider(includeServer, new MDItemTags(output, provider, blockTagsProvider.contentsGetter(), helper));
        generator.addProvider(includeServer, new MDDataMapDatagen(output, provider));
        generator.addProvider(includeServer, new MDRecipeProvider(output, provider));

        generator.addProvider(includeClient, new MDLangDatagen(output));

        var itemModels = new MDItemModels(output, helper);
        generator.addProvider(includeClient, new MDItemModels(output, helper));
        generator.addProvider(includeClient, new MDBlockStates(output, helper, itemModels));
    }
}
