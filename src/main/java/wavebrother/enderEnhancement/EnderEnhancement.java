package wavebrother.enderEnhancement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import wavebrother.enderEnhancement.client.renderer.EnderPedestalTER;
import wavebrother.enderEnhancement.common.tiles.EnderPedestalTileEntity;
import wavebrother.enderEnhancement.common.util.EnderTier;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class EnderEnhancement {

	public static final CreativeTabs CREATIVE_TAB = new EnderEnhancementTab();
	public static final Logger LOGGER = LogManager.getLogger();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		for (EnderTier tier : EnderTier.values())
			;
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(MobDropsHandler.class);

		if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
			registerRenderers();
	}

	@SideOnly(value = Side.CLIENT)
	public static void registerRenderers() {
		ClientRegistry.bindTileEntitySpecialRenderer(EnderPedestalTileEntity.class, new EnderPedestalTER());
	}

	public static Logger getLogger() {
		return LOGGER;
	}
}