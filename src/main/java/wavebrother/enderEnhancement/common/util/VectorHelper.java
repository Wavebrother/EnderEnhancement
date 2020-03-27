package wavebrother.enderEnhancement.common.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import wavebrother.enderEnhancement.Configuration;

public class VectorHelper {
	public static RayTraceResult getLookingAt(EntityPlayer player, EnderTier tier, ItemStack tool) {
		return getLookingAt(player, tier, false);
	}

	public static RayTraceResult getLookingAt(EntityPlayer player, EnderTier tier, boolean stopOnLiquid) {
		World world = player.world;

		Vec3d look = player.getLookVec();
		Vec3d start = new Vec3d(player.posX, player.posY + player.getEyeHeight(), player.posZ);

		double rayTraceRange = Configuration.Porter_Range * tier.multiplier();
		Vec3d end = new Vec3d(player.posX + look.x * rayTraceRange,
				player.posY + player.getEyeHeight() + look.y * rayTraceRange, player.posZ + look.z * rayTraceRange);
		RayTraceResult result = world.rayTraceBlocks(start, end, stopOnLiquid, true, true);
		return result;
	}

	public static BlockPos getPosLookingAt(EntityPlayer player, EnderTier tier, ItemStack tool) {
		return getLookingAt(player, tier, tool).getBlockPos();
	}
}