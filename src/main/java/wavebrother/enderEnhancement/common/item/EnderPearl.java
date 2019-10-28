package wavebrother.enderEnhancement.common.item;

import net.minecraft.item.Item;
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

}
