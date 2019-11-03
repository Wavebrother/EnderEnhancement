package wavebrother.enderEnhancement.common.item;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import wavebrother.enderEnhancement.Config;
import wavebrother.enderEnhancement.EnderEnhancement;
import wavebrother.enderEnhancement.common.util.EnderTier;
import wavebrother.enderEnhancement.common.util.TeleportUtil;
import wavebrother.enderEnhancement.common.util.VectorHelper;

public class ItemEnderPorter extends Item implements IEnderItem, IEndergyItem {

	public ItemEnderPorter(EnderTier tier, String name) {
		super(new Properties().maxDamage(64).group(EnderEnhancement.CREATIVE_TAB));
		setRegistryName(name);
		this.tier = tier;
	}

	private final EnderTier tier;

	@Override
	public EnderTier getEnderTier() {
		return tier;
	}

	@Override
	public Pair<PlayerEntity, StringTextComponent> getEndergyDisplay(PlayerEntity player) {
//		if (player.getPersistentData().contains(NBTKeys.endergyStored)) {
//			float endergy = event.getEntityPlayer().getPersistentData().getFloat(NBTKeys.endergyStored);
//			endergy = Math.round(endergy * 1000.0f) / 1000.0f;
//			return Pair.of(player, new StringTextComponent("You have " + endergy + "endergy stored."));
//		}
		return null;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		if (!worldIn.isRemote) {
			BlockRayTraceResult lookingAt = VectorHelper.getLookingAt(playerIn, tier, RayTraceContext.FluidMode.ANY);
			if (lookingAt.getType() == RayTraceResult.Type.MISS && playerIn.isSneaking()) {
				playerIn.setPositionAndUpdate(lookingAt.getHitVec().x, lookingAt.getHitVec().y,
						lookingAt.getHitVec().z);
			}
			if (worldIn.getBlockState(lookingAt.getPos()).getMaterial().isLiquid())
				return new ActionResult<ItemStack>(ActionResultType.FAIL, itemstack);

			BlockPos adjustedPos = lookingAt.getPos().offset(lookingAt.getFace());
			BlockRayTraceResult ground = worldIn
					.rayTraceBlocks(new RayTraceContext(new Vec3d(adjustedPos).add(0, 0.5, 0),
							new Vec3d(adjustedPos).add(0, Config.PORTER_RANGE.get() * -1, 0),
							RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.ANY, playerIn));
			if (ground.getType() == RayTraceResult.Type.MISS
					|| worldIn.getBlockState(ground.getPos()).getMaterial().isLiquid())
				return new ActionResult<ItemStack>(ActionResultType.FAIL, itemstack);
			BlockPos finalPos = ground.getPos().offset(ground.getFace());
			/* if (EndergyHandler.useEndergy(playerIn, 50) && */TeleportUtil.teleportTo(playerIn,
					finalPos.getX() + 0.5F, finalPos.getY(), finalPos.getZ() + 0.5F)/* ) */
			;
			itemstack.setDamage(itemstack.getDamage() - 1);
			return new ActionResult<ItemStack>(ActionResultType.SUCCESS, itemstack);
		}
		return new ActionResult<ItemStack>(ActionResultType.PASS, itemstack);
	}
}
