package com.sammy.minersdelight.data;

import com.sammy.minersdelight.content.item.CopperCupItem;
import com.sammy.minersdelight.content.item.MilkCupItem;
import com.sammy.minersdelight.setup.MDItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.wrappers.FluidBucketWrapper;
import org.jetbrains.annotations.NotNull;

public class CopperCupContainer extends FluidBucketWrapper {

    public CopperCupContainer(@NotNull ItemStack container) {
        super(container);
    }

    @Override
    public boolean canFillFluidType(FluidStack fluid) {
        if (fluid.getFluid() == Fluids.WATER) {
            return true;
        }
        Item bucketWithFluid = fluid.getFluid().getFluidType().getBucket(fluid).getItem();
        return !CopperCupItem.BUCKET_TO_CUP.apply(bucketWithFluid).isEmpty();
    }

    @Override
    public @NotNull FluidStack getFluid() {
        Item item = container.getItem();
        if (item instanceof CopperCupItem copperCup) {
            return new FluidStack(copperCup.getFluid(), FluidType.BUCKET_VOLUME);
        }
        if (item instanceof MilkCupItem && ForgeMod.MILK.isPresent()) {
            return new FluidStack(ForgeMod.MILK.get(), FluidType.BUCKET_VOLUME);
        }
        else {
            return FluidStack.EMPTY;
        }
    }

    @Override
    protected void setFluid(@NotNull FluidStack fluidStack) {
        if (fluidStack.isEmpty()) {
            container = new ItemStack(MDItems.COPPER_CUP.get());
        }
        else {
            container = CopperCupItem.BUCKET_TO_CUP.apply(FluidUtil.getFilledBucket(fluidStack).getItem());
        }
    }

    @Override
    public int fill(FluidStack resource, FluidAction action) {
        if (container.getCount() != 1 || resource.getAmount() < FluidType.BUCKET_VOLUME || container.getItem() instanceof MilkCupItem || !getFluid().isEmpty() || !canFillFluidType(resource)) {
            return 0;
        }
        if (action.execute()) {
            setFluid(resource);
        }
        return FluidType.BUCKET_VOLUME;
    }
}
