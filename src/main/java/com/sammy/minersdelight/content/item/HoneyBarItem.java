package com.sammy.minersdelight.content.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.item.MilkBottleItem;

import javax.annotation.Nullable;
import java.util.List;

public class HoneyBarItem extends ConsumableItem {
   public HoneyBarItem(Properties properties) {
      super(properties, false, true);
   }

   @Override
   public void affectConsumer(ItemStack stack, Level level, LivingEntity consumer) {
      consumer.removeEffect(MobEffects.POISON);
   }

   @Override
   public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag isAdvanced) {
      if (Configuration.ENABLE_FOOD_EFFECT_TOOLTIP.get()) {
         var text = Component.translatable("tooltip.minersdelight.honey_bar");
         tooltip.add(text.withStyle(ChatFormatting.BLUE));
      }
   }
}