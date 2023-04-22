package com.sammy.minersdelight.content.item;

import net.minecraft.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraftforge.api.distmarker.*;
import vectorwing.farmersdelight.common.*;
import vectorwing.farmersdelight.common.item.*;
import vectorwing.farmersdelight.common.utility.*;

import javax.annotation.*;
import java.util.*;

public class HotCocoaCupItem extends HotCocoaItem {
    public HotCocoaCupItem(Properties properties) {
        super(properties);
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag isAdvanced) {
        if (Configuration.FOOD_EFFECT_TOOLTIP.get()) {
            MutableComponent textEmpty = Component.translatable("farmersdelight.tooltip.hot_cocoa");
            tooltip.add(textEmpty.withStyle(ChatFormatting.BLUE));
            TextUtils.addFoodEffectTooltip(stack, tooltip, 1.0F);
        }
    }
}