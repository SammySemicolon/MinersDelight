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
        tag(ItemTags.WOLF_FOOD).addTag(MDTags.CAVE_CARROTS_CROP_ITEM).add(MDItems.BAT_WING.get(), MDItems.SMOKED_BAT_WING.get(), MDItems.BAT_COOKIE.get(), MDItems.SPIDER_LEG.get(), MDItems.BAKED_SPIDER_LEG.get());
        tag(ItemTags.CAT_FOOD).addTag(MDTags.RAW_FISHES_SQUID);

        tag(Tags.Items.CROPS).add(MDItems.CAVE_CARROT.get());
        tag(ModTags.WILD_CROPS_ITEM).add(MDItems.GOSSYPIUM.get());
        tag(MDTags.CAVE_CARROTS_CROP_ITEM).add(MDItems.CAVE_CARROT.get());
        tag(MDTags.CAVE_CARROTS_VEGETABLE_ITEM).add(MDItems.CAVE_CARROT.get());
        tag(ModTags.CABBAGE_ROLL_INGREDIENTS).add(
                MDItems.CAVE_CARROT.get(),
                MDItems.SPIDER_LEG.get(),
                MDItems.ARTHROPOD.get(),
                MDItems.CRUNCHY_BAR.get(),
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
                MDItems.SPIDER_LEG.get(),
                MDItems.BAKED_SPIDER_LEG.get(),
                MDItems.ARTHROPOD.get(),
                MDItems.COOKED_ARTHROPOD.get(),
                MDItems.CRUNCHY_BAR.get()
        );
        tag(MDTags.BC_RAW_MEATS).add(
                MDItems.SPIDER_LEG.get(),
                MDItems.ARTHROPOD.get(),
                MDItems.CRUNCHY_BAR.get());
        tag(MDTags.COOKED_INSECT_MEAT).add(
                MDItems.BAKED_SPIDER_LEG.get(),
                MDItems.COOKED_ARTHROPOD.get(),
                MDItems.CRUNCHY_BAR.get());

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

        tag(CommonTags.FOODS_SAFE_RAW_FISH).addTag(MDTags.RAW_FISHES_SQUID);
        tag(ModTags.FEASTS).add(MDItems.STUFFED_SQUID.get(), MDItems.GLAZED_ARACHNID_LIMBS.get());
    }
}