package wavebrother.enderEnhancement.common.blocks;
//Made with Blockbench

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ContainerBlock;
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
import wavebrother.enderEnhancement.EnderEnhancement;
import wavebrother.enderEnhancement.common.tiles.EnderPedestalTileEntity;

public class EnderPedestal extends ContainerBlock {
	public static final BooleanProperty HAS_AGITATOR = BooleanProperty.create("has_agitator");
	protected static final VoxelShape SHAPE = VoxelShapes
			.or(Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 14.0D, 12.0D));

	public final EnderPedestalItem blockItem;

	public EnderPedestal(String name) {
		super(Block.Properties.from(Blocks.STONE));
		setRegistryName(name);
		this.setDefaultState(this.stateContainer.getBaseState().with(HAS_AGITATOR, Boolean.valueOf(false)));
		blockItem = new EnderPedestalItem();
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new EnderPedestalTileEntity();
	}

	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn,
			BlockRayTraceResult hit) {
		if (state.get(HAS_AGITATOR)) {
			this.extractAgitator(worldIn, player, pos);
			state = state.with(HAS_AGITATOR, Boolean.valueOf(false));
			worldIn.setBlockState(pos, state, 2);
			return true;
		} else {
			return false;
		}
	}

	public void insertAgitator(IWorld worldIn, BlockPos pos, BlockState state, ItemStack recordStack) {
		TileEntity tileentity = worldIn.getTileEntity(pos);
		if (tileentity instanceof EnderPedestalTileEntity) {
			((EnderPedestalTileEntity) tileentity).setAgitator(recordStack.copy());
			worldIn.setBlockState(pos, state.with(HAS_AGITATOR, Boolean.valueOf(true)), 2);
		}
	}

	private void extractAgitator(World worldIn, PlayerEntity player, BlockPos pos) {
		if (!worldIn.isRemote) {
			TileEntity tileentity = worldIn.getTileEntity(pos);
			if (tileentity instanceof EnderPedestalTileEntity) {
				EnderPedestalTileEntity enderPedestalTileEntity = (EnderPedestalTileEntity) tileentity;
				ItemStack itemstack = enderPedestalTileEntity.getAgitator();
				if (!itemstack.isEmpty()) {
					enderPedestalTileEntity.clear();
					if (player != null && !player.addItemStackToInventory(itemstack)) {
						worldIn.playEvent(1010, pos, 0);
						float f = 0.7F;
						double d0 = (double) (worldIn.rand.nextFloat() * 0.7F) + (double) 0.15F;
						double d1 = (double) (worldIn.rand.nextFloat() * 0.7F) + (double) 0.060000002F + 0.6D;
						double d2 = (double) (worldIn.rand.nextFloat() * 0.7F) + (double) 0.15F;
						ItemStack itemstack1 = itemstack.copy();
						ItemEntity itementity = new ItemEntity(worldIn, (double) pos.getX() + d0,
								(double) pos.getY() + d1, (double) pos.getZ() + d2, itemstack1);
						itementity.setDefaultPickupDelay();
						worldIn.addEntity(itementity);
					}
				}
			}
		}
	}

	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			this.extractAgitator(worldIn, null, pos);
			super.onReplaced(state, worldIn, pos, newState, isMoving);
		}
	}

	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return new EnderPedestalTileEntity();
	}

	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(HAS_AGITATOR);
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
			// TODO Auto-generated constructor stub
		}

	}

}
