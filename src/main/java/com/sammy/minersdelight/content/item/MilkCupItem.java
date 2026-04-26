package com.sammy.minersdelight.content.item;

import net.minecraft.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.item.*;
import vectorwing.farmersdelight.common.*;
import vectorwing.farmersdelight.common.item.*;

import java.util.*;

public class MilkCupItem extends MilkBottleItem {

   public MilkCupItem(Item.Properties pProperties) {
      super(pProperties);
   }

   @Override
   public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag isAdvanced) {
      if (Configuration.ENABLE_COOKING_POT_RECIPE_BOOK.get()) {
         var text = Component.translatable("tooltip.farmersdelight.milk_bottle");
         tooltip.add(text.withStyle(ChatFormatting.BLUE));
      }
   }
}