package wavebrother.enderEnhancement.common.item;

import java.util.List;

import org.apache.logging.log4j.LogManager;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import wavebrother.enderEnhancement.Configuration;
import wavebrother.enderEnhancement.EnderEnhancement;
import wavebrother.enderEnhancement.Reference;
import wavebrother.enderEnhancement.common.blocks.EnderPedestal;
import wavebrother.enderEnhancement.common.init.ModBlocks;
import wavebrother.enderEnhancement.common.util.EnderTier;

@EventBusSubscriber(modid = Reference.MOD_ID)
public class EndermanAgitator extends Item implements IEnderItem {

	public static final String agitatorTag = "endermanAgitator";
	public final EnderTier tier;

	public static final Item DUMMY = new Item();

	public EndermanAgitator(EnderTier tier, String name) {
		setMaxStackSize(1);
		setCreativeTab(EnderEnhancement.CREATIVE_TAB);
		setRegistryName(name);
		setUnlocalizedName(name);
		this.tier = tier;
	}

	@Override
	public EnderTier getEnderTier() {
		return tier;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack itemstack = player.getHeldItem(hand);
		NBTTagCompound NBT;
		if (!itemstack.hasTagCompound())
			itemstack.setTagCompound(new NBTTagCompound());
		NBT = itemstack.getTagCompound();
		if (player.isSneaking()) {
			NBT.setBoolean(agitatorTag, !NBT.getBoolean(agitatorTag));
			if (NBT.getBoolean(agitatorTag)) {
				world.playSound(player, player.getPosition(), SoundEvents.ENTITY_ENDERMEN_SCREAM, SoundCategory.PLAYERS,
						0.15F, 1.2F);
			} else {
				world.playSound(player, player.getPosition(), SoundEvents.ENTITY_ENDERMEN_AMBIENT,
						SoundCategory.PLAYERS, 0.25F, 1F);
			}
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
		} else
			return super.onItemRightClick(world, player, hand);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World world, BlockPos blockpos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState blockstate = world.getBlockState(blockpos);
		ItemStack itemstack = playerIn.getHeldItem(hand);
		if (blockstate.getBlock() == ModBlocks.enderPedestal && !blockstate.getValue(EnderPedestal.HAS_AGITATOR)
				&& !blockstate.getValue(EnderPedestal.HAS_ACCUMULATOR)) {
			EnderPedestal.insertItem(world, playerIn, blockpos, blockstate, itemstack);
			world.playEvent((EntityPlayer) null, 1010, blockpos, Item.getIdFromItem(this));
			if (!world.isRemote) {
				itemstack.shrink(1);
			}

			return EnumActionResult.SUCCESS;
		} else {
			return EnumActionResult.PASS;
		}
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		if (stack.hasTagCompound())
			return stack.getTagCompound().getBoolean(agitatorTag);
		return false;
	}

//	@Override
//	public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
//		stack.
//		return false;
//	}

	public static int getRange(EnderTier tier) {
		return Configuration.Agitator_Range * tier.multiplier();
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (entityIn instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityIn;
			if (!player.isCreative()) {
				if (stack.hasTagCompound() && stack.getTagCompound().getBoolean(agitatorTag)) {
					if (worldIn.getWorldTime() % 20 == 0) {
						List<EntityEnderman> endermen = worldIn.getEntitiesWithinAABB(EntityEnderman.class,
								new AxisAlignedBB(entityIn.posX - getRange(getEnderTier()),
										entityIn.posY - getRange(getEnderTier()),
										entityIn.posZ - getRange(getEnderTier()),
										entityIn.posX + getRange(getEnderTier()),
										entityIn.posY + getRange(getEnderTier()),
										entityIn.posZ + getRange(getEnderTier())),
								EntitySelectors.NOT_SPECTATING);
//				player.removeTag(agitatorTag);
						for (EntityEnderman enderman : endermen) {
//					if (!enderman.getTags().contains(agitateTag)) {
//						enderman.addTag(agitateTag);
							if (!(enderman.getAttackTarget() instanceof EntityPlayer
									&& enderman.getDistance(enderman.getAttackTarget()) < enderman.getDistance(player)))
								enderman.setAttackTarget(player);
//					}
						}
					}
				} else {
					player.getCooldownTracker().setCooldown(DUMMY, 2);
					player.getEntityData().setString(agitatorTag, getEnderTier().name());
				}
			}
		}
	}

	@SubscribeEvent
	public static void onEnderHit(LivingAttackEvent event) {
		if (event.getSource() instanceof EntityDamageSource
				&& ((EntityDamageSource) event.getSource()).getTrueSource() instanceof EntityEnderman
				&& event.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			if (player.getCooldownTracker().hasCooldown(DUMMY)) {
				pacify(player);
				event.setCanceled(true);
			}
		}
	}

	private static void pacify(EntityPlayer player) {
		EnderTier tier = EnderTier.valueOf(player.getEntityData().getString(agitatorTag));
		LogManager.getLogger().info(tier);
		Vec3d playerPos = player.getPositionVector();
		List<EntityEnderman> endermen = player.world.getEntitiesWithinAABB(EntityEnderman.class,
				new AxisAlignedBB(playerPos.x - getRange(tier), playerPos.y - getRange(tier),
						playerPos.z - getRange(tier), playerPos.x + getRange(tier), playerPos.y + getRange(tier),
						playerPos.z + getRange(tier)),
				EntitySelectors.NOT_SPECTATING);
		LogManager.getLogger().info(endermen.size());
		for (EntityEnderman enderman : endermen) {
			if (enderman.getAttackTarget() instanceof EntityPlayer) {
				enderman.getCombatTracker().reset();
				enderman.setRevengeTarget(null);
				enderman.setAttackTarget(null);
			}
		}
	}

	@SubscribeEvent
	public static void onEnderSetAttack(LivingSetAttackTargetEvent event) {
		if (event.getEntityLiving() instanceof EntityEnderman && event.getTarget() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getTarget();
			if (player.getCooldownTracker().hasCooldown(DUMMY)) {
				((EntityEnderman) event.getEntityLiving()).getCombatTracker().reset();
				((EntityEnderman) event.getEntityLiving()).setRevengeTarget(null);
				((EntityEnderman) event.getEntityLiving()).setAttackTarget(null);
			}
		}
	}
}
