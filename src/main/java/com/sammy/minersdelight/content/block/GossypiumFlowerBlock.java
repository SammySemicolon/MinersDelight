package com.sammy.minersdelight.content.block;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import vectorwing.farmersdelight.common.block.WildCropBlock;

public class GossypiumFlowerBlock extends FlowerBlock {
    public GossypiumFlowerBlock(Properties properties) {
        super(MobEffects.DIG_SPEED, 10, properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(BlockTags.DIRT) || state.is(BlockTags.SAND);
    }
}
