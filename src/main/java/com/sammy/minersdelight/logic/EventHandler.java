package com.sammy.minersdelight.logic;

import com.sammy.minersdelight.MinersDelightMod;
import com.sammy.minersdelight.setup.MDWorldgen;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = MinersDelightMod.MODID)
public class EventHandler {

    @SubscribeEvent
    public static void addWorldgenFeatures(BiomeLoadingEvent event) {
        if (!event.getCategory().equals(Biome.BiomeCategory.THEEND) && !event.getCategory().equals(Biome.BiomeCategory.NETHER))
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MDWorldgen.PlacedFeatures.CAVE_CARROT);
    }
}