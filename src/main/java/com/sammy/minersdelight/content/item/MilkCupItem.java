package com.sammy.minersdelight.content.item;

import com.sammy.minersdelight.setup.MDItems;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.item.MilkBottleItem;
import vectorwing.farmersdelight.common.utility.TextUtils;

import javax.annotation.Nullable;
import java.util.List;

public class MilkCupItem extends MilkBottleItem {

   public MilkCupItem(Item.Properties pProperties) {
      super(pProperties);
   }


   @OnlyIn(Dist.CLIENT)
   public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag isAdvanced) {
      if (Configuration.FOOD_EFFECT_TOOLTIP.get()) {
         MutableComponent textEmpty = new TranslatableComponent("farmersdelight.tooltip.milk_bottle");
         tooltip.add(textEmpty.withStyle(ChatFormatting.BLUE));
      }
   }
}