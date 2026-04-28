package com.sammy.minersdelight.content.item;

import net.minecraft.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.EffectCures;
import vectorwing.farmersdelight.common.*;
import vectorwing.farmersdelight.common.item.*;

import java.util.*;

public class HoneyBarItem extends ConsumableItem {
    public HoneyBarItem(Properties properties) {
        super(properties, false, true);
    }

    @Override
    public void affectConsumer(ItemStack stack, Level level, LivingEntity consumer) {
        consumer.removeEffectsCuredBy(EffectCures.HONEY);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag isAdvanced) {
        if (Configuration.ENABLE_FOOD_EFFECT_TOOLTIP.get()) {
            var text = Component.translatable("farmersdelight.tooltip.honey_bar");
            tooltip.add(text.withStyle(ChatFormatting.BLUE));
        }
    }
}