package wavebrother.enderEnhancement.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import wavebrother.enderEnhancement.Configuration;
import wavebrother.enderEnhancement.EnderEnhancement;
import wavebrother.enderEnhancement.common.util.EnderTier;
import wavebrother.enderEnhancement.common.util.TeleportUtil;
import wavebrother.enderEnhancement.common.util.VectorHelper;

public class EnderPorter extends Item implements IEnderItem {

	public EnderPorter(EnderTier tier, String name) {
		setCreativeTab(EnderEnhancement.CREATIVE_TAB);
		setMaxDamage(64);
		setRegistryName(name);
		setUnlocalizedName(name);
		this.tier = tier;
	}

	private final EnderTier tier;

	@Override
	public EnderTier getEnderTier() {
		return tier;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		if (!worldIn.isRemote) {
			RayTraceResult lookingAt = VectorHelper.getLookingAt(playerIn, tier, true);
			if (lookingAt != null && lookingAt.typeOfHit == RayTraceResult.Type.MISS && playerIn.isSneaking()) {
				playerIn.setPositionAndUpdate(lookingAt.hitVec.x, lookingAt.hitVec.y, lookingAt.hitVec.z);
				return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
			}
			if (lookingAt != null && worldIn.getBlockState(lookingAt.getBlockPos()).getMaterial().isLiquid())
				return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);

			BlockPos adjustedPos = lookingAt.getBlockPos().offset(lookingAt.sideHit);
			RayTraceResult ground = worldIn.rayTraceBlocks(new Vec3d(adjustedPos).addVector(0, 0.5, 0),
					new Vec3d(adjustedPos).addVector(0, Configuration.Porter_Range * -1, 0), true, true, true);
			if (ground.typeOfHit == RayTraceResult.Type.MISS
					|| worldIn.getBlockState(ground.getBlockPos()).getMaterial().isLiquid())
				return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
			BlockPos finalPos = ground.getBlockPos().offset(ground.sideHit);
			if (/* EndergyHandler.takeEndergy(50, playerIn) && */TeleportUtil.teleportTo(playerIn,
					finalPos.getX() + 0.5F, finalPos.getY(), finalPos.getZ() + 0.5F) && !playerIn.isCreative()) {
				boolean flag = false;
				for (int i = 0; i < playerIn.inventory.mainInventory.size() && !flag; i++) {
					ItemStack item = playerIn.inventory.mainInventory.get(i);
					if ((item.getItem() instanceof EnderPearl && ((EnderPearl) item.getItem()).getEnderTier() == tier)
							|| (item.getItem() == Items.ENDER_PEARL && tier == EnderTier.ENDER)) {
						item.shrink(1);
						flag = true;
					}
				}
				if (!flag)
					itemstack.setItemDamage(itemstack.getItemDamage() + 1);
			}
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack);
	}
}
