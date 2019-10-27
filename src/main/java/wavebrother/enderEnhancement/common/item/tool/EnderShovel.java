package wavebrother.enderEnhancement.common.item.tool;

import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;
import wavebrother.enderEnhancement.EnderEnhancement;
import wavebrother.enderEnhancement.common.item.IEnderItem;
import wavebrother.enderEnhancement.common.util.EnderTier;

public class EnderShovel extends ShovelItem implements IEnderItem {

	private static final EnderToolsUtil tool = EnderToolsUtil.SHOVEL;

	public EnderShovel(EnderTier material, String name) {
		super(material.toolTier, tool.getDamage(material), tool.getSpeed(material),
				new Item.Properties().group(EnderEnhancement.CREATIVE_TAB));
		setRegistryName(name);
		this.tier = material;
		// TODO Auto-generated constructor stub
	}

	private final EnderTier tier;

	@Override
	public EnderTier getEnderTier() {
		return tier;
	}


}
