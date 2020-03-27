package wavebrother.enderEnhancement.common.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import wavebrother.enderEnhancement.client.renderer.EnderPedestalTER;
import wavebrother.enderEnhancement.common.tiles.EnderPedestalTileEntity;

public class ClientProxy implements CommonProxy{

	@Override
	public void init() {
	}

	public void registerItemRenderer(Item item, int meta, String id) {
		ClientRegistry.bindTileEntitySpecialRenderer(EnderPedestalTileEntity.class, new EnderPedestalTER());
	}
}
