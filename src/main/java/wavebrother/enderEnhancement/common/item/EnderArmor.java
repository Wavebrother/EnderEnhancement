package wavebrother.enderEnhancement.common.item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import wavebrother.enderEnhancement.Configuration;
import wavebrother.enderEnhancement.EnderEnhancement;
import wavebrother.enderEnhancement.Reference;
import wavebrother.enderEnhancement.common.util.EnderTier;
import wavebrother.enderEnhancement.common.util.TeleportUtil;

@EventBusSubscriber(modid = Reference.MOD_ID)
public class EnderArmor extends ItemArmor implements IEnderItem {

	public static final Item COOLDOWNITEM = new Item();

	private static final int cooldownBase = 1200;
	protected final Random rand = new Random();

	private static final DamageSource[] doNotTeleportSource = { DamageSource.ON_FIRE, DamageSource.FALL,
			DamageSource.FIREWORKS, DamageSource.STARVE };

	static {
		MinecraftForge.EVENT_BUS.register(EnderArmor.class);
	}

	public EnderArmor(EnderTier material, EntityEquipmentSlot slot, String name) {
		super(material.armorMaterial.material(), material.toolTier.getHarvestLevel(), slot);
		setCreativeTab(EnderEnhancement.CREATIVE_TAB);
		setRegistryName(name);
		setUnlocalizedName(name);
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
		if (event.getEntityLiving() instanceof EntityPlayer && event.getEntityLiving().isServerWorld()
				&& ((event.getSource() instanceof EntityDamageSourceIndirect
						&& !Arrays.asList(doNotTeleportSource).contains(event.getSource()))
						|| event.getSource().isExplosion())) {
			EntityPlayer attacked = (EntityPlayer) event.getEntityLiving();
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
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (entityIn instanceof EntityPlayer && entityIn.isWet()
				&& checkArmor(stack, new ArrayList<ItemStack>(), (EntityPlayer) entityIn)) {
			this.attackEntityFrom((EntityPlayer) entityIn, DamageSource.DROWN, 0.0F);
		}
	}

	private int maxCooldown() {
		return cooldownBase / getEnderTier().multiplier();
	}

	private static boolean checkArmor(ItemStack item, ArrayList<ItemStack> enderArmor, EntityPlayer entity) {
		EnderTier material = EnderTier.DULL;
		for (ItemStack armor : entity.getArmorInventoryList()) {
			if (armor.getItem() instanceof EnderArmor)
				enderArmor.add(armor);
		}
		if (enderArmor.size() == 0)
			return false;
		else if (entity.getCooldownTracker().hasCooldown((EnderArmor) enderArmor.get(0).getItem()))
			return false;
		if (enderArmor.get(0).getItem() instanceof EnderArmor)
			material = ((EnderArmor) enderArmor.get(0).getItem()).getEnderTier();
		int water = 0;
		int attack = 4;
		switch (material) {
		case DULL:
			water = Configuration.EnderArmor.Dull_Tier_Water;
			attack = Configuration.EnderArmor.Dull_Tier_Attack;
			break;
		case EMPOWERED:
			water = Configuration.EnderArmor.Empowered_Tier_Water;
			attack = Configuration.EnderArmor.Empowered_Tier_Attack;
			break;
		case ENDER:
			water = Configuration.EnderArmor.Ender_Tier_Water;
			attack = Configuration.EnderArmor.Ender_Tier_Attack;
			break;
		case EXTREME:
			water = Configuration.EnderArmor.Extreme_Tier_Water;
			attack = Configuration.EnderArmor.Extreme_Tier_Attack;
			break;
		}
		if (item != null) {
			if (enderArmor.get(0) != item)
				return false;
			return enderArmor.size() >= water;
		} else {
			return enderArmor.size() >= attack;
		}
	}

	public boolean attackEntityFrom(EntityPlayer entityIn, DamageSource source, float amount) {
		if (entityIn.isEntityInvulnerable(source)) {
			return false;
		} else if (!(source instanceof EntityDamageSourceIndirect) && source != DamageSource.FIREWORKS) {
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
