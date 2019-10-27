package wavebrother.enderEnhancement.common.item.tool;

import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import wavebrother.enderEnhancement.EnderEnhancement;
import wavebrother.enderEnhancement.common.item.IEnderItem;
import wavebrother.enderEnhancement.common.util.EnderTier;

public class EnderPickaxe extends PickaxeItem implements IEnderItem {

	private static final EnderToolsUtil tool = EnderToolsUtil.PICKAXE;

	public EnderPickaxe(EnderTier material, String name) {
		super(material.toolTier, tool.getDamage(material).intValue(), tool.getSpeed(material),
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
