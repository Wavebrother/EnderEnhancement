package wavebrother.enderEnhancement.common.blocks;

import java.util.List;

import org.apache.logging.log4j.LogManager;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameters;
import wavebrother.enderEnhancement.EnderEnhancement;
import wavebrother.enderEnhancement.common.item.EndermanAgitator;
import wavebrother.enderEnhancement.common.item.ItemAccumulator;
import wavebrother.enderEnhancement.common.tiles.EnderPedestalTileEntity;

public class EnderPedestal extends /* Container */Block {
	public static final BooleanProperty HAS_AGITATOR = BooleanProperty.create("has_agitator");
	public static final BooleanProperty HAS_ACCUMULATOR = BooleanProperty.create("has_accumulator");
	protected static final VoxelShape SHAPE = VoxelShapes.or(
			Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 2.0D, 15.0D),
			Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 14.0D, 11.0D));

	public final EnderPedestalItem blockItem;

	public EnderPedestal(String name) {
		super(Block.Properties.from(Blocks.STONE));
		setRegistryName(name);
		this.setDefaultState(this.stateContainer.getBaseState().with(HAS_AGITATOR, Boolean.valueOf(false))
				.with(HAS_ACCUMULATOR, Boolean.valueOf(false)));
		blockItem = new EnderPedestalItem();
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return createNewTileEntity(world);
	}

	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn,
			BlockRayTraceResult hit) {
		if ((state.get(HAS_AGITATOR) || state.get(HAS_ACCUMULATOR)) && player.isSneaking()) {
			if (!worldIn.isRemote)
				extract(worldIn, player, pos);
			state = state.with(HAS_AGITATOR, Boolean.valueOf(false));
			state = state.with(HAS_ACCUMULATOR, Boolean.valueOf(false));
			worldIn.setBlockState(pos, state, 2);
			return true;
		} else if (player.getHeldItem(handIn).getItem() instanceof EndermanAgitator
				|| player.getHeldItem(handIn).getItem() instanceof ItemAccumulator) {
			return false;
		} else if (!worldIn.isRemote) {
			extractInventory(worldIn, player, pos);
			return true;
		} else
			return true;
	}

	public static void insertItem(IWorld worldIn, PlayerEntity player, BlockPos pos, BlockState state,
			ItemStack itemStack) {
		TileEntity tileentity = worldIn.getTileEntity(pos);
		if (tileentity instanceof EnderPedestalTileEntity) {
			((EnderPedestalTileEntity) tileentity).setPedestalItem(itemStack.copy(), false);
			((EnderPedestalTileEntity) tileentity).setItemOwner(player);
			if (itemStack.getItem() instanceof EndermanAgitator)
				worldIn.setBlockState(pos, state.with(HAS_AGITATOR, Boolean.valueOf(true)), 2);
			else if (itemStack.getItem() instanceof ItemAccumulator)
				worldIn.setBlockState(pos, state.with(HAS_ACCUMULATOR, Boolean.valueOf(true)), 2);
		}
	}

	private static void extractItem(World worldIn, PlayerEntity player, BlockPos pos, ItemStack itemstack) {
		if (player != null && !player.addItemStackToInventory(itemstack)) {
			worldIn.playEvent(1010, pos, 0);
			float f = 0.7F;
			double d0 = (double) (worldIn.rand.nextFloat() * f) + (double) 0.15F;
			double d1 = (double) (worldIn.rand.nextFloat() * f) + (double) 0.060000002F + 0.6D;
			double d2 = (double) (worldIn.rand.nextFloat() * f) + (double) 0.15F;
			ItemStack itemstack1 = itemstack.copy();
			ItemEntity itementity = new ItemEntity(worldIn, (double) pos.getX() + d0, (double) pos.getY() + d1,
					(double) pos.getZ() + d2, itemstack1);
			itementity.setDefaultPickupDelay();
			worldIn.addEntity(itementity);
		}
	}

	private static void extractInventory(World worldIn, PlayerEntity player, BlockPos pos) {
		TileEntity tileentity = worldIn.getTileEntity(pos);
		if (tileentity instanceof EnderPedestalTileEntity) {
			EnderPedestalTileEntity enderPedestalTileEntity = (EnderPedestalTileEntity) tileentity;
			for (int i = 0; i < enderPedestalTileEntity.getSizeInventory(); i++) {
				if (!enderPedestalTileEntity.getStackInSlot(i).isEmpty()) {
					ItemStack item = enderPedestalTileEntity.removeStackFromSlot(i);
					extractItem(worldIn, player, pos, item);
				}
			}
		}
	}

	private static void extract(World worldIn, PlayerEntity player, BlockPos pos) {
		TileEntity tileentity = worldIn.getTileEntity(pos);
		if (tileentity instanceof EnderPedestalTileEntity) {
			EnderPedestalTileEntity enderPedestalTileEntity = (EnderPedestalTileEntity) tileentity;
			ItemStack itemstack = enderPedestalTileEntity.getPedestalItem();
			if (!itemstack.isEmpty()) {
				enderPedestalTileEntity.clearPedestal();
				extractItem(worldIn, player, pos, itemstack);
			}
			for (int i = 0; i < enderPedestalTileEntity.getSizeInventory(); i++) {
				if (!enderPedestalTileEntity.getStackInSlot(i).isEmpty()) {
					ItemStack item = enderPedestalTileEntity.removeStackFromSlot(i);
					extractItem(worldIn, player, pos, item);
				}
			}
		}
		extractInventory(worldIn, player, pos);
	}

	@SuppressWarnings("deprecation")
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			extract(worldIn, null, pos);
			super.onReplaced(state, worldIn, pos, newState, isMoving);
		}
	}

	@Override
	public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
		@SuppressWarnings("deprecation")
		List<ItemStack> drops = super.getDrops(state, builder);
		if (builder.get(LootParameters.BLOCK_ENTITY) instanceof EnderPedestalTileEntity) {
			EnderPedestalTileEntity te = (EnderPedestalTileEntity) builder.get(LootParameters.BLOCK_ENTITY);
			if (te.getPedestalItem() != ItemStack.EMPTY)
				drops.add(te.getPedestalItem());
			for (int i = 0; i < te.getSizeInventory(); i++) {
				if (te.getStackInSlot(i) != null && te.getStackInSlot(i) != ItemStack.EMPTY)
					drops.add(te.removeStackFromSlot(i));
			}
		}
		return drops;
	}

	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		EnderPedestalTileEntity pedestal = new EnderPedestalTileEntity();
		LogManager.getLogger().debug(worldIn.getClass());
		// pedestal.init((World) worldIn);
		return pedestal;
	}

	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(HAS_AGITATOR);
		builder.add(HAS_ACCUMULATOR);
	}

	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	public class EnderPedestalItem extends BlockItem {

		protected EnderPedestalItem() {
			super(EnderPedestal.this, new Item.Properties().group(EnderEnhancement.CREATIVE_TAB));
			setRegistryName(EnderPedestal.this.getRegistryName());
		}
	}
}
