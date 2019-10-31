package wavebrother.enderEnhancement.common.item;

import java.util.List;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import wavebrother.enderEnhancement.Config;
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
		super(new Item.Properties().maxStackSize(1).group(EnderEnhancement.CREATIVE_TAB));
		setRegistryName(name);
		this.tier = tier;
	}

	@Override
	public EnderTier getEnderTier() {
		return tier;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack item = playerIn.getHeldItem(handIn);
		CompoundNBT NBT = item.getOrCreateTag();
		if (playerIn.isSneaking()) {
			NBT.putBoolean(accumulatorTag, !NBT.getBoolean(accumulatorTag));
			if (NBT.getBoolean(accumulatorTag)) {
				playerIn.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 0.3F, 1);
			} else {
				playerIn.playSound(SoundEvents.ITEM_BOTTLE_EMPTY, 0.3F, 1);
			}
			return new ActionResult<ItemStack>(ActionResultType.SUCCESS, item);
		} else
			return new ActionResult<ItemStack>(ActionResultType.PASS, item);
	}

	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		World world = context.getWorld();
		BlockPos blockpos = context.getPos();
		BlockState blockstate = world.getBlockState(blockpos);
		if (blockstate.getBlock() == ModBlocks.enderPedestal && !blockstate.get(EnderPedestal.HAS_ACCUMULATOR)
				&& !blockstate.get(EnderPedestal.HAS_AGITATOR)) {
			ItemStack itemstack = context.getItem();
			if (!world.isRemote) {
				EnderPedestal.insertItem(world, context.getPlayer(), blockpos, blockstate, itemstack);
				world.playEvent((PlayerEntity) null, 1010, blockpos, Item.getIdFromItem(this));
				itemstack.shrink(1);
			}

			return ActionResultType.SUCCESS;
		} else {
			return ActionResultType.PASS;
		}
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		if (stack.hasTag())
			return stack.getTag().getBoolean(accumulatorTag);
		return false;
	}

//	@Override
//	public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
//		stack.
//		return false;
//	}

	private static int getRange(EnderTier tier) {
		return Config.ACCUMULATOR_RANGE.get() * Config.ENDER_TIER_MULTIPLIER.get(tier).get();
	}

	public void collectItems(ItemStack stack, World worldIn, PlayerEntity playerIn, EnderPedestalTileEntity pedestal) {
		if (stack.hasTag() && stack.getTag().getBoolean(accumulatorTag)) {
			List<ItemEntity> items;
			BlockPos pos;
			if (playerIn != null) {
				pos = playerIn.getPosition();
			} else if (pedestal != null) {
				pos = pedestal.getPos();
			} else {
				return;
			}
			items = worldIn.getEntitiesWithinAABB(ItemEntity.class,
					new AxisAlignedBB(pos.getX() - getRange(getEnderTier()), pos.getY() - getRange(getEnderTier()),
							pos.getZ() - getRange(getEnderTier()), pos.getX() + getRange(getEnderTier()),
							pos.getY() + getRange(getEnderTier()), pos.getZ() + getRange(getEnderTier())),
					EntityPredicates.NOT_SPECTATING);
			for (ItemEntity itemEntity : items) {
				if (playerIn != null) {
					if (itemEntity.getThrowerId() != playerIn.getUniqueID()) {
						itemEntity.setNoPickupDelay();
						itemEntity.onCollideWithPlayer(playerIn);
					}
				} else if (pedestal != null) {
					pedestal.addItemStackToInventory(itemEntity.getItem());
					if (itemEntity.getItem().isEmpty())
						itemEntity.remove();
				} else
					return;
			}
		}
	}

	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (entityIn instanceof PlayerEntity && !worldIn.isRemote) {
			collectItems(stack, worldIn, (PlayerEntity) entityIn, null);
		}
	}
}
