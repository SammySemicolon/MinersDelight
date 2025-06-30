package com.sammy.minersdelight.setup;

import com.sammy.minersdelight.*;
import com.sammy.minersdelight.content.block.*;
import com.sammy.minersdelight.content.block.copper_pot.*;
import com.sammy.minersdelight.content.block.sticky_basket.*;
import net.minecraft.core.registries.*;
import net.minecraft.tags.*;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.registries.*;
import team.lodestar.lodestone.systems.block.*;
import vectorwing.farmersdelight.common.registry.*;
import vectorwing.farmersdelight.common.tag.*;

import java.util.function.*;

public class MDBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, MinersDelightMod.MODID);

    public static final Supplier<Block> COPPER_POT = BLOCKS.register("copper_pot",
            () -> new CopperPotBlock(LodestoneBlockProperties.of().setCutoutRenderType().needsPickaxe().strength(2.25F).sound(SoundType.COPPER)));

    public static final Supplier<Block> STICKY_BASKET = BLOCKS.register("sticky_basket",
            () -> new StickyBasketBlock(LodestoneBlockProperties.of().setCutoutRenderType().needsAxe().strength(1.5F).sound(SoundType.BAMBOO_WOOD)));

    public static final Supplier<Block> STUFFED_SQUID = BLOCKS.register("stuffed_squid",
            () -> new StuffedSquidFeastBlock(LodestoneBlockProperties.copy(Blocks.CAKE).needsAxe()));

    public static final Supplier<Block> WILD_CAVE_CARROTS = BLOCKS.register("wild_cave_carrots",
            () -> new WildCaveCarrotBlock(LodestoneBlockProperties.copy(Blocks.TALL_GRASS).addTags(BlockTags.SMALL_FLOWERS, ModTags.WILD_CROPS, ModTags.COMPOST_ACTIVATORS).randomTicks().setCutoutRenderType()));

    public static final Supplier<Block> CAVE_CARROTS = BLOCKS.register("cave_carrots",
            () -> new CaveCarrotBlock(LodestoneBlockProperties.copy(Blocks.CARROTS).addTags(BlockTags.CROPS, MDTags.CAVE_CARROTS_CROP_BLOCK).setCutoutRenderType()));

    public static final Supplier<Block> CAVE_CARROT_CRATE = BLOCKS.register("cave_carrot_crate",
            () -> new Block(LodestoneBlockProperties.copy(ModBlocks.CARROT_CRATE.get()).needsAxe()));

    public static final Supplier<Block> GOSSYPIUM = BLOCKS.register("gossypium",
            () -> new GossypiumFlowerBlock(LodestoneBlockProperties.copy(Blocks.TALL_GRASS).addTag(ModTags.WILD_CROPS).setCutoutRenderType()));
}