package com.sammy.minersdelight.content.block;

import net.minecraft.core.*;
import net.minecraft.tags.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;

public class GossypiumFlowerBlock extends FlowerBlock {
    public GossypiumFlowerBlock(Properties properties) {
        super(()->MobEffects.DIG_SPEED, 10, properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(BlockTags.DIRT) || state.is(BlockTags.SAND);
    }
}
