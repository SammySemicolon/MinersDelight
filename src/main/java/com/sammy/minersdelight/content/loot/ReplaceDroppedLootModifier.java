package com.sammy.minersdelight.content.loot;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.List;

public class ReplaceDroppedLootModifier extends LootModifier
{
	private final Item addedItem;
	private final int min;
	private final int max;

	/**
	 * This loot modifier adds an item to the loot table, given the conditions specified.
	 */
	protected ReplaceDroppedLootModifier(LootItemCondition[] conditionsIn, Item addedItemIn, int min, int max) {
		super(conditionsIn);
		this.addedItem = addedItemIn;
		this.min = min;
		this.max = max;
	}

	@Nonnull
	@Override
	protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
		generatedLoot.clear();
		ItemStack addedStack = new ItemStack(addedItem, Mth.nextInt(context.getRandom(), min, max));
		if (addedStack.getCount() < addedStack.getMaxStackSize()) {
			generatedLoot.add(addedStack);
		} else {
			int i = addedStack.getCount();

			while (i > 0) {
				ItemStack subStack = addedStack.copy();
				subStack.setCount(Math.min(addedStack.getMaxStackSize(), i));
				i -= subStack.getCount();
				generatedLoot.add(subStack);
			}
		}

		return generatedLoot;
	}

	public static class Serializer extends GlobalLootModifierSerializer<ReplaceDroppedLootModifier>
	{
		@Override
		public ReplaceDroppedLootModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] ailootcondition) {
			Item addedItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation((GsonHelper.getAsString(object, "item"))));
			int min = GsonHelper.getAsInt(object, "min");
			int max = GsonHelper.getAsInt(object, "max");
			return new ReplaceDroppedLootModifier(ailootcondition, addedItem, min, max);
		}

		@Override
		public JsonObject write(ReplaceDroppedLootModifier instance) {
			return new JsonObject();
		}
	}
}
