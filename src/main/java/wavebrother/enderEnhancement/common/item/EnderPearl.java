package wavebrother.enderEnhancement.common.item;

import net.minecraft.entity.item.EnderPearlEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import wavebrother.enderEnhancement.EnderEnhancement;
import wavebrother.enderEnhancement.common.util.EnderTier;

public class EnderPearl extends Item implements IEnderItem {

	public EnderPearl(EnderTier tier, String name) {
		super(new Properties().maxStackSize(16).group(EnderEnhancement.CREATIVE_TAB));
		setRegistryName(name);
		this.tier = tier;
		// TODO Auto-generated constructor stub
	}

	private final EnderTier tier;

	@Override
	public EnderTier getEnderTier() {
		return tier;
	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		if (!playerIn.abilities.isCreativeMode) {
			itemstack.shrink(1);
		}

		worldIn.playSound((PlayerEntity) null, playerIn.posX, playerIn.posY, playerIn.posZ,
				SoundEvents.ENTITY_ENDER_PEARL_THROW, SoundCategory.NEUTRAL, 0.5F,
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
		if (!worldIn.isRemote) {
			EnderPearlEntity enderpearlentity = new EnderPearlEntity(worldIn, playerIn);
			enderpearlentity.setItem(itemstack);
			enderpearlentity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, velocity, 1.0F);
			worldIn.addEntity(enderpearlentity);
		}

		playerIn.addStat(Stats.ITEM_USED.get(this));
		return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
	}

}
