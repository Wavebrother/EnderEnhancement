package wavebrother.enderEnhancement.common.blocks;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import wavebrother.enderEnhancement.EnderEnhancement;
import wavebrother.enderEnhancement.common.item.EndermanAgitator;
import wavebrother.enderEnhancement.common.item.ItemAccumulator;
import wavebrother.enderEnhancement.common.tiles.EnderPedestalTileEntity;

public class EnderPedestal extends /* Container */Block implements ITileEntityProvider {
	public static final PropertyBool HAS_AGITATOR = PropertyBool.create("has_agitator");
	public static final PropertyBool HAS_ACCUMULATOR = PropertyBool.create("has_accumulator");
//	protected static final VoxelShape SHAPE = VoxelShapes.or(
//			Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 2.0D, 15.0D),
//			Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 14.0D, 11.0D));
	protected static final AxisAlignedBB BASE_AABB = new AxisAlignedBB(0.0165D, 0, 0.0165, 0.9375D, 0.125D, 0.9375D);
	protected static final AxisAlignedBB STICK_AABB = new AxisAlignedBB(0.3125D, 0, 0.3125D, 0.6875D, 0.875D, 0.6875D);

	public final EnderPedestalItem blockItem;

	public EnderPedestal(String name) {
		super(Material.ROCK);
		setCreativeTab(EnderEnhancement.CREATIVE_TAB).setHardness(2.0F).setResistance(10.0F);
		setRegistryName(name);
		this.setDefaultState(this.blockState.getBaseState().withProperty(HAS_AGITATOR, Boolean.valueOf(false))
				.withProperty(HAS_ACCUMULATOR, Boolean.valueOf(false)));
		blockItem = new EnderPedestalItem();
		setUnlocalizedName(name);
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return createNewTileEntity(worldIn);
	}

	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand handIn, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if ((state.getValue(HAS_AGITATOR) || state.getValue(HAS_ACCUMULATOR)) && playerIn.isSneaking()) {
			if (!worldIn.isRemote)
				extract(worldIn, playerIn, pos);
			state = state.withProperty(HAS_AGITATOR, Boolean.valueOf(false));
			state = state.withProperty(HAS_ACCUMULATOR, Boolean.valueOf(false));
			worldIn.setBlockState(pos, state, 2);
			return true;
		} else if (playerIn.getHeldItem(handIn).getItem() instanceof EndermanAgitator
				|| playerIn.getHeldItem(handIn).getItem() instanceof ItemAccumulator) {
			return false;
		} else if (!worldIn.isRemote) {
			extractInventory(worldIn, playerIn, pos);
			return true;
		} else
			return true;
	}

	public static void insertItem(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state,
			ItemStack itemStack) {
		TileEntity tileentity = worldIn.getTileEntity(pos);
		if (tileentity instanceof EnderPedestalTileEntity) {
			if (itemStack.getItem() instanceof EndermanAgitator)
				worldIn.setBlockState(pos, state.withProperty(HAS_AGITATOR, Boolean.valueOf(true)), 2);
			else if (itemStack.getItem() instanceof ItemAccumulator)
				worldIn.setBlockState(pos, state.withProperty(HAS_ACCUMULATOR, Boolean.valueOf(true)), 2);
			((EnderPedestalTileEntity) worldIn.getTileEntity(pos)).setPedestalItem(itemStack.copy(), false);
			((EnderPedestalTileEntity) worldIn.getTileEntity(pos)).setItemOwner(player);
		}
	}

	private static void extractItem(World worldIn, EntityPlayer player, BlockPos pos, ItemStack itemstack) {
		if ((player != null && !player.addItemStackToInventory(itemstack)) || player == null) {
			worldIn.playEvent(1010, pos, 0);
			float f = 0.7F;
			double d0 = (double) (worldIn.rand.nextFloat() * f) + (double) 0.15F;
			double d1 = (double) (worldIn.rand.nextFloat() * f) + (double) 0.060000002F + 0.6D;
			double d2 = (double) (worldIn.rand.nextFloat() * f) + (double) 0.15F;
			ItemStack itemstack1 = itemstack.copy();
			EntityItem itementity = new EntityItem(worldIn, (double) pos.getX() + d0, (double) pos.getY() + d1,
					(double) pos.getZ() + d2, itemstack1);
			itementity.setDefaultPickupDelay();
			worldIn.spawnEntity(itementity);
		}
	}

	private static void extractInventory(World worldIn, EntityPlayer player, BlockPos pos) {
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

	private static void extract(World worldIn, EntityPlayer player, BlockPos pos) {
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

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		extract(worldIn, null, pos);
		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		@SuppressWarnings("deprecation")
		List<ItemStack> drops = super.getDrops(world, pos, state, fortune);
		if (world.getTileEntity(pos) instanceof EnderPedestalTileEntity) {
			EnderPedestalTileEntity te = (EnderPedestalTileEntity) world.getTileEntity(pos);
			if (te.getPedestalItem() != ItemStack.EMPTY)
				drops.add(te.getPedestalItem());
			for (int i = 0; i < te.getSizeInventory(); i++) {
				if (te.getStackInSlot(i) != null && te.getStackInSlot(i) != ItemStack.EMPTY)
					drops.add(te.removeStackFromSlot(i));
			}
		}
		return drops;
	}

	public TileEntity createNewTileEntity(World worldIn) {
		EnderPedestalTileEntity pedestal = new EnderPedestalTileEntity();
		return pedestal;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { HAS_AGITATOR, HAS_ACCUMULATOR });
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	@Deprecated
	public IBlockState getStateFromMeta(int meta) {
		switch (meta) {
		case 0:
			return this.getDefaultState();
		case 1:
			return this.getDefaultState().withProperty(HAS_AGITATOR, true);
		case 2:
			return this.getDefaultState().withProperty(HAS_ACCUMULATOR, true);
		}
		return this.getDefaultState();
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	public int getMetaFromState(IBlockState state) {
		if (state.getPropertyKeys().isEmpty()) {
			return 0;
		} else {
			boolean agitator = state.getValue(HAS_AGITATOR);
			boolean accumulator = state.getValue(HAS_ACCUMULATOR);

			if (agitator)
				return 1;
			if (accumulator)
				return 2;
			return 0;
		}
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.MIDDLE_POLE_THICK;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {
		addCollisionBoxToList(pos, entityBox, collidingBoxes, STICK_AABB);
		addCollisionBoxToList(pos, entityBox, collidingBoxes, BASE_AABB);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return STICK_AABB;
	}

	public class EnderPedestalItem extends ItemBlock {

		protected EnderPedestalItem() {
			super(EnderPedestal.this);
			setRegistryName(EnderPedestal.this.getRegistryName());
			setUnlocalizedName(getRegistryName().getResourcePath());
		}
	}
}
