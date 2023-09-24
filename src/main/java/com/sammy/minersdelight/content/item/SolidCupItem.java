package com.sammy.minersdelight.content.item;

import com.sammy.minersdelight.setup.*;
import net.minecraft.core.*;
import net.minecraft.sounds.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.phys.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class SolidCupItem extends BlockItem implements DispensibleContainerItem {
   private final SoundEvent placeSound;

   public SolidCupItem(Block pBlock, SoundEvent pPlaceSound, Item.Properties pProperties) {
      super(pBlock, pProperties);
      this.placeSound = pPlaceSound;
   }

   @Override
   public InteractionResult useOn(UseOnContext pContext) {
      InteractionResult interactionresult = super.useOn(pContext);
      Player player = pContext.getPlayer();
      if (interactionresult.consumesAction() && player != null && !player.isCreative()) {
         InteractionHand interactionhand = pContext.getHand();
         player.setItemInHand(interactionhand, MDItems.COPPER_CUP.asStack());
      }
      return interactionresult;
   }

   @Override
   public void registerBlocks(Map<Block, Item> pBlockToItemMap, Item pItem) {
   }

   @Override
   public String getDescriptionId() {
      return this.getOrCreateDescriptionId();
   }

   @Override
   protected SoundEvent getPlaceSound(BlockState pState) {
      return this.placeSound;
   }

   @Override
   public boolean emptyContents(@Nullable Player pPlayer, Level pLevel, BlockPos pPos, @Nullable BlockHitResult pResult) {
      if (pLevel.isInWorldBounds(pPos) && pLevel.isEmptyBlock(pPos)) {
         if (!pLevel.isClientSide) {
            pLevel.setBlock(pPos, this.getBlock().defaultBlockState(), 3);
         }

         pLevel.playSound(pPlayer, pPos, this.placeSound, SoundSource.BLOCKS, 1.0F, 1.0F);
         return true;
      } else {
         return false;
      }
   }
}