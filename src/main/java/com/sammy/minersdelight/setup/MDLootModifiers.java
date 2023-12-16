package com.sammy.minersdelight.setup;

import com.mojang.serialization.*;
import com.sammy.minersdelight.*;
import com.sammy.minersdelight.content.loot.*;
import net.minecraftforge.common.loot.*;
import net.minecraftforge.registries.*;

public class MDLootModifiers {
	public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, MinersDelightMod.MODID);

	public static final RegistryObject<Codec<? extends IGlobalLootModifier>> ADD_ITEMS = LOOT_MODIFIERS.register("add_items", AddSeveralItemsModifier.CODEC);
	public static final RegistryObject<Codec<? extends IGlobalLootModifier>> REPLACE_ITEMS = LOOT_MODIFIERS.register("replace_items", ReplaceDroppedLootModifier.CODEC);

}
