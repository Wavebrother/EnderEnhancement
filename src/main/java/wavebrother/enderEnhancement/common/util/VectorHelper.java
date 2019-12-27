package wavebrother.enderEnhancement.common.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import wavebrother.enderEnhancement.Config;

public class VectorHelper {
	public static BlockRayTraceResult getLookingAt(PlayerEntity player, EnderTier tier, ItemStack tool) {
		return getLookingAt(player, tier, RayTraceContext.FluidMode.NONE);
	}

	public static BlockRayTraceResult getLookingAt(PlayerEntity player, EnderTier tier,
			RayTraceContext.FluidMode rayTraceFluid) {
		World world = player.world;

		Vec3d look = player.getLookVec();
		Vec3d playerPos = player.getPositionVec();
		Vec3d start = new Vec3d(playerPos.x, playerPos.y + player.getEyeHeight(), playerPos.z);

		double rayTraceRange = Config.PORTER_RANGE.get() * tier.multiplier();
		Vec3d end = new Vec3d(playerPos.x + look.x * rayTraceRange,
				playerPos.y + player.getEyeHeight() + look.y * rayTraceRange, playerPos.z + look.z * rayTraceRange);
		RayTraceContext context = new RayTraceContext(start, end, RayTraceContext.BlockMode.OUTLINE, rayTraceFluid,
				player);
		BlockRayTraceResult result = world.rayTraceBlocks(context);
		return result;
	}

	public static BlockPos getPosLookingAt(PlayerEntity player, EnderTier tier, ItemStack tool) {
		return VectorHelper.getLookingAt(player, tier, tool).getPos();
	}
}