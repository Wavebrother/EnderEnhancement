package wavebrother.enderEnhancement.common.item;

import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import wavebrother.enderEnhancement.Configuration;
import wavebrother.enderEnhancement.EnderEnhancement;
import wavebrother.enderEnhancement.Reference;
import wavebrother.enderEnhancement.common.blocks.EnderPedestal;
import wavebrother.enderEnhancement.common.init.ModBlocks;
import wavebrother.enderEnhancement.common.tiles.EnderPedestalTileEntity;
import wavebrother.enderEnhancement.common.util.EnderTier;

@EventBusSubscriber(modid = Reference.MOD_ID)
public class ItemAccumulator extends Item implements IEnderItem {

	public static final String accumulatorTag = "accumulator";
	public final EnderTier tier;

	public ItemAccumulator(EnderTier tier, String name) {
		setMaxStackSize(1);
		setCreativeTab(EnderEnhancement.CREATIVE_TAB);
		setRegistryName(name);
		setUnlocalizedName(name);
		this.tier = tier;
	}

	@Override
	public EnderTier getEnderTier() {
		return tier;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn) {
		ItemStack itemstack = player.getHeldItem(handIn);
		NBTTagCompound NBT;
		if (!itemstack.hasTagCompound())
			itemstack.setTagCompound(new NBTTagCompound());
		NBT = itemstack.getTagCompound();
		if (player.isSneaking()) {
			NBT.setBoolean(accumulatorTag, !NBT.getBoolean(accumulatorTag));
			if (NBT.getBoolean(accumulatorTag)) {
				player.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 0.3F, 1);
			} else {
				player.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 0.3F, -1);
			}
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
		} else 
		return super.onItemRightClick(worldIn, player, handIn);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos blockpos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState blockstate = world.getBlockState(blockpos);
		ItemStack itemstack = player.getHeldItem(hand);
		if (blockstate.getBlock() == ModBlocks.enderPedestal && !blockstate.getValue(EnderPedestal.HAS_ACCUMULATOR)
				&& !blockstate.getValue(EnderPedestal.HAS_AGITATOR)) {
			EnderPedestal.insertItem(world, player, blockpos, blockstate, itemstack);
			world.playEvent((EntityPlayer) null, 1010, blockpos, Item.getIdFromItem(this));
			if (!world.isRemote) {
				itemstack.shrink(1);
			}

			return EnumActionResult.SUCCESS;
		} else {
			return EnumActionResult.PASS;
		}
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		if (stack.hasTagCompound())
			return stack.getTagCompound().getBoolean(accumulatorTag);
		return false;
	}

//	@Override
//	public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
//		stack.
//		return false;
//	}

	private static int getRange(EnderTier tier) {
		return Configuration.Accumulator_Range * tier.multiplier();
	}

	public void collectItems(ItemStack stack, World worldIn, EntityPlayer playerIn, EnderPedestalTileEntity pedestal) {
		if (stack.hasTagCompound() && stack.getTagCompound().getBoolean(accumulatorTag)) {
			List<EntityItem> items;
			BlockPos pos;
			if (playerIn != null) {
				pos = playerIn.getPosition();
			} else if (pedestal != null) {
				pos = pedestal.getPos();
			} else {
				return;
			}
			items = worldIn.getEntitiesWithinAABB(EntityItem.class,
					new AxisAlignedBB(pos.getX() - getRange(getEnderTier()), pos.getY() - getRange(getEnderTier()),
							pos.getZ() - getRange(getEnderTier()), pos.getX() + getRange(getEnderTier()),
							pos.getY() + getRange(getEnderTier()), pos.getZ() + getRange(getEnderTier())),
					EntitySelectors.NOT_SPECTATING);
			for (EntityItem itemEntity : items) {
				if (playerIn != null) {
					if (itemEntity.getThrower() != playerIn.getName()) {
						itemEntity.onCollideWithPlayer(playerIn);
					}
				} else if (pedestal != null && !itemEntity.cannotPickup()) {
					pedestal.addItemStackToInventory(itemEntity.getItem());
					if (itemEntity.getItem().isEmpty())
						itemEntity.setDead();
				} else
					return;
			}
		}
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (entityIn instanceof EntityPlayer && !worldIn.isRemote) {
			collectItems(stack, worldIn, (EntityPlayer) entityIn, null);
		}
	}
}
