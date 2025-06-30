package com.sammy.minersdelight.content.item;

import com.sammy.minersdelight.content.data.*;
import com.sammy.minersdelight.setup.*;
import net.minecraft.advancements.*;
import net.minecraft.core.*;
import net.minecraft.core.particles.*;
import net.minecraft.server.level.*;
import net.minecraft.sounds.*;
import net.minecraft.stats.*;
import net.minecraft.tags.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.gameevent.*;
import net.minecraft.world.level.material.*;
import net.minecraft.world.phys.*;

import javax.annotation.*;
import java.util.*;

public class CopperCupItem extends Item implements DispensibleContainerItem {

   private final Fluid content;

   public CopperCupItem(Fluid pContent, Item.Properties pProperties) {
      super(pProperties);
      this.content = pContent;
   }

   @Override
   public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
      ItemStack itemstack = player.getItemInHand(hand);
      BlockHitResult blockhitresult = getPlayerPOVHitResult(
              level, player, this.content == Fluids.EMPTY ? ClipContext.Fluid.SOURCE_ONLY : ClipContext.Fluid.NONE
      );
      if (blockhitresult.getType() == HitResult.Type.MISS) {
         return InteractionResultHolder.pass(itemstack);
      } else if (blockhitresult.getType() != HitResult.Type.BLOCK) {
         return InteractionResultHolder.pass(itemstack);
      } else {
         BlockPos blockpos = blockhitresult.getBlockPos();
         Direction direction = blockhitresult.getDirection();
         BlockPos offsetPos = blockpos.relative(direction);
         if (!level.mayInteract(player, blockpos) || !player.mayUseItemAt(offsetPos, direction, itemstack)) {
            return InteractionResultHolder.fail(itemstack);
         } else if (this.content == Fluids.EMPTY) {
            BlockState fluidState = level.getBlockState(blockpos);
            if (fluidState.getBlock() instanceof BucketPickup bucketpickup) {
               ItemStack bucketStack = bucketpickup.pickupBlock(player, level, blockpos, fluidState);
               Optional<ItemStack> data = CupConversionDataMap.getCupVariant(bucketStack);
               if (data.isEmpty()) {
                  return InteractionResultHolder.pass(itemstack);
               }
               ItemStack cupStack = data.get();
               cupStack.applyComponents(bucketStack.getComponents());
               if (!cupStack.isEmpty()) {
                  player.awardStat(Stats.ITEM_USED.get(this));
                  bucketpickup.getPickupSound(fluidState).ifPresent(p_150709_ -> player.playSound(p_150709_, 1.0F, 1.0F));
                  level.gameEvent(player, GameEvent.FLUID_PICKUP, blockpos);
                  ItemStack itemstack2 = ItemUtils.createFilledResult(itemstack, player, cupStack);
                  if (!level.isClientSide) {
                     CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) player, cupStack);
                  }

                  return InteractionResultHolder.sidedSuccess(itemstack2, level.isClientSide());
               }
            }

