package com.sammy.minersdelight.content.block;

import com.sammy.minersdelight.setup.*;
import net.minecraft.core.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.phys.shapes.*;
import vectorwing.farmersdelight.common.block.*;

import static vectorwing.farmersdelight.common.BlockShapes.TRAY_SHAPE;

public class StuffedSquidFeastBlock extends FeastBlock {

    public static final IntegerProperty SERVINGS = IntegerProperty.create("servings", 0, 5);
    protected static final VoxelShape[] FEAST_SHAPE = new VoxelShape[]{
            Block.box(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D),
            Block.box(2, 1, 2, 14, 6, 14),
            Block.box(2, 1, 2, 14, 7, 14),
            Block.box(2, 1.2, 2, 14, 8.2, 14),
            Block.box(2, 1.2, 2, 14, 11.2, 14),
            Block.box(2, 1.2, 2, 14, 12.2, 14)
    };

    public StuffedSquidFeastBlock(Properties properties) {
        super(properties, MDItems.BOWL_OF_STUFFED_SQUID::get, true);
    }

    @Override
    public int getMaxServings() {
        return 5;
    }

    @Override
    public IntegerProperty getServingsProperty() {
        return SERVINGS;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        int servings = state.getValue(SERVINGS);
        return Shapes.or(TRAY_SHAPE, FEAST_SHAPE[servings]);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, SERVINGS);
    }
}