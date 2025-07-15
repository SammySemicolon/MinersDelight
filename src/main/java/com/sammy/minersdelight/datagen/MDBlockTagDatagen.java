package com.sammy.minersdelight.datagen;

import com.sammy.minersdelight.*;
import com.sammy.minersdelight.setup.*;
import net.minecraft.core.*;
import net.minecraft.data.*;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.common.*;
import net.neoforged.neoforge.common.data.*;
import net.neoforged.neoforge.registries.*;
import team.lodestar.lodestone.systems.datagen.providers.*;
import vectorwing.farmersdelight.common.tag.ModTags;

import javax.annotation.*;
import java.util.*;
import java.util.concurrent.*;

public class MDBlockTagDatagen extends LodestoneBlockTagsProvider {

    public MDBlockTagDatagen(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MinersDelightMod.MODID, existingFileHelper);
    }

    @Override
    public String getName() {
        return "MD Block Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        Set<DeferredHolder<Block, ? extends Block>> blocks = new HashSet<>(MDBlocks.BLOCKS.getEntries());

        tag(MDTags.INFESTED_BLOCKS).addTag(Tags.Blocks.COBBLESTONES_INFESTED).add(
                Blocks.INFESTED_STONE, Blocks.INFESTED_STONE_BRICKS, Blocks.INFESTED_CRACKED_STONE_BRICKS,
                Blocks.INFESTED_MOSSY_STONE_BRICKS, Blocks.INFESTED_CHISELED_STONE_BRICKS, Blocks.INFESTED_DEEPSLATE
        );

        tag(ModTags.MINEABLE_WITH_KNIFE).add(MDBlocks.STUFFED_SQUID.get(), MDBlocks.GLAZED_ARACHNID_LIMBS.get());

        addTagsFromBlockProperties(blocks);
    }
}