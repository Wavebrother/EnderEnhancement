package wavebrother.enderEnhancement.common.proxy;

import net.minecraft.item.Item;

public interface CommonProxy {

	public void init();

	public void registerItemRenderer(Item item, int i, String name);

}
