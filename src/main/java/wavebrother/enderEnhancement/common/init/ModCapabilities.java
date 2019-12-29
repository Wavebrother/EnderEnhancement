package wavebrother.enderEnhancement.common.init;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import wavebrother.enderEnhancement.Config;
import wavebrother.enderEnhancement.Reference;
import wavebrother.enderEnhancement.common.capabilities.CapabilityEndergyProvider;

@EventBusSubscriber(bus = Bus.MOD, modid = Reference.MOD_ID)
public class ModCapabilities {


	public static void init() {
	}

	@SubscribeEvent
	public static void onAttachCapabilities(final AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof PlayerEntity)
			event.addCapability(
					new ResourceLocation(Reference.MOD_ID, Reference.Capabilities.ENDERGYCAPABILITY.getRegistryName()),
					new CapabilityEndergyProvider(Config.ENDERGY_MAX.get(), 0));
	}
}
