package com.sammy.minersdelight.setup;

import com.sammy.minersdelight.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.block.*;

public class MDTags {

    public static final TagKey<Biome> HAS_CAVE_CARROTS = modBiome("has_cave_carrots");

    public static final TagKey<Block> INFESTED_BLOCKS = modBlock("infested_blocks");

    public static final TagKey<Block> CAVE_CARROTS_CROP_BLOCK = commonBlock("crops/cave_carrot");
    public static final TagKey<Item> CAVE_CARROTS_VEGETABLE_ITEM = commonItem("vegetables/cave_carrot");
    public static final TagKey<Item> CAVE_CARROTS_CROP_ITEM = commonItem("crops/cave_carrot");
    public static final TagKey<Item> BAKED_CAVE_CARROT = modItem("baked_cave_carrot");

    public static final TagKey<Item> MOSS = commonItem("moss");

    public static final TagKey<Item> RAW_INSECT_MEAT = modItem("raw_insect_meat");
    public static final TagKey<Item> COOKED_INSECT_MEAT = modItem("cooked_insect_meat");

    public static final TagKey<Item> BAT_WING = commonItem("foods/bat_wing");
    public static final TagKey<Item> SQUID = commonItem("foods/squid");
    public static final TagKey<Item> GLOW_SQUID = commonItem("foods/glow_squid");
    public static final TagKey<Item> RAW_FISHES_SQUID = commonItem("foods/raw_squid");
    public static final TagKey<Item> COOKED_FISHES_SQUID = commonItem("foods/cooked_squid");
    public static final TagKey<Item> TENTACLES = commonItem("foods/tentacles");

    public static final TagKey<Item> BC_RAW_MEATS = modItem("brewinandchewin:raw_meats");


    private static TagKey<Item> modItem(String path) {
        return ItemTags.create(path.contains(":") ? ResourceLocation.parse(path) : MinersDelightMod.path(path));
    }

    private static TagKey<Block> modBlock(String path) {
        return BlockTags.create(path.contains(":") ? ResourceLocation.parse(path) : MinersDelightMod.path(path));
    }

    private static TagKey<Biome> modBiome(String path) {
        return TagKey.create(Registries.BIOME, (path.contains(":") ? ResourceLocation.parse(path) : MinersDelightMod.path(path)));
    }

    private static TagKey<Item> commonItem(String path) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", path));
    }

    private static TagKey<Block> commonBlock(String path) {
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath("forge", path));
    }
}