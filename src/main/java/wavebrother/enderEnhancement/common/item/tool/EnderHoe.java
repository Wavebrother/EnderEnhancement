package wavebrother.enderEnhancement.common.item.tool;

import net.minecraft.item.ItemHoe;
import wavebrother.enderEnhancement.EnderEnhancement;
import wavebrother.enderEnhancement.common.item.IEnderItem;
import wavebrother.enderEnhancement.common.util.EnderTier;

public class EnderHoe extends ItemHoe implements IEnderItem {

	private static final EnderToolsUtil tool = EnderToolsUtil.HOE;

	public EnderHoe(EnderTier material, String name) {
		super(material.toolTier.material());
		setCreativeTab(EnderEnhancement.CREATIVE_TAB);
		setRegistryName(name);
		setUnlocalizedName(name);
		this.tier = material;
		// TODO Auto-generated constructor stub
	}

	private final EnderTier tier;

	@Override
	public EnderTier getEnderTier() {
		return tier;
	}


}
