package wavebrother.enderEnhancement.common.item.tool;

import java.util.HashMap;
import java.util.Map;

import wavebrother.enderEnhancement.common.util.EnderTier;

public enum EnderToolsUtil {
	AXE(new float[] { 5, 6, 6, 7 }, new float[] { -3.2f, -3.2f, -3.0f, -3.0f }),
	PICKAXE(new float[] { 1 }, new float[] { -2.8f }), HOE(new float[] { 0 }, new float[] { -3, -2, -1, 0 }),
	SHOVEL(new float[] { 1.5f }, new float[] { -3 }), SWORD(new float[] { 3 }, new float[] { -2.4f }),
	TOOL(new float[] { 6 }, new float[] { -3.2f });
	private final Map<EnderTier, Float> attackDamage = new HashMap<EnderTier, Float>();
	private final Map<EnderTier, Float> attackSpeed = new HashMap<EnderTier, Float>();

	EnderToolsUtil(float[] damage, float[] speed) {
		for (int i = 0; i < EnderTier.values().length; i++) {
			if (i < damage.length)
				attackDamage.put(EnderTier.values()[i], damage[i]);
			else
				attackDamage.put(EnderTier.values()[i], damage[0]);
			if (i < speed.length)
				attackSpeed.put(EnderTier.values()[i], speed[i]);
			else
				attackSpeed.put(EnderTier.values()[i], speed[0]);
		}
	}

	public Float getDamage(EnderTier tier) {
		return attackDamage.get(tier);
	}

	public Float getSpeed(EnderTier tier) {
		return attackSpeed.get(tier);
	}

//	public TieredItem newEnderTool(EnderTier material, String name) {
//		switch (this) {
//		case AXE:
//			return (AxeItem) new EnderAxe(material.toolTier, attackDamage.get(material),
//					attackSpeed.get(material), new Item.Properties().group(EnderEnhancement.CREATIVE_TAB))
//							.setRegistryName(name);
//		case HOE:
//			return (HoeItem) new HoeItem(material.toolTier, attackSpeed.get(material),
//					new Item.Properties().group(EnderEnhancement.CREATIVE_TAB)).setRegistryName(name);
//		case PICKAXE:
//			return (PickaxeItem) new PickaxeItem(material.toolTier, attackDamage.get(material).intValue(),
//					attackSpeed.get(material), new Item.Properties().group(EnderEnhancement.CREATIVE_TAB))
//							.setRegistryName(name);
//		case SHOVEL:
//			return (ShovelItem) new ShovelItem(material.toolTier, attackDamage.get(material),
//					attackSpeed.get(material), new Item.Properties().group(EnderEnhancement.CREATIVE_TAB))
//							.setRegistryName(name);
//		case SWORD:
//			return (SwordItem) new SwordItem(material.toolTier, attackDamage.get(material).intValue(),
//					attackSpeed.get(material), new Item.Properties().group(EnderEnhancement.CREATIVE_TAB))
//							.setRegistryName(name);
//		case TOOL:
//			break;
//		}
//		return null;
//	}

}
