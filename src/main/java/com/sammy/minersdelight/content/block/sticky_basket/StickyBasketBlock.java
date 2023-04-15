package com.sammy.minersdelight.content.block.sticky_basket;

import com.google.common.collect.*;
import com.sammy.minersdelight.setup.*;
import net.minecraft.core.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.*;
import net.minecraft.world.level.pathfinder.*;
import net.minecraft.world.phys.*;
import net.minecraft.world.phys.shapes.*;

import javax.annotation.*;

public class StickyBasketBlock extends BaseEntityBlock implements SimpleWaterloggedBlock
{
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final BooleanProperty ENABLED = BlockStateProperties.ENABLED;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public static final VoxelShape OUT_SHAPE = Shapes.block();
    public static final VoxelShape RENDER_SHAPE = box(1.0D, 1.0D, 1.0D, 15.0D, 15.0D, 15.0D);

    public static final ImmutableMap<Direction, VoxelShape> COLLISION_SHAPE_FACING =
            Maps.immutableEnumMap(ImmutableMap.<Direction, VoxelShape>builder()
                    .put(Direction.DOWN, makeHollowCubeShape(box(2.0D, 0.0D, 2.0D, 14.0D, 14.0D, 14.0D)))
                    .put(Direction.UP, makeHollowCubeShape(box(2.0D, 2.0D, 2.0D, 14.0D, 16.0D, 14.0D)))
                    .put(Direction.NORTH, makeHollowCubeShape(box(2.0D, 2.0D, 0.0D, 14.0D, 14.0D, 14.0D)))
                    .put(Direction.SOUTH, makeHollowCubeShape(box(2.0D, 2.0D, 2.0D, 14.0D, 14.0D, 16.0D)))
                    .put(Direction.WEST, makeHollowCubeShape(box(0.0D, 2.0D, 2.0D, 14.0D, 14.0D, 14.0D)))
                    .put(Direction.EAST, makeHollowCubeShape(box(2.0D, 2.0D, 2.0D, 16.0D, 14.0D, 14.0D)))
                    .build());

    private static VoxelShape makeHollowCubeShape(VoxelShape cutout) {
        return Shapes.joinUnoptimized(OUT_SHAPE, cutout, BooleanOp.ONLY_FIRST).optimize();
    }

    public StickyBasketBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.UP).setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return COLLISION_SHAPE_FACING.get(state.getValue(FACING));
    }

    @Override
    public VoxelShape getOcclusionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return RENDER_SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, ENABLED, WATERLOGGED);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        return InteractionResult.PASS;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity tileEntity = level.getBlockEntity(pos);
            if (tileEntity instanceof Container) {
                Containers.dropContents(level, pos, (Container) tileEntity);
                level.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if (pEntity instanceof LivingEntity livingEntity) {
            Vec3 motion = livingEntity.getDeltaMovement();
            if (livingEntity instanceof Player player && player.isCrouching()) {
                return;
            }
            pEntity.setDeltaMovement(new Vec3(motion.x, -0.05f, motion.z));
        }
    }

    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    // --- HOPPER STUFF ---

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        boolean isPowered = !level.hasNeighborSignal(pos);
        if (isPowered != state.getValue(ENABLED)) {
            level.setBlock(pos, state.setValue(ENABLED, isPowered), 4);
        }
    }

    // --- BARREL STUFF ---

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(level.getBlockEntity(pos));
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if (stack.hasCustomHoverName()) {
            BlockEntity tileEntity = level.getBlockEntity(pos);
            if (tileEntity instanceof StickyBasketBlockEntity) {
                ((StickyBasketBlockEntity) tileEntity).setCustomName(stack.getHoverName());
            }
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(FACING, context.getNearestLookingDirection().getOpposite()).setValue(WATERLOGGED, fluid.getType() == Fluids.WATER);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    @Override
    public VoxelShape getInteractionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return OUT_SHAPE;
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType type) {
        return false;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return MDBlockEntities.STICKY_BASKET.get().create(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return level.isClientSide ? null : createTickerHelper(blockEntityType, MDBlockEntities.STICKY_BASKET.get(), StickyBasketBlockEntity::pushItemsTick);
    }
}
