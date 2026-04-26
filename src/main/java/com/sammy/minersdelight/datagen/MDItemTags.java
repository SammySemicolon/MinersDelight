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
        tag(ModTags.Items.WILD_CROPS).add(MDItems.GOSSYPIUM.get());
        tag(MDTags.CAVE_CARROTS_CROP_ITEM).add(MDItems.CAVE_CARROT.get());
        tag(MDTags.CAVE_CARROTS_VEGETABLE_ITEM).add(MDItems.CAVE_CARROT.get());

        tag(MDTags.BAKED_CAVE_CARROT).add(
                MDItems.BAKED_CAVE_CARROT.get(),
                MDItems.VEGAN_PATTY.get()
        );

        tag(MDTags.BAT_WING).add(
                MDItems.BAT_WING.get(),
                MDItems.SMOKED_BAT_WING.get()
        );

        tag(MDTags.RAW_INSECT_MEAT).add(
                MDItems.SPIDER_LEG.get(),
                MDItems.ARTHROPOD.get()
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

        tag(CommonTags.Items.FOODS_SAFE_RAW_FISH).addTag(MDTags.RAW_FISHES_SQUID);
        tag(ModTags.Items.FEASTS).add(MDItems.STUFFED_SQUID.get(), MDItems.FAKE_MEATLOAF.get(), MDItems.GLAZED_ARACHNID_LIMBS.get());

        tag(Tags.Items.FOODS_RAW_MEAT).addTag(MDTags.RAW_INSECT_MEAT);
        tag(Tags.Items.FOODS_SOUP).add(MDItems.CAVE_SOUP.get(), MDItems.BAT_SOUP.get(), MDItems.INSECT_STEW.get());
        tag(Tags.Items.FOODS).add(MDItems.CAVE_CARROT.get(), MDItems.BAKED_CAVE_CARROT.get(), MDItems.COPPER_CARROT.get(), MDItems.PASTA_WITH_VEGGIEBALLS.get(), MDItems.CAVE_SOUP.get(), MDItems.VEGAN_PATTY.get(), MDItems.VEGAN_HAMBURGER.get(), MDItems.VEGAN_WRAP.get(), MDItems.VEGAN_STEAK_AND_POTATOES.get(), MDItems.BAT_WING.get(), MDItems.SMOKED_BAT_WING.get(), MDItems.BAT_ROLLS.get(), MDItems.CAVE_HAMBURGER.get(), MDItems.BAT_SOUP.get(), MDItems.IMPROVISED_BARBECUE_STICK.get(), MDItems.BAT_COOKIE.get(), MDItems.SPIDER_LEG.get(), MDItems.BAKED_SPIDER_LEG.get(), MDItems.SILVERFISH_EGGS.get(), MDItems.WEIRD_CAVIAR.get(), MDItems.ARTHROPOD.get(), MDItems.COOKED_ARTHROPOD.get(), MDItems.INSECT_SANDWICH.get(), MDItems.INSECT_WRAP.get(), MDItems.INSECT_STEW.get(), MDItems.SEASONED_ARTHROPODS.get(), MDItems.CRUNCHY_BAR.get(), MDItems.NUTRITIONAL_BAR.get(), MDItems.GOLDEN_NUTRITIONAL_BAR.get(), MDItems.GLOW_INK_PASTA.get(), MDItems.GLOW_SQUID.get(), MDItems.SQUID.get(), MDItems.BAKED_SQUID.get(), MDItems.TENTACLES.get(), MDItems.BAKED_TENTACLES.get(), MDItems.SQUID_SANDWICH.get(), MDItems.TAKOYAKI.get(), MDItems.TENTACLES_ON_A_STICK.get(), MDItems.BOWL_OF_STUFFED_SQUID.get(), MDItems.MOSS.get());
        tag(ModTags.Items.MEALS).add(MDItems.CAVE_SOUP.get(), MDItems.BAT_SOUP.get(), MDItems.INSECT_STEW.get(), MDItems.GLOW_INK_PASTA.get(), MDItems.PASTA_WITH_VEGGIEBALLS.get(), MDItems.VEGAN_STEAK_AND_POTATOES.get(), MDItems.PLATE_OF_FAKE_MEATLOAF.get(), MDItems.SEASONED_ARTHROPODS.get(), MDItems.PLATE_OF_GLAZED_ARACHNID_LIMBS.get(), MDItems.BOWL_OF_STUFFED_SQUID.get());
        tag(Tags.Items.FOODS_GOLDEN).add(MDItems.GOLDEN_NUTRITIONAL_BAR.get());
        tag(Tags.Items.FOODS_COOKED_MEAT).addTag(MDTags.COOKED_INSECT_MEAT).add(MDItems.VEGAN_PATTY.get());
        tag(Tags.Items.FOODS_COOKIE).add(MDItems.BAT_COOKIE.get());
        tag(Tags.Items.FOODS_VEGETABLE).add(MDItems.CAVE_CARROT.get());

        tag(ItemTags.PIGLIN_LOVED).add(MDItems.GOLDEN_NUTRITIONAL_BAR.get());
    }
}