package wavebrother.enderEnhancement;

import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod(value = Reference.MOD_ID)
public class EnderEnhancement {

	public static final ItemGroup CREATIVE_TAB = new EnderEnhancementTab();
	public static final Logger LOGGER = LogManager.getLogger();

	public EnderEnhancement() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);

		// Register the setup method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

		Config.loadConfig(Config.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve(Reference.MOD_ID + "-client.toml"));
		Config.loadConfig(Config.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(Reference.MOD_ID + "-common.toml"));

		// EndermanEntity.death

		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(MobDropsHandler.class);
		
//		CapabilityManager.INSTANCE.register(CapabilityEndergy.class, new CapabilityEndergy.EndergyStorage(0), factory);
	}

	private void setup(final FMLCommonSetupEvent event) {
	}

	/**
	 * Only run on the client making it a safe place to register client components.
	 * Remember that you shouldn't reference client only methods in this class as
	 * it'll crash the mod :P
	 */
	private void doClientStuff(final FMLClientSetupEvent event) {
		// Register the container screens.
	}

	@SubscribeEvent
	public void onServerStarting(FMLServerStartingEvent event) {
	}

	/**
	 * Intermod communication
	 */
	// Register the enqueueIMC method for modloading
	private void enqueueIMC(final InterModEnqueueEvent event) {
	}

	// Register the processIMC method for modloading
	private void processIMC(final InterModProcessEvent event) {
		// some example code to receive and process InterModComms from other mods
		LOGGER.info("Got IMC {}",
				event.getIMCStream().map(m -> m.getMessageSupplier().get()).collect(Collectors.toList()));
	}

	public static Logger getLogger() {
		return LOGGER;
	}
}