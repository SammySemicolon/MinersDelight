package com.sammy.minersdelight.data;

import com.sammy.minersdelight.*;
import com.sammy.minersdelight.setup.MDBlocks;
import com.sammy.minersdelight.setup.MDTags;
import net.minecraft.core.*;
import net.minecraft.data.*;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.*;
import org.jetbrains.annotations.*;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.concurrent.*;

public class MDBlockTags extends BlockTagsProvider {

    public MDBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MinersDelightMod.MODID, existingFileHelper);
    }

    @Override
    public String getName() {
        return "MD Block Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(MDTags.INFESTED_BLOCKS).add(
                Blocks.INFESTED_STONE,
                Blocks.INFESTED_STONE_BRICKS,
                Blocks.INFESTED_CRACKED_STONE_BRICKS,
                Blocks.INFESTED_MOSSY_STONE_BRICKS,
                Blocks.INFESTED_CHISELED_STONE_BRICKS,
                Blocks.INFESTED_DEEPSLATE
        );

        tag(ModTags.Blocks.FEASTS).add(
                MDBlocks.STUFFED_SQUID.get(),
                MDBlocks.GLAZED_ARACHNID_LIMBS.get(),
                MDBlocks.FAKE_MEATLOAF.get()
        );

        tag(ModTags.Blocks.MINEABLE_WITH_KNIFE).add(
                MDBlocks.STUFFED_SQUID.get(),
                MDBlocks.GLAZED_ARACHNID_LIMBS.get(),
                MDBlocks.FAKE_MEATLOAF.get()
        );

        tag(net.minecraft.tags.BlockTags.MINEABLE_WITH_PICKAXE).add(
                MDBlocks.COPPER_POT.get()
        );

        tag(net.minecraft.tags.BlockTags.MINEABLE_WITH_AXE).add(
                MDBlocks.STICKY_BASKET.get(),
                MDBlocks.STUFFED_SQUID.get(),
                MDBlocks.FAKE_MEATLOAF.get(),
                MDBlocks.GLAZED_ARACHNID_LIMBS.get(),
                MDBlocks.CAVE_CARROT_CRATE.get()
        );

        tag(net.minecraft.tags.BlockTags.CROPS).add(
                MDBlocks.CAVE_CARROTS.get()
        );

        tag(MDTags.CAVE_CARROTS_CROP_BLOCK).add(
                MDBlocks.CAVE_CARROTS.get()
        );

        tag(net.minecraft.tags.BlockTags.SMALL_FLOWERS).add(
                MDBlocks.WILD_CAVE_CARROTS.get()
        );

        tag(ModTags.Blocks.WILD_CROPS).add(
                MDBlocks.WILD_CAVE_CARROTS.get()
        );

        tag(ModTags.Blocks.COMPOST_ACTIVATORS).add(
                MDBlocks.WILD_CAVE_CARROTS.get()
        );

        tag(net.minecraft.tags.BlockTags.FLOWER_POTS).add(
                MDBlocks.POTTED_GOSSYPIUM.get()
        );
    }
}