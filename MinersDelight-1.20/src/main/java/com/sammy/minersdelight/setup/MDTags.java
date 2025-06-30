package com.sammy.minersdelight.setup;

import com.sammy.minersdelight.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;

public class MDTags {
    public static final TagKey<Block> CAVE_CARROTS_CROP_BLOCK = forgeBlockTag("crops/cave_carrot");
    public static final TagKey<Item> CAVE_CARROTS_VEGETABLE_ITEM = forgeItemTag("vegetables/cave_carrot");
    public static final TagKey<Item> CAVE_CARROTS_CROP_ITEM = forgeItemTag("crops/cave_carrot");
    public static final TagKey<Item> BAKED_CAVE_CARROT = modItemTag("baked_cave_carrot");

    public static final TagKey<Item> MOSS = forgeItemTag("moss");

    public static final TagKey<Item> BAT_WING = modItemTag("bat_wing");
    public static final TagKey<Item> INSECT_MEAT = modItemTag("insect_meat");
    public static final TagKey<Item> COOKED_INSECT_MEAT = modItemTag("cooked_insect_meat");

    public static final TagKey<Item> SQUID = forgeItemTag("squid");
    public static final TagKey<Item> GLOW_SQUID = forgeItemTag("glow_squid");
    public static final TagKey<Item> RAW_FISHES_SQUID = forgeItemTag("raw_fishes/squid");
    public static final TagKey<Item> COOKED_FISHES_SQUID = forgeItemTag("cooked_fishes/squid");
    public static final TagKey<Item> TENTACLES = forgeItemTag("tentacles");

    public static final TagKey<Item> BC_RAW_MEATS = modItemTag("brewinandchewin:raw_meats");


    private static TagKey<Item> modItemTag(String path) {
        return ItemTags.create(path.contains(":") ? new ResourceLocation(path) : MinersDelightMod.path(path));
    }

    private static TagKey<Block> modBlockTag(String path) {
        return BlockTags.create(path.contains(":") ? new ResourceLocation(path) : MinersDelightMod.path(path));
    }

    private static TagKey<Item> forgeItemTag(String path) {
        return ItemTags.create(new ResourceLocation("forge", path));
    }

    private static TagKey<Block> forgeBlockTag(String path) {
        return BlockTags.create(new ResourceLocation("forge", path));
    }
}
