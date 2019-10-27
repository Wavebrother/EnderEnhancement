package wavebrother.enderEnhancement.common.item;

import net.minecraft.item.Item;
import wavebrother.enderEnhancement.EnderEnhancement;
import wavebrother.enderEnhancement.common.util.EnderTier;

public class EnderStick extends Item implements IEnderItem {

	public EnderStick(EnderTier tier, String name) {
		super(new Properties().group(EnderEnhancement.CREATIVE_TAB));
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
