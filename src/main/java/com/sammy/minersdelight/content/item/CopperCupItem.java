package com.sammy.minersdelight.content.item;

import com.sammy.minersdelight.setup.*;
import net.minecraft.advancements.*;
import net.minecraft.core.*;
import net.minecraft.core.particles.*;
import net.minecraft.server.level.*;
import net.minecraft.sounds.*;
import net.minecraft.stats.*;
import net.minecraft.tags.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.gameevent.*;
import net.minecraft.world.level.material.*;
import net.minecraft.world.phys.*;
import org.jetbrains.annotations.*;

import java.util.function.*;

public class CopperCupItem extends Item implements DispensibleContainerItem {

   public static final Function<Item, ItemStack> BUCKET_TO_CUP = (item) -> {
      if (Items.WATER_BUCKET.equals(item)) {
         return MDItems.WATER_CUP.asStack();
      } else if (Items.MILK_BUCKET.equals(item)) {
         return MDItems.MILK_CUP.asStack();
      } else if (Items.POWDER_SNOW_BUCKET.equals(item)) {
         return MDItems.POWDERED_SNOW_CUP.asStack();
      } else if (Items.BUCKET.equals(item)) {
         return MDItems.COPPER_CUP.asStack();
      }
      return ItemStack.EMPTY;
   };

   private final Fluid content;

   public CopperCupItem(Fluid pContent, Item.Properties pProperties) {
      super(pProperties);
      this.content = pContent;
   }

