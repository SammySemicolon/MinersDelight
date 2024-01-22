package com.sammy.minersdelight.content.block.copper_pot;

import com.google.gson.*;
import com.sammy.minersdelight.*;
import com.sammy.minersdelight.setup.*;
import net.minecraft.nbt.*;
import net.minecraft.resources.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.storage.loot.*;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.parameters.*;
import net.minecraft.world.level.storage.loot.predicates.*;

public class CopperPotCopyMealFunction extends LootItemConditionalFunction
{
    public static final ResourceLocation ID = MinersDelightMod.path("copy_meal");

    private CopperPotCopyMealFunction(LootItemCondition[] conditions) {
        super(conditions);
    }

    public static LootItemConditionalFunction.Builder<?> builder() {
        return simpleBuilder(CopperPotCopyMealFunction::new);
    }

    @Override
    protected ItemStack run(ItemStack stack, LootContext context) {
        BlockEntity tile = context.getParamOrNull(LootContextParams.BLOCK_ENTITY);
        if (tile instanceof CopperPotBlockEntity copperPotBlockEntity) {
            CompoundTag tag = copperPotBlockEntity.writeMeal(new CompoundTag());
            if (!tag.isEmpty()) {
                stack.addTagElement("BlockEntityTag", tag);
            }
        }
        return stack;
    }

    @Override
    public LootItemFunctionType getType() {
        return MDLootFunctions.COPY_MEAL.get();
    }

    public static class Serializer extends LootItemConditionalFunction.Serializer<CopperPotCopyMealFunction> {
        @Override
        public CopperPotCopyMealFunction deserialize(JsonObject json, JsonDeserializationContext context, LootItemCondition[] conditions) {
            return new CopperPotCopyMealFunction(conditions);
        }
    }
}