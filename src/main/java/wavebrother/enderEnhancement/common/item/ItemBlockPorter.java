package wavebrother.enderEnhancement.common.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.tileentity.TileEntity;
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

public class ItemBlockPorter extends Item implements IEnderItem {

	public static final String TargetPositionTag = "BlockPorterTargetPosition";

	public ItemBlockPorter(EnderTier tier, String name) {
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
//				if (worldIn.getBlockState(targetPos).getBlock() == Blocks.AIR) {
				stack.getOrCreateTag().putIntArray(TargetPositionTag, new int[] { targetPos.getX(), targetPos.getY(),
						targetPos.getZ(), worldIn.dimension.getType().getId() });
				return ActionResultType.SUCCESS;
//				} else {
//					return new ActionResult<ItemStack>(ActionResultType.FAIL, stack);
//				}
			} else if (stack.hasTag() && stack.getTag().contains(TargetPositionTag)) {
				int[] intPos = stack.getTag().getIntArray(TargetPositionTag);
				BlockPos oldPos = new BlockPos(intPos[0], intPos[1], intPos[2]);
				World oldWorld = worldIn.getServer().getWorld(DimensionType.getById(intPos[3]));
				BlockState oldState = oldWorld.getBlockState(oldPos);
				TileEntity oldTE = oldWorld.getTileEntity(oldPos);
				BlockState newState = worldIn.getBlockState(lookingAt.getPos());
				TileEntity newTE = worldIn.getTileEntity(lookingAt.getPos());
				if (newTE != null) {
					newTE.func_226984_a_(oldWorld, oldPos);
				}
				oldWorld.setBlockState(oldPos, newState);
				// worldIn.setTileEntity(oldPos, newTE);
				if (oldTE != null) {
					oldTE.func_226984_a_(worldIn, lookingAt.getPos());
				}
				worldIn.setBlockState(lookingAt.getPos(), oldState);
				// worldIn.setTileEntity(lookingAt.getPos(), oldTE);
				return ActionResultType.SUCCESS;
			} else {
				return super.onItemUseFirst(stack, context);
			}
		}
		return super.onItemUseFirst(stack, context);
	}

	public StringTextComponent getTargetBlockDisplay(ItemStack stack) {
		if (stack != null && stack.hasTag() && stack.getTag().contains(TargetPositionTag)) {
			int[] pos = stack.getTag().getIntArray(TargetPositionTag);
			return new StringTextComponent("This porter is set to (" + pos[0] + ", " + pos[1] + ", " + pos[2]
					+ ") in Dimension " + DimensionType.getKey(DimensionType.getById(pos[3])) + ".");
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
