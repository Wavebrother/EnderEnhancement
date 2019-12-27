package wavebrother.enderEnhancement.common.util;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import wavebrother.enderEnhancement.Config;
import wavebrother.enderEnhancement.common.item.EnderArmor;

public class TeleportUtil {

	protected static final Random rand = new Random();

	/**
	 * Teleport the player to a random nearby position
	 */
	public static boolean teleportRandomly(PlayerEntity entityIn, int cooldown) {
		entityIn.getCooldownTracker().setCooldown(EnderArmor.COOLDOWNITEM, cooldown);
		int range = Config.ENDER_ARMOR_TELEPORT_RANGE.get();
		Vec3d entityPos = entityIn.getPositionVec();
		double d0 = entityPos.x + (rand.nextDouble() - 0.5D) * range;
		double d1 = entityPos.y + (double) (rand.nextInt(range) - (range / 2));
		double d2 = entityPos.z + (rand.nextDouble() - 0.5D) * range;
		return teleportTo(entityIn, d0, d1, d2);
	}

	/**
	 * Teleport the player to another entity
	 */
	public static boolean teleportToEntity(PlayerEntity player, Entity target) {
		Vec3d targetPos = target.getPositionVec();
		Vec3d playerPos = player.getPositionVec();
		Vec3d vec3d = new Vec3d(targetPos.x - playerPos.x, target.getBoundingBox().minY
				+ (double) (target.getHeight() / 2.0F) - playerPos.y + (double) player.getEyeHeight(),
				targetPos.z - playerPos.z);
		vec3d = vec3d.normalize();
		double d0 = 16.0D;
		double d1 = targetPos.x + (rand.nextDouble() - 0.5D) * 8.0D - vec3d.x * d0;
		double d2 = targetPos.y + (double) (rand.nextInt(16) - 8) - vec3d.y * d0;
		double d3 = targetPos.z + (rand.nextDouble() - 0.5D) * 8.0D - vec3d.z * d0;
		return teleportTo(null, d1, d2, d3);
	}

	/**
	 * Teleport the player
	 */
	public static boolean teleportTo(PlayerEntity entityIn, double x, double y, double z) {
		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable(x, y, z);

		while (blockpos$mutable.getY() > 0
				&& !entityIn.world.getBlockState(blockpos$mutable).getMaterial().blocksMovement()) {
			blockpos$mutable.move(Direction.DOWN);
		}

		if (!entityIn.world.getBlockState(blockpos$mutable).getMaterial().blocksMovement()) {
			return false;
		} else {
			net.minecraftforge.event.entity.living.EnderTeleportEvent event = new net.minecraftforge.event.entity.living.EnderTeleportEvent(
					entityIn, x, y, z, 0);
			if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event))
				return false;
			boolean flag = entityIn.attemptTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), true);
			if (flag) {
				entityIn.world.playSound((PlayerEntity) null, entityIn.prevPosX, entityIn.prevPosY, entityIn.prevPosZ,
						SoundEvents.ENTITY_ENDERMAN_TELEPORT, entityIn.getSoundCategory(), 1.0F, 1.0F);
				entityIn.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
			}

			return flag;
		}
	}

}
