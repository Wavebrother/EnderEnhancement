package wavebrother.enderEnhancement.common.init;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import wavebrother.enderEnhancement.Reference;
import wavebrother.enderEnhancement.common.tiles.EnderPedestalTileEntity;

@EventBusSubscriber
public class ModTileEntities {

	@SubscribeEvent
	public static void onTileEntitiesRegistry(final RegistryEvent.Register<Block> tileEntityRegistryEvent) {
		GameRegistry.registerTileEntity(EnderPedestalTileEntity.class,
				new ResourceLocation(Reference.MOD_ID, Reference.TileEntities.ENDERPEDESTAL.getRegistryName()));
	}
}
