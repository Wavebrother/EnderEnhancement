package wavebrother.enderEnhancement.common.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import wavebrother.enderEnhancement.EnderEnhancement;
import wavebrother.enderEnhancement.common.util.EnderTier;

public class ItemEntityPorter extends Item implements IEnderItem {

	public static final String TargetPositionTag = "EntityPorterTargetPosition";
	// public static final String AnimationTag = "EntityPorterAnimationPosition";

	public ItemEntityPorter(EnderTier tier, String name) {
		super(new Properties().maxDamage(64).group(EnderEnhancement.CREATIVE_TAB));
		setRegistryName(name);
		this.tier = tier;
//		this.addPropertyOverride(new ResourceLocation("porting"), (item, unknown, player) -> {
//			if (player == null) {
//				return 0;
//			} else {
//				return !(player.getActiveItemStack().getItem() instanceof ItemEntityPorter && item.hasTag()
//						&& item.getTag().contains(AnimationTag)) ? 0
//								: (float) Math.floor(item.getTag().getInt(AnimationTag) / 5);
//			}
//		});
	}

	private final EnderTier tier;

	@Override
	public EnderTier getEnderTier() {
		return tier;
	}
//
//	@Override
//	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
//		if (stack.hasTag() && stack.getTag().contains(AnimationTag)) {
//			if (stack.getTag().getInt(AnimationTag) >= 24000) {
//				stack.getTag().putInt(AnimationTag, 0);
//			} else if (stack.getTag().getInt(AnimationTag) >= 0) {
//				stack.getTag().putInt(AnimationTag, stack.getTag().getInt(AnimationTag) + 1);
//			}
//		}
//		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
//	}

	@Override
	public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
		if (!context.getWorld().isRemote) {
			World worldIn = (ServerWorld) context.getWorld();
			PlayerEntity playerIn = context.getPlayer();
			float attrib = (float) playerIn.getAttribute(PlayerEntity.REACH_DISTANCE).getValue();
			float distance = playerIn.isCreative() ? attrib : attrib - 0.5F;
			BlockRayTraceResult lookingAt = lookingAt(playerIn, distance);
			if (worldIn.getBlockState(lookingAt.getPos()).getBlock() == Blocks.AIR)
				return ActionResultType.FAIL;
			if (playerIn.isCrouching()) {
				BlockPos targetPos = lookingAt.getPos().offset(lookingAt.getFace());
				stack.getOrCreateTag().putIntArray(TargetPositionTag, new int[] { targetPos.getX(), targetPos.getY(),
						targetPos.getZ(), worldIn.dimension.getType().getId() });
				playerIn.sendMessage(getTargetBlockDisplay(stack));
				return ActionResultType.SUCCESS;
			}
		}
		return super.onItemUseFirst(stack, context);
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, net.minecraft.entity.player.PlayerEntity playerIn,
			LivingEntity entity, net.minecraft.util.Hand hand) {
//		if (!stack.hasTag() || !stack.getTag().contains(AnimationTag) || stack.getTag().getInt(AnimationTag) <= 0) {
//			stack.getOrCreateTag().putInt(AnimationTag, 1);
		if (entity.world.isRemote || entity instanceof PlayerEntity || !stack.hasTag()
				|| !stack.getTag().contains(TargetPositionTag))
			return false;
		int[] intPos = stack.getTag().getIntArray(TargetPositionTag);
		BlockPos oldPos = new BlockPos(intPos[0], intPos[1], intPos[2]);
		DimensionType oldDim = entity.dimension;
		stack.setDamage(stack.getDamage() - 1);
		if (oldDim.getId() == intPos[3]) {
			entity.moveToBlockPosAndAngles(oldPos, entity.rotationYaw, entity.rotationPitch);
		} else {
			stack.setDamage(stack.getDamage() - 1);
			ServerWorld newWorld = entity.world.getServer().getWorld(DimensionType.getById(intPos[3]));
			Entity newEntity = entity.getType().create(newWorld);
			if (newEntity != null) {
				newEntity.copyDataFromOld(entity);
				newEntity.moveToBlockPosAndAngles(oldPos, newEntity.rotationYaw, newEntity.rotationPitch);
				newEntity.setMotion(entity.getMotion());
				newWorld.func_217460_e(newEntity);
			}
			entity.remove(false);
		}
		playerIn.getCooldownTracker().setCooldown(this, 3);
		return true;
//		}
//		return false;
	}

	public StringTextComponent getTargetBlockDisplay(ItemStack stack) {
		if (stack != null && stack.hasTag() && stack.getTag().contains(TargetPositionTag)) {
			int[] pos = stack.getTag().getIntArray(TargetPositionTag);
			return new StringTextComponent("This porter is set to (" + pos[0] + ", " + pos[1] + ", " + pos[2]
					+ ") in Dimension " + pos[3] + ".");
		}
		return null;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip,
			ITooltipFlag flagIn) {
		StringTextComponent display = getTargetBlockDisplay(stack);
		if (display != null)
			tooltip.add(display);
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	private static BlockRayTraceResult lookingAt(PlayerEntity player, double distance) {
		Vec3d vec3d = player.getEyePosition(0);
		Vec3d vec3d1 = player.getLook(0);
		Vec3d vec3d2 = vec3d.add(vec3d1.x * distance, vec3d1.y * distance, vec3d1.z * distance);
		return player.world.rayTraceBlocks(new RayTraceContext(vec3d, vec3d2, RayTraceContext.BlockMode.OUTLINE,
				RayTraceContext.FluidMode.NONE, player));
	}
}
