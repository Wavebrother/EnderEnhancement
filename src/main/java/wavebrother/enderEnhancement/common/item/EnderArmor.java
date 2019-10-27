package wavebrother.enderEnhancement.common.item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import wavebrother.enderEnhancement.Config;
import wavebrother.enderEnhancement.EnderEnhancement;
import wavebrother.enderEnhancement.Reference;
import wavebrother.enderEnhancement.common.util.EnderTier;
import wavebrother.enderEnhancement.common.util.EnderTier.EnderArmorMaterial;

@EventBusSubscriber(modid = Reference.MOD_ID)
public class EnderArmor extends ArmorItem implements IEnderItem {

	protected final Random rand = new Random();

	private static final int cooldownBase = 1200;

	private static final DamageSource[] doNotTeleportSource = { DamageSource.ON_FIRE, DamageSource.FALL,
			DamageSource.FIREWORKS, DamageSource.STARVE };

	static {
		MinecraftForge.EVENT_BUS.register(EnderArmor.class);
	}

	public EnderArmor(EnderTier material, EquipmentSlotType slot, String name) {
		super(material.armorMaterial, slot, new Item.Properties().group(EnderEnhancement.CREATIVE_TAB));
		setRegistryName(name);
		this.tier = material;
		// TODO Auto-generated constructor stub
	}

	private final EnderTier tier;

	@Override
	public EnderTier getEnderTier() {
		return tier;
	}

	@SubscribeEvent
	public static void entityAttacked(LivingAttackEvent event) {
		if (event.getEntityLiving() instanceof PlayerEntity && event.getEntityLiving().isServerWorld()
				&& event.getSource() instanceof IndirectEntityDamageSource
				&& !Arrays.asList(doNotTeleportSource).contains(event.getSource())) {
			PlayerEntity attacked = (PlayerEntity) event.getEntityLiving();
			ArrayList<ItemStack> enderArmor = new ArrayList<ItemStack>();
			if (checkArmor(null, enderArmor, attacked)) {
				for (int i = 0; i < 64; ++i) {
					if (((EnderArmor) enderArmor.get(0).getItem()).teleportRandomly(attacked)) {
						event.setCanceled(true);
						return;
					}
				}
			}
		}
	}

	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (entityIn instanceof PlayerEntity && this.material instanceof EnderArmorMaterial
				&& entityIn.isInWaterRainOrBubbleColumn()
				&& checkArmor(stack, new ArrayList<ItemStack>(), (PlayerEntity) entityIn)) {
			this.attackEntityFrom((PlayerEntity) entityIn, DamageSource.DROWN, 0.0F);
			EnderEnhancement.getLogger().debug(itemSlot);
		}
	}

	private int maxCooldown() {
		return cooldownBase / ((EnderArmorMaterial) material).getTier().multiplier();
	}

	private static boolean checkArmor(ItemStack item, ArrayList<ItemStack> enderArmor, PlayerEntity entity) {
		EnderArmorMaterial material = EnderTier.DULL.armorMaterial;
		for (ItemStack armor : entity.getArmorInventoryList()) {
			if (armor.getItem() instanceof EnderArmor)
				enderArmor.add(armor);
		}
		if (enderArmor.size() == 0)
			return false;
		else if (entity.getCooldownTracker().hasCooldown((EnderArmor) enderArmor.get(0).getItem()))
			return false;
		if (((EnderArmor) enderArmor.get(0).getItem()).material instanceof EnderArmorMaterial)
			material = (EnderArmorMaterial) ((EnderArmor) enderArmor.get(0).getItem()).material;
		if (item != null) {
			if (enderArmor.get(0) != item)
				return false;
			return enderArmor.size() >= Config.ENDER_ARMOR_WATER_MINIMUM.get(material.getTier()).get();
		} else {
			return enderArmor.size() >= Config.ENDER_ARMOR_ATTACK_MINIMUM.get(material.getTier()).get();
		}
	}

	public boolean attackEntityFrom(PlayerEntity entityIn, DamageSource source, float amount) {
		if (entityIn.isInvulnerableTo(source)) {
			return false;
		} else if (!(source instanceof IndirectEntityDamageSource) && source != DamageSource.FIREWORKS) {
			boolean flag = entityIn.attackEntityFrom(source, amount);
			if (source.isUnblockable() && this.rand.nextInt(10) != 0) {
				this.teleportRandomly(entityIn);
			}

			return flag;
		} else {
			for (int i = 0; i < 64; ++i) {
				if (this.teleportRandomly(entityIn)) {
					return true;
				}
			}

			return false;
		}
	}

	/**
	 * Teleport the enderman to a random nearby position
	 */
	protected boolean teleportRandomly(PlayerEntity entityIn) {
		entityIn.getCooldownTracker().setCooldown(this, maxCooldown());
		int range = Config.ENDER_ARMOR_TELEPORT_RANGE.get();
		double d0 = entityIn.posX + (this.rand.nextDouble() - 0.5D) * range;
		double d1 = entityIn.posY + (double) (this.rand.nextInt(range) - (range / 2));
		double d2 = entityIn.posZ + (this.rand.nextDouble() - 0.5D) * range;
		return this.teleportTo(entityIn, d0, d1, d2);
	}

	/**
	 * Teleport the enderman to another entity
	 */
//	private boolean teleportToEntity(Entity p_70816_1_) {
//		Vec3d vec3d = new Vec3d(equippedPlayer.posX - p_70816_1_.posX, equippedPlayer.getBoundingBox().minY
//				+ (double) (equippedPlayer.getHeight() / 2.0F) - p_70816_1_.posY + (double) p_70816_1_.getEyeHeight(),
//				equippedPlayer.posZ - p_70816_1_.posZ);
//		vec3d = vec3d.normalize();
//		double d0 = 16.0D;
//		double d1 = equippedPlayer.posX + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3d.x * 16.0D;
//		double d2 = equippedPlayer.posY + (double) (this.rand.nextInt(16) - 8) - vec3d.y * 16.0D;
//		double d3 = equippedPlayer.posZ + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3d.z * 16.0D;
//		return this.teleportTo(d1, d2, d3);
//	}

	/**
	 * Teleport the enderman
	 */
	private boolean teleportTo(PlayerEntity entityIn, double x, double y, double z) {
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(x, y, z);

		while (blockpos$mutableblockpos.getY() > 0
				&& !entityIn.world.getBlockState(blockpos$mutableblockpos).getMaterial().blocksMovement()) {
			blockpos$mutableblockpos.move(Direction.DOWN);
		}

		if (!entityIn.world.getBlockState(blockpos$mutableblockpos).getMaterial().blocksMovement()) {
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
