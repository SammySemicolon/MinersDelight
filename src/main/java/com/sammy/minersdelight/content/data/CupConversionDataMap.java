package com.sammy.minersdelight.content.data;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import com.sammy.minersdelight.setup.*;
import net.minecraft.core.*;
import net.minecraft.world.item.*;

import java.util.*;

public record CupConversionDataMap(Holder<Item> cupVariant) {
    public static final Codec<CupConversionDataMap> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ItemStack.ITEM_NON_AIR_CODEC.fieldOf("cup_variant").forGetter(CupConversionDataMap::cupVariant)
    ).apply(instance, CupConversionDataMap::new));

    public static Optional<ItemStack> getCupVariant(ItemStack stack) {
        var data = stack.getItem().builtInRegistryHolder().getData(MDDataMaps.CUP_VARIANT);
        if (data != null) {
            ItemStack inCup = new ItemStack(data.cupVariant(), stack.getCount());
            inCup.applyComponents(stack.getComponents());
            return Optional.of(inCup);
        }
        return Optional.empty();
    }
}