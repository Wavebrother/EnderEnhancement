package wavebrother.enderEnhancement.common.item.tool;

import net.minecraft.item.ItemAxe;
import wavebrother.enderEnhancement.EnderEnhancement;
import wavebrother.enderEnhancement.common.item.IEnderItem;
import wavebrother.enderEnhancement.common.util.EnderTier;

public class EnderAxe extends ItemAxe implements IEnderItem {

	private static final EnderToolsUtil tool = EnderToolsUtil.AXE;

	public EnderAxe(EnderTier material, String name) {
		super(material.toolTier.material(), tool.getDamage(material), tool.getSpeed(material));
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
