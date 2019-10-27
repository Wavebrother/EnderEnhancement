package wavebrother.enderEnhancement.common.item;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import wavebrother.enderEnhancement.Config;
import wavebrother.enderEnhancement.EnderEnhancement;
import wavebrother.enderEnhancement.Reference;
import wavebrother.enderEnhancement.common.util.EnderTier;

@EventBusSubscriber(modid = Reference.MOD_ID)
public class EndermanAgitator extends Item implements IEnderItem {

	public static final String agitatorTag = "agitator";
	public final EnderTier tier;

	public EndermanAgitator(EnderTier tier, String name) {
		super(new Item.Properties().maxStackSize(1).group(EnderEnhancement.CREATIVE_TAB));
		setRegistryName(name);
		this.tier = tier;
	}

	@Override
	public EnderTier getEnderTier() {
		return tier;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack item = playerIn.getHeldItem(handIn);
		CompoundNBT NBT = item.getOrCreateTag();
		if (playerIn.isSneaking()) {
			NBT.putBoolean(agitatorTag, !NBT.getBoolean(agitatorTag));
			if (NBT.getBoolean(agitatorTag)) {
				worldIn.playSound(playerIn, playerIn.getPosition(), SoundEvents.ENTITY_ENDERMAN_SCREAM,
						SoundCategory.PLAYERS, 1, 1);
			} else {
				worldIn.playSound(playerIn, playerIn.getPosition(), SoundEvents.ENTITY_ENDERMAN_AMBIENT,
						SoundCategory.PLAYERS, 1, 1);
			}
			return new ActionResult<ItemStack>(ActionResultType.SUCCESS, item);
		} else
			return new ActionResult<ItemStack>(ActionResultType.PASS, item);
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		if (stack.hasTag())
			return stack.getTag().getBoolean(agitatorTag);
		return false;
	}

//	@Override
//	public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
//		stack.
//		return false;
//	}

	private static int getRange(EnderTier tier) {
		return Config.AGITATOR_RANGE.get() * Config.ENDER_TIER_MULTIPLIER.get(tier).get();
	}

	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (entityIn instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) entityIn;
			if (stack.hasTag() && stack.getTag().getBoolean(agitatorTag)) {
				List<EndermanEntity> endermen = worldIn.getEntitiesWithinAABB(EndermanEntity.class,
						new AxisAlignedBB(entityIn.posX - getRange(getEnderTier()),
								entityIn.posY - getRange(getEnderTier()), entityIn.posZ - getRange(getEnderTier()),
								entityIn.posX + getRange(getEnderTier()), entityIn.posY + getRange(getEnderTier()),
								entityIn.posZ + getRange(getEnderTier())),
						EntityPredicates.NOT_SPECTATING);
//				player.removeTag(agitatorTag);
				for (EndermanEntity enderman : endermen) {
//					if (!enderman.getTags().contains(agitateTag)) {
//						enderman.addTag(agitateTag);
					if (!(enderman.getAttackTarget() instanceof PlayerEntity
							&& enderman.getDistance(enderman.getAttackTarget()) < enderman.getDistance(player)))
						enderman.setAttackTarget(player);
//					}
				}
			} else {
				player.getCooldownTracker().setCooldown(DummyAgitator.INSTANCE, 2);
				player.getPersistentData().putString(agitatorTag, getEnderTier().name());
			}
		}
	}

	@Override
	public boolean onDroppedByPlayer(ItemStack item, PlayerEntity player) {
		player.removeTag(agitatorTag);
		return true;
	}

	@SubscribeEvent
	public static void onEnderHit(LivingAttackEvent event) {
		if (event.getSource() instanceof EntityDamageSource
				&& ((EntityDamageSource) event.getSource()).getTrueSource() instanceof EndermanEntity
				&& event.getEntityLiving() instanceof PlayerEntity) {
			Entity attacker = ((EntityDamageSource) event.getSource()).getTrueSource();
			PlayerEntity player = (PlayerEntity) event.getEntityLiving();
			if (player.getCooldownTracker().hasCooldown(DummyAgitator.INSTANCE)) {
				EnderTier tier = EnderTier.valueOf(player.getPersistentData().getString(agitatorTag));
				List<EndermanEntity> endermen = attacker.world.getEntitiesWithinAABB(EndermanEntity.class,
						new AxisAlignedBB(attacker.posX - getRange(tier), attacker.posY - getRange(tier),
								attacker.posZ - getRange(tier), attacker.posX + getRange(tier),
								attacker.posY + getRange(tier), attacker.posZ + getRange(tier)),
						EntityPredicates.NOT_SPECTATING);
				for (EndermanEntity enderman : endermen) {
					if (enderman.getAttackTarget() instanceof PlayerEntity) {
						if (enderman.getAttackingEntity() instanceof PlayerEntity) {
							enderman.getCombatTracker().reset();
						}
						enderman.setRevengeTarget(null);
						enderman.setAttackTarget(null);
					}
				}
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public static void onEnderSetAttack(LivingSetAttackTargetEvent event) {
		if (event.getEntityLiving() instanceof EndermanEntity && event.getTarget() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) event.getTarget();
			if (player.getCooldownTracker().hasCooldown(DummyAgitator.INSTANCE)) {
				((EndermanEntity) event.getEntityLiving()).setAttackTarget(null);
			}
		}
	}

	private static class DummyAgitator extends Item {
		protected static final DummyAgitator INSTANCE = new DummyAgitator();

		private DummyAgitator() {
			super(new Item.Properties());
		}
	}

}
