package com.sammy.minersdelight.content.block;

import com.sammy.minersdelight.setup.MDItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import vectorwing.farmersdelight.common.block.FeastBlock;

import static vectorwing.farmersdelight.common.BlockShapes.TRAY_SHAPE;

public class FakeMeatloafFeastBlock extends FeastBlock {

    protected static final VoxelShape FEAST_SHAPE = Shapes.joinUnoptimized(TRAY_SHAPE, Block.box(5, 2, 5, 11, 5, 11), BooleanOp.OR);

    public FakeMeatloafFeastBlock(Properties properties) {
        super(properties, MDItems.PLATE_OF_FAKE_MEATLOAF::get, true);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        int servings = state.getValue(SERVINGS);
        return (servings == 0) ? TRAY_SHAPE : FEAST_SHAPE;
    }
}
