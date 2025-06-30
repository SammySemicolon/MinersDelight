package com.sammy.minersdelight.content.block.sticky_basket;

import com.sammy.minersdelight.setup.*;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import vectorwing.farmersdelight.common.block.*;
import vectorwing.farmersdelight.common.block.entity.*;

import java.util.function.*;

public class StickyBasketBlockEntity extends RandomizableContainerBlockEntity implements Basket
{
    private NonNullList<ItemStack> items = NonNullList.withSize(27, ItemStack.EMPTY);
    private int transferCooldown = -1;

    public StickyBasketBlockEntity(BlockPos pos, BlockState state) {
        super(MDBlockEntities.STICKY_BASKET.get(), pos, state);
    }
    
    @Override
    protected void loadAdditional(CompoundTag compound, HolderLookup.Provider registries) {
        super.loadAdditional(compound, registries);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(compound)) {
            ContainerHelper.loadAllItems(compound, this.items, registries);
        }
        this.transferCooldown = compound.getInt("TransferCooldown");
    }

    @Override
    public void saveAdditional(CompoundTag compound, HolderLookup.Provider registries) {
        super.saveAdditional(compound, registries);
        if (!this.trySaveLootTable(compound)) {
            ContainerHelper.saveAllItems(compound, this.items, registries);
        }

        compound.putInt("TransferCooldown", this.transferCooldown);
    }

    @Override
    public int getContainerSize() {
        return this.items.size();
    }

    @Override
    public ItemStack removeItem(int index, int count) {
        this.unpackLootTable(null);
        return ContainerHelper.removeItem(this.getItems(), index, count);
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        this.unpackLootTable(null);
        this.getItems().set(index, stack);
        if (stack.getCount() > this.getMaxStackSize()) {
            stack.setCount(this.getMaxStackSize());
        }
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("minersdelight.container.sticky_basket");
    }

    // -- STANDARD INVENTORY STUFF --
    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> itemsIn) {
        this.items = itemsIn;
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory player) {
        return ChestMenu.threeRows(id, player, this);
    }

    @Override
    public void setCooldown(int ticks) {
        this.transferCooldown = ticks;
    }

    public boolean isOnCooldown() {
        return this.transferCooldown > 0;
    }

    @Override
    public boolean isOnCustomCooldown() {
        return this.transferCooldown > 8;
    }

    @Override
    public void tryTransfer(BooleanSupplier transfer) {
        if (this.level != null && !this.level.isClientSide) {
            if (!this.isOnCooldown() && this.getBlockState().getValue(BlockStateProperties.ENABLED)) {
                boolean flag = false;
                if (!this.isFull()) {
                    flag = transfer.getAsBoolean();
                }

                if (flag) {
                    this.setCooldown(8);
                    this.setChanged();
                }
            }
        }
    }

    protected boolean isFull() {
        for (ItemStack itemstack : this.items) {
            if (itemstack.isEmpty() || itemstack.getCount() != itemstack.getMaxStackSize()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public double getLevelX() {
        return (double) this.worldPosition.getX() + 0.5D;
    }

    @Override
    public double getLevelY() {
        return (double) this.worldPosition.getY() + 0.5D;
    }

    @Override
    public double getLevelZ() {
        return (double) this.worldPosition.getZ() + 0.5D;
    }

    public static void pushItemsTick(Level level, BlockPos pos, BlockState state, StickyBasketBlockEntity blockEntity) {
        --blockEntity.transferCooldown;
        if (!blockEntity.isOnCooldown()) {
            blockEntity.setCooldown(0);
            int facing = state.getValue(BasketBlock.FACING).get3DDataValue();
            blockEntity.tryTransfer(() -> blockEntity.collectItems(level, facing));
        }
    }
}