package wavebrother.enderEnhancement.common.blocks;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootContext.Builder;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraftforge.fml.network.NetworkHooks;
import wavebrother.enderEnhancement.common.blocks.EnderBlock.EnderBlockItem;
import wavebrother.enderEnhancement.common.tiles.ChargingStationTileEntity;

public class ChargingStationBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    public ChargingStationBlock(Properties builder) {
        super(builder);
        setDefaultState(getStateContainer().getBaseState().with(FACING, Direction.NORTH).with(LIT, Boolean.FALSE));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(FACING);
        builder.add(LIT);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, Builder builder) {
        TileEntity te = builder.get(LootParameters.BLOCK_ENTITY);
        List<ItemStack> drops = super.getDrops(state, builder);
        if (te instanceof ChargingStationTileEntity) {
            ChargingStationTileEntity tileEntity = (ChargingStationTileEntity) te;
            List<ItemStack> res = new ArrayList<>(drops.size());
            for (ItemStack stack : drops) {
                if (stack.getItem() instanceof EnderBlockItem)
                    tileEntity.onStoreEnergy(stack);
                res.add(stack);
            }
            return res;
        }
        return drops;
    }


    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (newState.getBlock() != this) {
//            GadgetUtils.dropTileEntityInventory(worldIn, pos);
            super.onReplaced(state, worldIn, pos, newState, isMoving);
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
    	return null;
//        return OurBlocks.OurTileEntities.CHARGING_STATION_TYPE.create();
    }

    @Override
    @Deprecated
    public boolean isSolid(BlockState state) {
        return false;
    }

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult blockRayTraceResult) {
        // Only execute on the server
        if (worldIn.isRemote)
            return true;

        TileEntity te = worldIn.getTileEntity(pos);
        if (! (te instanceof ChargingStationTileEntity))
            return false;
        NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) te, te.getPos());
        return true;
    }

    @Override
    public int getLightValue(BlockState state) {
        return state.get(LIT) ? 14 : 0;
    }
}