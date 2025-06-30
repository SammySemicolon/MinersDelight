package com.sammy.minersdelight.setup;

import net.minecraft.core.*;
import net.minecraft.sounds.*;
import net.minecraft.stats.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.gameevent.*;
import net.minecraftforge.fml.event.lifecycle.*;

import static net.minecraft.core.cauldron.CauldronInteraction.*;

public class MDCauldronInteractions {

    public static void addCauldronInteractions(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            EMPTY.put(MDItems.WATER_CUP.get(), (p_175683_, p_175684_, p_175685_, p_175686_, p_175687_, p_175688_) -> emptyCup(p_175684_, p_175685_, p_175686_, p_175687_, p_175688_, Blocks.WATER_CAULDRON.defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, Integer.valueOf(3)), SoundEvents.BUCKET_EMPTY));
            EMPTY.put(MDItems.POWDERED_SNOW_CUP.get(), (p_175669_, p_175670_, p_175671_, p_175672_, p_175673_, p_175674_) -> emptyCup(p_175670_, p_175671_, p_175672_, p_175673_, p_175674_, Blocks.POWDER_SNOW_CAULDRON.defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, Integer.valueOf(3)), SoundEvents.BUCKET_EMPTY_POWDER_SNOW));

            WATER.put(MDItems.COPPER_CUP.get(), (p_175725_, p_175726_, p_175727_, p_175728_, p_175729_, p_175730_) -> fillBucket(p_175725_, p_175726_, p_175727_, p_175728_, p_175729_, p_175730_, MDItems.WATER_CUP.asStack(), (p_175660_) -> p_175660_.getValue(LayeredCauldronBlock.LEVEL) == 3, SoundEvents.BUCKET_FILL));
            POWDER_SNOW.put(MDItems.COPPER_CUP.get(), (p_175690_, p_175691_, p_175692_, p_175693_, p_175694_, p_175695_) -> fillBucket(p_175690_, p_175691_, p_175692_, p_175693_, p_175694_, p_175695_, MDItems.POWDERED_SNOW_CUP.asStack(), (p_175627_) -> p_175627_.getValue(LayeredCauldronBlock.LEVEL) == 3, SoundEvents.BUCKET_FILL_POWDER_SNOW));
        });
    }

    static InteractionResult emptyCup(Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, ItemStack pFilledStack, BlockState pState, SoundEvent pEmptySound) {
        if (!pLevel.isClientSide) {
            Item item = pFilledStack.getItem();
            pPlayer.setItemInHand(pHand, ItemUtils.createFilledResult(pFilledStack, pPlayer, MDItems.COPPER_CUP.asStack()));
            pPlayer.awardStat(Stats.FILL_CAULDRON);
            pPlayer.awardStat(Stats.ITEM_USED.get(item));
            pLevel.setBlockAndUpdate(pPos, pState);
            pLevel.playSound(null, pPos, pEmptySound, SoundSource.BLOCKS, 1.0F, 1.0F);
            pLevel.gameEvent(null, GameEvent.FLUID_PLACE, pPos);
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide);
    }
}
