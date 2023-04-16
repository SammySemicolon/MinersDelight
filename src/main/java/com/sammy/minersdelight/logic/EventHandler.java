package com.sammy.minersdelight.logic;

import com.sammy.minersdelight.*;
import com.sammy.minersdelight.setup.*;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.*;
import net.minecraftforge.event.*;
import net.minecraftforge.event.world.*;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.fml.common.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = MinersDelightMod.MODID)
public class EventHandler {

    @SubscribeEvent
    public static void addWorldgenFeatures(BiomeLoadingEvent event) {
        if (!event.getCategory().equals(Biome.BiomeCategory.THEEND) && !event.getCategory().equals(Biome.BiomeCategory.NETHER))
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MDWorldgen.PlacedFeatures.CAVE_CARROT);
    }
    @SubscribeEvent
    public static void registerListeners(AddReloadListenerEvent event) {
        CupConversionReloadListener.register(event);
    }
}