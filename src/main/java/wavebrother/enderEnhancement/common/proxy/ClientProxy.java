package wavebrother.enderEnhancement.common.proxy;

import net.minecraft.item.Item;

public class ClientProxy implements CommonProxy{

	@Override
	public void init() {
//		ModItems.registerRenders();
//		ModBlocks.registerRenders();
	}

	public void registerItemRenderer(Item item, int meta, String id) {
//		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Reference.MOD_ID + ":" + id, "inventory"));
	}
}
