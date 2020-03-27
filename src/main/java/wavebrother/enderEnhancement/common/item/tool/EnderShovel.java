package wavebrother.enderEnhancement.common.item.tool;

import net.minecraft.item.ItemSpade;
import wavebrother.enderEnhancement.EnderEnhancement;
import wavebrother.enderEnhancement.common.item.IEnderItem;
import wavebrother.enderEnhancement.common.util.EnderTier;

public class EnderShovel extends ItemSpade implements IEnderItem {

	private static final EnderToolsUtil tool = EnderToolsUtil.SHOVEL;

	public EnderShovel(EnderTier material, String name) {
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
