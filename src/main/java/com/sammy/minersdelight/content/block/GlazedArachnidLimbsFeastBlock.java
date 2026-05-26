package com.sammy.minersdelight.content.block;

import com.sammy.minersdelight.setup.MDItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import vectorwing.farmersdelight.common.block.FeastBlock;

import static vectorwing.farmersdelight.common.BlockShapes.TRAY_SHAPE;

public class GlazedArachnidLimbsFeastBlock extends FeastBlock {

    protected static final VoxelShape[] FEAST_SHAPE = new VoxelShape[]{
            Block.box(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D),
            Block.box(4.0, 2.0, 4.0, 12.0, 4.0, 12.0),
            Block.box(2.0, 2.0, 2.0, 14.0, 4.0, 14.0),
            Shapes.or(Block.box(2.0, 2.0, 2.0, 14.0, 6.0, 14.0), Block.box(6.0, 6.0, 6.0, 10.0, 11.0, 10.0)),
            Shapes.or(Block.box(2.0, 2.0, 2.0, 14.0, 7.0, 14.0), Block.box(6.0, 7.0, 6.0, 10.0, 13.0, 10.0))
    };

    public GlazedArachnidLimbsFeastBlock(Properties properties) {
        super(properties, MDItems.PLATE_OF_GLAZED_ARACHNID_LIMBS::get, true);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        int servings = state.getValue(SERVINGS);
        return Shapes.or(TRAY_SHAPE, FEAST_SHAPE[servings]);
    }
}
