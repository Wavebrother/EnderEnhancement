package wavebrother.enderEnhancement.common.init;

import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import wavebrother.enderEnhancement.Reference;

@EventBusSubscriber(bus = Bus.MOD, modid = Reference.MOD_ID)
public class ModEntities {


	public static void init() {
	}

	@SubscribeEvent
	public static void onEntitiesRegistry(final RegistryEvent.Register<EntityType<?>> entityRegistryEvent) {
		init();
//		entityRegistryEvent.getRegistry().registerAll(enderPedestal);
	}
}
