package wavebrother.enderEnhancement.common.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import wavebrother.enderEnhancement.common.util.EnderTier;

public interface IEnderItem {

	public EnderTier getEnderTier();

	public static void registerModel(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0,
				new ModelResourceLocation(item.getRegistryName(), null));
	}
}
