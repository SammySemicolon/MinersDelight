package com.sammy.minersdelight.setup;

import com.sammy.minersdelight.*;
import com.sammy.minersdelight.content.block.copper_pot.*;
import net.minecraft.core.registries.*;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraftforge.registries.*;

public class MDLootFunctions
{
    public static final DeferredRegister<LootItemFunctionType> LOOT_FUNCTIONS = DeferredRegister.create(Registries.LOOT_FUNCTION_TYPE, MinersDelightMod.MODID);

    public static final RegistryObject<LootItemFunctionType> COPY_MEAL = LOOT_FUNCTIONS.register("copy_meal", () -> new LootItemFunctionType(new CopperPotCopyMealFunction.Serializer()));
}