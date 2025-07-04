package com.sammy.minersdelight;

import com.sammy.minersdelight.setup.*;
import net.minecraft.resources.*;
import net.neoforged.bus.api.*;
import net.neoforged.fml.*;
import net.neoforged.fml.common.*;

@Mod(MinersDelightMod.MODID)
public class MinersDelightMod {
	public static final String MODID = "minersdelight";

	public MinersDelightMod(IEventBus modEventBus, ModContainer modContainer) {
		MDLootModifiers.LOOT_MODIFIERS.register(modEventBus);
		MDLootConditions.LOOT_CONDITIONS.register(modEventBus);

		MDWorldgen.FEATURE_TYPES.register(modEventBus);
		MDBlocks.BLOCKS.register(modEventBus);
		MDBlockEntities.BLOCK_ENTITIES.register(modEventBus);
		MDItems.ITEMS.register(modEventBus);
		MDCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
		MDPotions.POTIONS.register(modEventBus);

		MDMenuTypes.MENU_TYPES.register(modEventBus);

		modEventBus.addListener(MDCauldronInteractions::addCauldronInteractions);
		modEventBus.addListener(MDBlockEntities::registerCapabilities);
	}

	public static ResourceLocation path(String path) {
		return ResourceLocation.fromNamespaceAndPath(MODID, path);
	}
}