   @Override
   public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand) {
      if (content.equals(Fluids.EMPTY)) {
         pPlayer.setItemInHand(pUsedHand, Items.BUCKET.getDefaultInstance());
         pInteractionTarget.interact(pPlayer, pUsedHand);
         Item maybeFilledBucket = pPlayer.getItemInHand(pUsedHand).getItem();
         ItemStack filledResult = ItemUtils.createFilledResult(pStack, pPlayer, BUCKET_TO_CUP.apply(maybeFilledBucket));
         pPlayer.setItemInHand(pUsedHand, filledResult);
      }
      return super.interactLivingEntity(pStack, pPlayer, pInteractionTarget, pUsedHand);
   }

   public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
      ItemStack itemstack = pPlayer.getItemInHand(pHand);
      BlockHitResult blockhitresult = getPlayerPOVHitResult(pLevel, pPlayer, this.content == Fluids.EMPTY ? ClipContext.Fluid.SOURCE_ONLY : ClipContext.Fluid.NONE);
      InteractionResultHolder<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onBucketUse(pPlayer, pLevel, itemstack, blockhitresult);
      if (ret != null) return ret;
      if (blockhitresult.getType() == HitResult.Type.MISS) {
         return InteractionResultHolder.pass(itemstack);
      } else if (blockhitresult.getType() != HitResult.Type.BLOCK) {
         return InteractionResultHolder.pass(itemstack);
      } else {
         BlockPos blockpos = blockhitresult.getBlockPos();
         Direction direction = blockhitresult.getDirection();
         BlockPos blockpos1 = blockpos.relative(direction);
         if (pLevel.mayInteract(pPlayer, blockpos) && pPlayer.mayUseItemAt(blockpos1, direction, itemstack)) {
            if (this.content == Fluids.EMPTY) {
               BlockState blockstate1 = pLevel.getBlockState(blockpos);
               if (blockstate1.getBlock() instanceof BucketPickup bucketpickup) {
                  ItemStack bucket = bucketpickup.pickupBlock(pLevel, blockpos, blockstate1);
                  if (!bucket.isEmpty()) {
                     ItemStack cup = BUCKET_TO_CUP.apply(bucket.getItem());
                     if (!cup.isEmpty()) {
                        pPlayer.awardStat(Stats.ITEM_USED.get(this));
                        bucketpickup.getPickupSound(blockstate1).ifPresent((p_150709_) -> pPlayer.playSound(p_150709_, 1.0F, 1.0F));
                        pLevel.gameEvent(pPlayer, GameEvent.FLUID_PICKUP, blockpos);
                        ItemStack itemstack2 = ItemUtils.createFilledResult(itemstack, pPlayer, cup);
                        if (!pLevel.isClientSide) {
                           CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) pPlayer, cup);
                        }

                        return InteractionResultHolder.sidedSuccess(itemstack2, pLevel.isClientSide());
                     } else {
                        pLevel.setBlock(blockpos, blockstate1, 3);
                     }
                  }
               }

               return InteractionResultHolder.fail(itemstack);
            } else {
               BlockState blockstate = pLevel.getBlockState(blockpos);
               BlockPos blockpos2 = canBlockContainFluid(pLevel, blockpos, blockstate) ? blockpos : blockpos1;
               if (this.emptyContents(pPlayer, pLevel, blockpos2, blockhitresult)) {
                  this.checkExtraContent(pPlayer, pLevel, itemstack, blockpos2);
                  if (pPlayer instanceof ServerPlayer) {
                     CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer) pPlayer, blockpos2, itemstack);
                  }

                  pPlayer.awardStat(Stats.ITEM_USED.get(this));
                  return InteractionResultHolder.sidedSuccess(getEmptySuccessItem(itemstack, pPlayer), pLevel.isClientSide());
               } else {
                  return InteractionResultHolder.fail(itemstack);
               }
            }
         } else {
            return InteractionResultHolder.fail(itemstack);
         }
      }
   }

   public static ItemStack getEmptySuccessItem(ItemStack pBucketStack, Player pPlayer) {
      return !pPlayer.getAbilities().instabuild ? new ItemStack(MDItems.COPPER_CUP.get()) : pBucketStack;
   }

   public void checkExtraContent(@Nullable Player pPlayer, Level pLevel, ItemStack pContainerStack, BlockPos pPos) {
   }

   public boolean emptyContents(@Nullable Player pPlayer, Level pLevel, BlockPos pPos, @Nullable BlockHitResult pResult) {
      if (!(this.content instanceof FlowingFluid)) {
         return false;
      } else {
         BlockState blockstate = pLevel.getBlockState(pPos);
         Block block = blockstate.getBlock();
         Material material = blockstate.getMaterial();
         boolean flag = blockstate.canBeReplaced(this.content);
         boolean flag1 = blockstate.isAir() || flag || block instanceof LiquidBlockContainer && ((LiquidBlockContainer) block).canPlaceLiquid(pLevel, pPos, blockstate, this.content);
         if (!flag1) {
            return pResult != null && this.emptyContents(pPlayer, pLevel, pResult.getBlockPos().relative(pResult.getDirection()), null);
         } else if (pLevel.dimensionType().ultraWarm() && this.content.is(FluidTags.WATER)) {
            int i = pPos.getX();
            int j = pPos.getY();
            int k = pPos.getZ();
            pLevel.playSound(pPlayer, pPos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.5F, 2.6F + (pLevel.random.nextFloat() - pLevel.random.nextFloat()) * 0.8F);

            for (int l = 0; l < 8; ++l) {
               pLevel.addParticle(ParticleTypes.LARGE_SMOKE, (double) i + Math.random(), (double) j + Math.random(), (double) k + Math.random(), 0.0D, 0.0D, 0.0D);
            }

            return true;
         } else if (block instanceof LiquidBlockContainer && ((LiquidBlockContainer) block).canPlaceLiquid(pLevel, pPos, blockstate, content)) {
            ((LiquidBlockContainer) block).placeLiquid(pLevel, pPos, blockstate, ((FlowingFluid) this.content).getSource(false));
            this.playEmptySound(pPlayer, pLevel, pPos);
            return true;
         } else {
            if (!pLevel.isClientSide && flag && !material.isLiquid()) {
               pLevel.destroyBlock(pPos, true);
            }

            if (!pLevel.setBlock(pPos, this.content.defaultFluidState().createLegacyBlock(), 11) && !blockstate.getFluidState().isSource()) {
               return false;
            } else {
               this.playEmptySound(pPlayer, pLevel, pPos);
               return true;
            }
         }
      }
   }

   protected void playEmptySound(@Nullable Player pPlayer, LevelAccessor pLevel, BlockPos pPos) {
      SoundEvent soundevent = this.content.getFluidType().getSound(pPlayer, pLevel, pPos, net.minecraftforge.common.SoundActions.BUCKET_EMPTY);
      if (soundevent == null) {
         soundevent = this.content.is(FluidTags.LAVA) ? SoundEvents.BUCKET_EMPTY_LAVA : SoundEvents.BUCKET_EMPTY;
      }
      pLevel.playSound(pPlayer, pPos, soundevent, SoundSource.BLOCKS, 1.0F, 1.0F);
      pLevel.gameEvent(pPlayer, GameEvent.FLUID_PLACE, pPos);
   }

   @Override
   public net.minecraftforge.common.capabilities.ICapabilityProvider initCapabilities(ItemStack stack, @Nullable net.minecraft.nbt.CompoundTag nbt) {
      if (this.getClass() == CopperCupItem.class)
         return new net.minecraftforge.fluids.capability.wrappers.FluidBucketWrapper(stack);
      else
         return super.initCapabilities(stack, nbt);
   }


   private boolean canBlockContainFluid(Level worldIn, BlockPos posIn, BlockState blockstate) {
      return blockstate.getBlock() instanceof LiquidBlockContainer && ((LiquidBlockContainer) blockstate.getBlock()).canPlaceLiquid(worldIn, posIn, blockstate, this.content);
   }
}