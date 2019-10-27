package wavebrother.enderEnhancement.common.item;

import java.util.Random;

import net.minecraft.entity.item.EnderPearlEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import wavebrother.enderEnhancement.EnderEnhancement;
import wavebrother.enderEnhancement.Reference;

public class ItemEnderPearler extends Item {

	public ItemEnderPearler() {
		super(new Properties().maxDamage(64).group(EnderEnhancement.CREATIVE_TAB));
		setRegistryName(Reference.EnderEnhancementItems.ENDERPEARLER.getRegistryName());
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);

		if (!playerIn.abilities.isCreativeMode) {
			if (!worldIn.isRemote) {
				EnderEnhancement.LOGGER.debug(itemstack.getDamage());
				itemstack.setDamage(itemstack.getDamage() + 1);
			}
//				itemstack.attemptDamageItem(1, new Random(),
//						(ServerPlayerEntity) worldIn.getPlayerByUuid(playerIn.getUniqueID()));
		}

		worldIn.playSound((PlayerEntity) playerIn, playerIn.posX, playerIn.posY, playerIn.posZ,
				SoundEvents.ENTITY_ENDER_PEARL_THROW, SoundCategory.NEUTRAL, 0.5F,
				0.4F / (random.nextFloat() * 0.4F + 0.8F));
		playerIn.getCooldownTracker().setCooldown(this, 20);
		if (!worldIn.isRemote) {
			EnderPearlEntity enderpearlentity = new EnderPearlEntity(worldIn, playerIn);
			enderpearlentity.func_213884_b(new ItemStack(Items.ENDER_PEARL));
			enderpearlentity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
			worldIn.addEntity(enderpearlentity);
		}

		playerIn.addStat(Stats.ITEM_USED.get(this));
		return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
	}
}
