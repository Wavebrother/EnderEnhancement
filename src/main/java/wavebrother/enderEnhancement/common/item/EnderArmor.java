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
import net.minecraft.util.IndirectEntityDamageSource;
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
import wavebrother.enderEnhancement.common.util.TeleportUtil;

@EventBusSubscriber(modid = Reference.MOD_ID)
public class EnderArmor extends ArmorItem implements IEnderItem {

	public static final Item COOLDOWNITEM = new Item(new Item.Properties());

	private static final int cooldownBase = 1200;
	protected final Random rand = new Random();

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
				&& ((event.getSource() instanceof IndirectEntityDamageSource
						&& !Arrays.asList(doNotTeleportSource).contains(event.getSource()))
						|| event.getSource().isExplosion())) {
			PlayerEntity attacked = (PlayerEntity) event.getEntityLiving();
			ArrayList<ItemStack> enderArmor = new ArrayList<ItemStack>();
			if (checkArmor(null, enderArmor, attacked)) {
				for (int i = 0; i < 64; ++i) {
					if (TeleportUtil.teleportRandomly(attacked,
							((EnderArmor) enderArmor.get(0).getItem()).maxCooldown())) {
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
				TeleportUtil.teleportRandomly(entityIn, maxCooldown());
			}

			return flag;
		} else {
			for (int i = 0; i < 64; ++i) {
				if (TeleportUtil.teleportRandomly(entityIn, maxCooldown())) {
					return true;
				}
			}

			return false;
		}
	}

}
