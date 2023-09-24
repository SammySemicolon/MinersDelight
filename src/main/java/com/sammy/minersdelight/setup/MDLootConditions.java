package com.sammy.minersdelight.setup;

import com.sammy.minersdelight.*;
import com.sammy.minersdelight.content.loot.*;
import net.minecraft.core.registries.*;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraftforge.registries.*;

public class MDLootConditions {
	public static final DeferredRegister<LootItemConditionType> LOOT_CONDITIONS = DeferredRegister.create(Registries.LOOT_CONDITION_TYPE, MinersDelightMod.MODID);

	public static final RegistryObject<LootItemConditionType> BLOCK_TAG_CONDITION = LOOT_CONDITIONS.register("block_tag", ()->new LootItemConditionType(new LootItemBlockTagCondition.Serializer()));
}
