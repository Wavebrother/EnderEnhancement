package wavebrother.enderEnhancement.common.init;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import wavebrother.enderEnhancement.Reference;
import wavebrother.enderEnhancement.client.renderer.EnderPedestalTER;
import wavebrother.enderEnhancement.common.tiles.EnderPedestalTileEntity;

@EventBusSubscriber(bus = Bus.MOD, modid = Reference.MOD_ID)
public class ModTileEntities {

//	public static TileEntityType<EnderPedestalTileEntity> enderPedestal;

	public static void init() {
//		enderPedestal = TileEntityType.Builder.create(EnderPedestalTileEntity::new, ModBlocks.enderPedestal)
//				.build(null);
//		enderPedestal.setRegistryName(Reference.TileEntities.ENDERPEDESTAL.getRegistryName());
	}

	@SubscribeEvent
	public static void onTileEntitiesRegistry(final RegistryEvent.Register<TileEntityType<?>> tileEntityRegistryEvent) {
		init();
//		tileEntityRegistryEvent.getRegistry().registerAll(enderPedestal);

		DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
			registerRenderers();
		});
	}

	@OnlyIn(Dist.CLIENT)
	public static void registerRenderers() {
		ClientRegistry.bindTileEntitySpecialRenderer(EnderPedestalTileEntity.class, new EnderPedestalTER());
	}
}
