package com.sammy.minersdelight;

import com.sammy.minersdelight.data.MDLangMerger;
import com.sammy.minersdelight.data.MDRecipeProvider;
import com.sammy.minersdelight.setup.*;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

@Mod(MinersDelightMod.MODID)
public class MinersDelightMod {
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MODID = "miners_delight";
	public static final Random RANDOM = new Random();
	public static final NonNullSupplier<Registrate> REGISTRATE = NonNullSupplier.lazy(() -> Registrate.create(MODID));

	public static Registrate registrate(){
		return REGISTRATE.get();
	}
	public MinersDelightMod() {
		IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
		IEventBus forgeBus = MinecraftForge.EVENT_BUS;
		MDLootModifiers.LOOT_MODIFIERS.register(modBus);
		MDMenuTypes.MENU_TYPES.register(modBus);
		MDBlocks.register();
		MDItems.register();
		MDBlockEntities.register();
		modBus.addListener(DataOnly::gatherData);
	}

	public static ResourceLocation path(String path) {
		return new ResourceLocation(MODID, path);
	}

	public static class DataOnly {
		public static void gatherData(GatherDataEvent event) {
			DataGenerator generator = event.getGenerator();
			generator.addProvider(new MDLangMerger(generator));
			generator.addProvider(new MDRecipeProvider(generator));
		}
	}
}