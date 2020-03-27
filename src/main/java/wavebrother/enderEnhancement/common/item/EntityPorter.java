package wavebrother.enderEnhancement.common.item;

import java.util.List;

import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import wavebrother.enderEnhancement.EnderEnhancement;
import wavebrother.enderEnhancement.common.util.EnderTier;

public class EntityPorter extends Item implements IEnderItem {

	public static final String TargetPositionTag = "EntityPorterTargetPosition";
	// public static final String AnimationTag = "EntityPorterAnimationPosition";

	public EntityPorter(EnderTier tier, String name) {
		setCreativeTab(EnderEnhancement.CREATIVE_TAB);
		setMaxDamage(64);
		setRegistryName(name);
		setUnlocalizedName(name);
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
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			ItemStack stack = playerIn.getHeldItem(hand);
			if (worldIn.getBlockState(pos).getBlock() == Blocks.AIR)
				return EnumActionResult.FAIL;
			if (playerIn.isSneaking()) {
				BlockPos targetPos = pos.offset(facing);
				if (!stack.hasTagCompound())
					stack.setTagCompound(new NBTTagCompound());
				stack.getTagCompound().setIntArray(TargetPositionTag,
						new int[] { targetPos.getX(), targetPos.getY(), targetPos.getZ(), playerIn.dimension });
				playerIn.sendMessage(new TextComponentString(getTargetBlockDisplay(stack)));
				return EnumActionResult.SUCCESS;
			}
		}
		return super.onItemUse(playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase entityIn,
			EnumHand hand) {
		if (entityIn.world.isRemote || entityIn instanceof EntityPlayer || !stack.hasTagCompound()
				|| !stack.getTagCompound().hasKey(TargetPositionTag))
			return false;
		int[] intPos = stack.getTagCompound().getIntArray(TargetPositionTag);
		BlockPos oldPos = new BlockPos(intPos[0], intPos[1], intPos[2]);
		int oldDim = entityIn.dimension;
		stack.setItemDamage(stack.getItemDamage() - 1);
		if (oldDim == intPos[3]) {
			entityIn.moveToBlockPosAndAngles(oldPos, entityIn.rotationYaw, entityIn.rotationPitch);
		} else {
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
				stack.setItemDamage(stack.getItemDamage() + 1);
			World newWorld = entityIn.world.getMinecraftServer().getWorld(intPos[3]);
			if (!entityIn.world.isRemote && !entityIn.isDead) {
				if (!net.minecraftforge.common.ForgeHooks.onTravelToDimension(entityIn, intPos[3]))
					return false;
				entityIn.world.profiler.startSection("changeDimension");
				MinecraftServer minecraftserver = entityIn.getServer();
				int i = entityIn.dimension;
				WorldServer worldserver = minecraftserver.getWorld(i);
				WorldServer worldserver1 = minecraftserver.getWorld(intPos[3]);
				entityIn.dimension = intPos[3];

				entityIn.world.removeEntity(entityIn);
				entityIn.isDead = false;
				entityIn.world.profiler.startSection("reposition");
				BlockPos blockpos;

				double moveFactor = worldserver.provider.getMovementFactor()
						/ worldserver1.provider.getMovementFactor();
				float f = entityIn.rotationYaw;
				entityIn.setLocationAndAngles(oldPos.getX(), oldPos.getY(), oldPos.getZ(), entityIn.rotationYaw,
						entityIn.rotationPitch);
				blockpos = new BlockPos(entityIn);

				worldserver.updateEntityWithOptionalForce(entityIn, false);
				entityIn.world.profiler.endStartSection("reloading");
				Entity entity = EntityList.newEntity(entityIn.getClass(), worldserver1);

				if (entity != null) {
					NBTTagCompound nbttagcompound = entityIn.writeToNBT(new NBTTagCompound());
					nbttagcompound.removeTag("Dimension");
					entity.readFromNBT(nbttagcompound);

					entity.moveToBlockPosAndAngles(blockpos, entity.rotationYaw, entity.rotationPitch);

					boolean flag2 = entity.forceSpawn;
					entity.forceSpawn = true;
					worldserver1.spawnEntity(entity);
					entity.forceSpawn = flag2;
					worldserver1.updateEntityWithOptionalForce(entity, false);
				}

				entityIn.isDead = true;
				entityIn.world.profiler.endSection();
				worldserver.resetUpdateEntityTick();
				worldserver1.resetUpdateEntityTick();
				entityIn.world.profiler.endSection();
			}
//			entity.moveToBlockPosAndAngles(oldPos, entity.rotationYaw, entity.rotationPitch);
//			entityIn.setVelocity(entityIn.getVelocity());
//			newWorld.func_217460_e(entity);
		}
		playerIn.getCooldownTracker().setCooldown(this, 3);
		return true;
//		}
//		return false;
	}

	public String getTargetBlockDisplay(ItemStack stack) {
		if (stack != null && stack.hasTagCompound() && stack.getTagCompound().hasKey(TargetPositionTag)) {
			int[] pos = stack.getTagCompound().getIntArray(TargetPositionTag);
			return "This porter is set to (" + pos[0] + ", " + pos[1] + ", " + pos[2] + ") in Dimension " + pos[3]
					+ ".";
		}
		return null;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		String display = getTargetBlockDisplay(stack);
		if (display != null)
			tooltip.add(display);
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	private static RayTraceResult lookingAt(EntityPlayer player, double distance) {
		Vec3d vec3d = player.getPositionVector().addVector(0, player.getEyeHeight(), 0);
		Vec3d vec3d1 = player.getLook(0);
		Vec3d vec3d2 = vec3d.addVector(vec3d1.x * distance, vec3d1.y * distance, vec3d1.z * distance);
		return player.world.rayTraceBlocks(vec3d, vec3d2, true, true, false);
	}
}
