package wavebrother.enderEnhancement.common.item;

import java.util.List;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import wavebrother.enderEnhancement.Config;
import wavebrother.enderEnhancement.EnderEnhancement;
import wavebrother.enderEnhancement.Reference;
import wavebrother.enderEnhancement.common.blocks.EnderPedestal;
import wavebrother.enderEnhancement.common.init.ModBlocks;
import wavebrother.enderEnhancement.common.util.EnderTier;

@EventBusSubscriber(modid = Reference.MOD_ID)
public class EndermanAgitator extends Item implements IEnderItem {

	public static final String agitatorTag = "endermanAgitator";
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
						SoundCategory.PLAYERS, 0.15F, 1.2F);
			} else {
				worldIn.playSound(playerIn, playerIn.getPosition(), SoundEvents.ENTITY_ENDERMAN_AMBIENT,
						SoundCategory.PLAYERS, 0.25F, 1F);
			}
			return new ActionResult<ItemStack>(ActionResultType.SUCCESS, item);
		} else
			return new ActionResult<ItemStack>(ActionResultType.PASS, item);
	}

	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		World world = context.getWorld();
		BlockPos blockpos = context.getPos();
		BlockState blockstate = world.getBlockState(blockpos);
		if (blockstate.getBlock() == ModBlocks.enderPedestal && !blockstate.get(EnderPedestal.HAS_AGITATOR)
				&& !blockstate.get(EnderPedestal.HAS_ACCUMULATOR)) {
			ItemStack itemstack = context.getItem();
			EnderPedestal.insertItem(world, context.getPlayer(), blockpos, blockstate, itemstack);
			world.playEvent((PlayerEntity) null, 1010, blockpos, Item.getIdFromItem(this));
			if (!world.isRemote) {
				itemstack.shrink(1);
			}

			return ActionResultType.SUCCESS;
		} else {
			return ActionResultType.PASS;
		}
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

	public static int getRange(EnderTier tier) {
		return Config.AGITATOR_RANGE.get() * Config.ENDER_TIER_MULTIPLIER.get(tier).get();
	}

	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (entityIn instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) entityIn;
			if (!player.isCreative()) {
				if (stack.hasTag() && stack.getTag().getBoolean(agitatorTag)) {
					if (worldIn.getGameTime() % 20 == 0) {
						List<EndermanEntity> endermen = worldIn.getEntitiesWithinAABB(EndermanEntity.class,
								new AxisAlignedBB(entityIn.posX - getRange(getEnderTier()),
										entityIn.posY - getRange(getEnderTier()),
										entityIn.posZ - getRange(getEnderTier()),
										entityIn.posX + getRange(getEnderTier()),
										entityIn.posY + getRange(getEnderTier()),
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
					}
				} else {
					player.getCooldownTracker().setCooldown(DummyAgitator.INSTANCE, 2);
					player.getPersistentData().putString(agitatorTag, getEnderTier().name());
				}
			}
		}
	}

	@SubscribeEvent
	public static void onEnderHit(LivingAttackEvent event) {
		if (event.getSource() instanceof EntityDamageSource
				&& ((EntityDamageSource) event.getSource()).getTrueSource() instanceof EndermanEntity
				&& event.getEntityLiving() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) event.getEntityLiving();
			if (player.getCooldownTracker().hasCooldown(DummyAgitator.INSTANCE)) {
				pacify(player);
				event.setCanceled(true);
			}
		}
	}
	
	private static void pacify(PlayerEntity player) {
		EnderTier tier = EnderTier.valueOf(player.getPersistentData().getString(agitatorTag));
		Vec3d playerPos = player.getPositionVec();
		List<EndermanEntity> endermen = player.world.getEntitiesWithinAABB(EndermanEntity.class,
				new AxisAlignedBB(playerPos.x - getRange(tier), playerPos.y - getRange(tier),
						playerPos.z - getRange(tier), playerPos.x + getRange(tier),
						playerPos.y + getRange(tier), playerPos.z + getRange(tier)),
				EntityPredicates.NOT_SPECTATING);
		for (EndermanEntity enderman : endermen) {
			if (enderman.getAttackTarget() instanceof PlayerEntity) {
				if (enderman.getAttackingEntity() instanceof PlayerEntity) {
					enderman.getCombatTracker().reset();
					enderman.setRevengeTarget(null);
					enderman.setAttackTarget(null);
				}
			}
		}
	}

	@SubscribeEvent
	public static void onEnderSetAttack(LivingSetAttackTargetEvent event) {
		if (event.getEntityLiving() instanceof EndermanEntity && event.getTarget() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) event.getTarget();
			if (player.getCooldownTracker().hasCooldown(DummyAgitator.INSTANCE)) {
				((EndermanEntity) event.getEntityLiving()).getCombatTracker().reset();
				((EndermanEntity) event.getEntityLiving()).setRevengeTarget(null);
				((EndermanEntity) event.getEntityLiving()).setAttackTarget(null);
			}
		}
	}

	public static class DummyAgitator extends Item {
		public static final DummyAgitator INSTANCE = new DummyAgitator();

		private DummyAgitator() {
			super(new Item.Properties());
		}
	}

}
