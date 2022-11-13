package com.sammy.minersdelight.setup;

import com.sammy.minersdelight.MinersDelightMod;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.HashMap;
import java.util.function.Supplier;

import static com.sammy.minersdelight.setup.MDItems.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = MinersDelightMod.MODID)
public class CupConversionHandler {
    public static final HashMap<Item, Item> BOWL_TO_CUP = new HashMap<>();

    @SubscribeEvent
    public static void addCupConversions(FMLCommonSetupEvent event) {

        BOWL_TO_CUP.put(CAVE_SOUP.get(), CAVE_SOUP_CUP.get());
        BOWL_TO_CUP.put(Items.BEETROOT_SOUP, BEETROOT_SOUP_CUP.get());
        BOWL_TO_CUP.put(Items.MUSHROOM_STEW, MUSHROOM_STEW_CUP.get());
        BOWL_TO_CUP.put(Items.RABBIT_STEW, RABBIT_STEW_CUP.get());
        BOWL_TO_CUP.put(ModItems.BAKED_COD_STEW.get(), BAKED_COD_STEW_CUP.get());
        BOWL_TO_CUP.put(ModItems.NOODLE_SOUP.get(), NOODLE_SOUP_CUP.get());
        BOWL_TO_CUP.put(ModItems.BEEF_STEW.get(), BEEF_STEW_CUP.get());
        BOWL_TO_CUP.put(ModItems.CHICKEN_SOUP.get(), CHICKEN_SOUP_CUP.get());
        BOWL_TO_CUP.put(ModItems.FISH_STEW.get(), FISH_STEW_CUP.get());
        BOWL_TO_CUP.put(ModItems.HOT_COCOA.get(), HOT_COCOA_CUP.get());
        BOWL_TO_CUP.put(ModItems.PUMPKIN_SOUP.get(), PUMPKIN_SOUP_CUP.get());
        BOWL_TO_CUP.put(ModItems.VEGETABLE_SOUP.get(), VEGETABLE_SOUP_CUP.get());
    }
}