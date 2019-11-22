package wavebrother.enderEnhancement.common.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import wavebrother.enderEnhancement.EnderEnhancement;
import wavebrother.enderEnhancement.common.util.EnderTier;
import wavebrother.enderEnhancement.common.util.NBTKeys;

public class EndergyCollector extends Item implements IEndergyItem {

	public EndergyCollector(EnderTier tier, String name) {
		super(new Properties().maxStackSize(16).group(EnderEnhancement.CREATIVE_TAB));
		setRegistryName(name);
		this.tier = tier;
		// TODO Auto-generated constructor stub
	}

	private final EnderTier tier;

	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (stack.hasTag() && stack.getTag().contains(NBTKeys.endergyStored)) {
			if (stack.getTag().getFloat(NBTKeys.endergyStored) < getEndergyMax())
				stack.getTag().putFloat(NBTKeys.endergyStored, stack.getTag().getFloat(NBTKeys.endergyStored)
						+ getEndergyToAdd(stack.getTag().getFloat(NBTKeys.endergyStored)));
		} else {
			stack.getOrCreateTag().putFloat(NBTKeys.endergyStored, getEndergyToAdd(0));
		}
		// MinecraftForge.EVENT_BUS.post(new EndergyChangedEvent(entityIn,
		// stack.getTag().getFloat(NBTKeys.endergyStored)));
//		float endergy = entityIn.getPersistentData().getFloat(NBTKeys.endergyStored);
//		endergy = Math.round(endergy * 100.0f) / 100.0f;
//		stack.getOrCreateTag().putString(NBTKeys.endergyStored, "You have " + endergy + " endergy stored.");
	}

	private float getEndergyToAdd(float endergyStored) {
		float endergyToAdd = getEnderTier().multiplier()
				* ((-.049f * endergyStored / getEndergyMax()) + .05f);
		if (endergyStored + endergyToAdd > getEndergyMax()) {
			return getEndergyMax() - endergyStored;
		}
		return endergyToAdd;
	}

	private float getEndergyMax() {
		return getEnderTier().multiplier() * 1000.0f;
	}

	@Override
	public StringTextComponent getEndergyDisplay(ItemStack stack) {
		if (stack != null && stack.hasTag() && stack.getTag().contains(NBTKeys.endergyStored)) {
			return new StringTextComponent("This collector has "
					+ Math.round(stack.getTag().getFloat(NBTKeys.endergyStored)) + " endergy stored.");
		}
		return null;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip,
			ITooltipFlag flagIn) {
		if (getEndergyDisplay(stack) != null)
			tooltip.add(getEndergyDisplay(stack));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public EnderTier getEnderTier() {
		return tier;
	}

}