            return InteractionResultHolder.fail(itemstack);
         } else {
            BlockState blockstate = level.getBlockState(blockpos);
            BlockPos blockpos2 = canBlockContainFluid(player, level, blockpos, blockstate) ? blockpos : offsetPos;
            if (this.emptyContents(player, level, blockpos2, blockhitresult, itemstack)) {
               this.checkExtraContent(player, level, itemstack, blockpos2);
               if (player instanceof ServerPlayer) {
                  CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer) player, blockpos2, itemstack);
               }

               player.awardStat(Stats.ITEM_USED.get(this));
               ItemStack itemstack1 = ItemUtils.createFilledResult(itemstack, player, getEmptySuccessItem(itemstack, player));
               return InteractionResultHolder.sidedSuccess(itemstack1, level.isClientSide());
            } else {
               return InteractionResultHolder.fail(itemstack);
            }
         }
      }
   }

   public static ItemStack getEmptySuccessItem(ItemStack bucketStack, Player player) {
      return !player.hasInfiniteMaterials() ? new ItemStack(MDItems.COPPER_CUP) : bucketStack;
   }

   @Override
   public void checkExtraContent(@Nullable Player player, Level level, ItemStack containerStack, BlockPos pos) {
   }

   @Override
   @Deprecated
   public boolean emptyContents(@Nullable Player player, Level level, BlockPos pos, @Nullable BlockHitResult result) {
      return this.emptyContents(player, level, pos, result, null);
   }

   public boolean emptyContents(@Nullable Player player, Level level, BlockPos pos, @Nullable BlockHitResult result, @Nullable ItemStack container) {
      if (!(this.content instanceof FlowingFluid flowingfluid)) {
         return false;
      } else {
         Block $$7;
         boolean $$8;
         BlockState blockstate;
         boolean flag2;
         label82:
         {
            blockstate = level.getBlockState(pos);
            $$7 = blockstate.getBlock();
            $$8 = blockstate.canBeReplaced(this.content);
            label70:
            if (!blockstate.isAir() && !$$8) {
               if ($$7 instanceof LiquidBlockContainer liquidblockcontainer
                       && liquidblockcontainer.canPlaceLiquid(player, level, pos, blockstate, this.content)) {
                  break label70;
               }

               flag2 = false;
               break label82;
            }

            flag2 = true;
         }

         boolean flag1 = flag2;
         java.util.Optional<net.neoforged.neoforge.fluids.FluidStack> containedFluidStack = java.util.Optional.ofNullable(container).flatMap(net.neoforged.neoforge.fluids.FluidUtil::getFluidContained);
         if (!flag1) {
            return result != null && this.emptyContents(player, level, result.getBlockPos().relative(result.getDirection()), null, container);
         } else if (containedFluidStack.isPresent() && this.content.getFluidType().isVaporizedOnPlacement(level, pos, containedFluidStack.get())) {
            this.content.getFluidType().onVaporize(player, level, pos, containedFluidStack.get());
            return true;
         } else if (level.dimensionType().ultraWarm() && this.content.is(FluidTags.WATER)) {
            int l = pos.getX();
            int i = pos.getY();
            int j = pos.getZ();
            level.playSound(
                    player,
                    pos,
                    SoundEvents.FIRE_EXTINGUISH,
                    SoundSource.BLOCKS,
                    0.5F,
                    2.6F + (level.random.nextFloat() - level.random.nextFloat()) * 0.8F
            );

            for (int k = 0; k < 8; k++) {
               level.addParticle(
                       ParticleTypes.LARGE_SMOKE, (double) l + Math.random(), (double) i + Math.random(), (double) j + Math.random(), 0.0, 0.0, 0.0
               );
            }

            return true;
         } else {
            if ($$7 instanceof LiquidBlockContainer liquidblockcontainer1 && liquidblockcontainer1.canPlaceLiquid(player, level, pos, blockstate, content)) {
               liquidblockcontainer1.placeLiquid(level, pos, blockstate, flowingfluid.getSource(false));
               this.playEmptySound(player, level, pos);
               return true;
            }

            if (!level.isClientSide && $$8 && !blockstate.liquid()) {
               level.destroyBlock(pos, true);
            }

            if (!level.setBlock(pos, this.content.defaultFluidState().createLegacyBlock(), 11) && !blockstate.getFluidState().isSource()) {
               return false;
            } else {
               this.playEmptySound(player, level, pos);
               return true;
            }
         }
      }
   }

   protected void playEmptySound(@Nullable Player player, LevelAccessor level, BlockPos pos) {
      SoundEvent soundevent = this.content.getFluidType().getSound(player, level, pos, net.neoforged.neoforge.common.SoundActions.BUCKET_EMPTY);
      if (soundevent == null)
         soundevent = this.content.is(FluidTags.LAVA) ? SoundEvents.BUCKET_EMPTY_LAVA : SoundEvents.BUCKET_EMPTY;
      level.playSound(player, pos, soundevent, SoundSource.BLOCKS, 1.0F, 1.0F);
      level.gameEvent(player, GameEvent.FLUID_PLACE, pos);
   }

   protected boolean canBlockContainFluid(@Nullable Player player, Level worldIn, BlockPos posIn, BlockState blockstate) {
      return blockstate.getBlock() instanceof LiquidBlockContainer && ((LiquidBlockContainer) blockstate.getBlock()).canPlaceLiquid(player, worldIn, posIn, blockstate, this.content);
   }
}