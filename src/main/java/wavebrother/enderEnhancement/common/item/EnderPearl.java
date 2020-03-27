package wavebrother.enderEnhancement.common.item;

import java.util.Random;

import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import wavebrother.enderEnhancement.EnderEnhancement;
import wavebrother.enderEnhancement.common.util.EnderTier;

public class EnderPearl extends Item implements IEnderItem {
	
	private Random random = new Random();

	public EnderPearl(EnderTier tier, String name) {
		setCreativeTab(EnderEnhancement.CREATIVE_TAB);
		setMaxStackSize(16);
		setRegistryName(name);
		setUnlocalizedName(name);
		this.tier = tier;
		// TODO Auto-generated constructor stub
	}

	private final EnderTier tier;

	@Override
	public EnderTier getEnderTier() {
		return tier;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		if (!playerIn.capabilities.isCreativeMode) {
			itemstack.shrink(1);
		}

		world.playSound((EntityPlayer) null, playerIn.posX, playerIn.posY, playerIn.posZ,
				SoundEvents.ENTITY_ENDERPEARL_THROW, SoundCategory.NEUTRAL, 0.5F,
				0.4F / (random.nextFloat() * 0.4F + 0.8F));
		playerIn.getCooldownTracker().setCooldown(this, 20);
		float velocity = 1.5F;
		switch(tier) {
		case DULL:
			velocity = 1.0F;
			break;
		case EMPOWERED:
			velocity = 2.0F;
			break;
		case EXTREME:
			velocity = 3.0F;
			break;
		default:
			break;
		}
		if (!world.isRemote) {
			EntityEnderPearl enderpearlentity = new EntityEnderPearl(world, playerIn);
			enderpearlentity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, velocity, 1.0F);
			world.spawnEntity(enderpearlentity);
		}

		playerIn.addStat(StatList.getObjectUseStats(this));
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
	}

}
