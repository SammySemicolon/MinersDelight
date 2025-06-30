package com.sammy.minersdelight.datagen;

import com.sammy.minersdelight.*;
import com.sammy.minersdelight.setup.*;
import net.minecraft.core.*;
import net.minecraft.data.*;
import net.minecraft.data.tags.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.common.*;
import net.neoforged.neoforge.common.data.*;
import vectorwing.farmersdelight.common.tag.*;

import javax.annotation.*;
import java.util.concurrent.*;

public class MDItemTags extends ItemTagsProvider {

    public MDItemTags(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, MinersDelightMod.MODID, existingFileHelper);
    }

    @Override
    public String getName() {
        return "MD Item Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(MDTags.MOSS).add(Items.MOSS_BLOCK, MDItems.MOSS.get());

        tag(Tags.Items.BUCKETS_MILK).add(MDItems.MILK_CUP.get());

        tag(Tags.Items.CROPS).add(MDItems.CAVE_CARROT.get());
        tag(ModTags.WILD_CROPS_ITEM).add(MDItems.GOSSYPIUM.get());
        tag(MDTags.CAVE_CARROTS_CROP_ITEM).add(MDItems.CAVE_CARROT.get());
        tag(MDTags.CAVE_CARROTS_VEGETABLE_ITEM).add(MDItems.CAVE_CARROT.get());
        tag(ModTags.CABBAGE_ROLL_INGREDIENTS).add(
                MDItems.CAVE_CARROT.get(),
                MDItems.ARTHROPOD.get(),
                MDItems.SQUID.get(),
                MDItems.GLOW_SQUID.get(),
                MDItems.TENTACLES.get()
        );

        tag(MDTags.BAKED_CAVE_CARROT).add(
                MDItems.BAKED_CAVE_CARROT.get(),
                MDItems.VEGAN_PATTY.get()
        );

        tag(MDTags.BAT_WING).add(
                MDItems.BAT_WING.get(),
                MDItems.SMOKED_BAT_WING.get()
        );

        tag(MDTags.INSECT_MEAT).add(
                MDItems.ARTHROPOD.get(),
                MDItems.COOKED_ARTHROPOD.get()
        );
        tag(MDTags.BC_RAW_MEATS).add(MDItems.ARTHROPOD.get());
        tag(MDTags.COOKED_INSECT_MEAT).add(MDItems.COOKED_ARTHROPOD.get());

        tag(ItemTags.FISHES).add(
                MDItems.SQUID.get(),
                MDItems.GLOW_SQUID.get(),
                MDItems.BAKED_SQUID.get()
        );
        tag(MDTags.SQUID).add(
                MDItems.SQUID.get(),
                MDItems.GLOW_SQUID.get(),
                MDItems.BAKED_SQUID.get()
        );
        tag(MDTags.RAW_FISHES_SQUID).add(
                MDItems.SQUID.get(),
                MDItems.GLOW_SQUID.get(),
                MDItems.TENTACLES.get()
        );
        tag(MDTags.GLOW_SQUID).add(MDItems.GLOW_SQUID.get());
        tag(MDTags.COOKED_FISHES_SQUID).add(MDItems.BAKED_SQUID.get());
        tag(MDTags.TENTACLES).add(
                MDItems.TENTACLES.get(),
                MDItems.BAKED_TENTACLES.get()
        );
    }
}