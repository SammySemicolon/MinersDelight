package com.sammy.minersdelight.content.item;

import net.minecraft.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.item.*;
import vectorwing.farmersdelight.common.*;
import vectorwing.farmersdelight.common.item.*;
import vectorwing.farmersdelight.common.utility.*;

import java.util.*;

public class HotCocoaCupItem extends HotCocoaItem {
    public HotCocoaCupItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag isAdvanced) {
        if (Configuration.ENABLE_FOOD_EFFECT_TOOLTIP.get()) {
            var text = Component.translatable("farmersdelight.tooltip.hot_cocoa");
            tooltip.add(text.withStyle(ChatFormatting.BLUE));
            TextUtils.addFoodEffectTooltip(stack, tooltip::add, 1.0F, context.tickRate());
        }
    }
}