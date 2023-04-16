package com.sammy.minersdelight.setup;

import com.sammy.minersdelight.*;
import com.sammy.minersdelight.content.loot.*;
import net.minecraftforge.common.loot.*;
import net.minecraftforge.registries.*;

public class MDLootModifiers {
	public static final DeferredRegister<GlobalLootModifierSerializer<?>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.LOOT_MODIFIER_SERIALIZERS, MinersDelightMod.MODID);
	public static final RegistryObject<GlobalLootModifierSerializer<?>> ADD_ITEMS = LOOT_MODIFIERS.register("add_items", AddSeveralItemsModifier.Serializer::new);
	public static final RegistryObject<GlobalLootModifierSerializer<?>> REPLACE_ITEMS = LOOT_MODIFIERS.register("replace_items", ReplaceDroppedLootModifier.Serializer::new);

}
