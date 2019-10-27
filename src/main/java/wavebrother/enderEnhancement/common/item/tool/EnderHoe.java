package wavebrother.enderEnhancement.common.item.tool;

import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import wavebrother.enderEnhancement.EnderEnhancement;
import wavebrother.enderEnhancement.common.item.IEnderItem;
import wavebrother.enderEnhancement.common.util.EnderTier;

public class EnderHoe extends HoeItem implements IEnderItem {

	private static final EnderToolsUtil tool = EnderToolsUtil.HOE;

	public EnderHoe(EnderTier material, String name) {
		super(material.toolTier, tool.getSpeed(material), new Item.Properties().group(EnderEnhancement.CREATIVE_TAB));
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
