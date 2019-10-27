package wavebrother.enderEnhancement.common.init;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import wavebrother.enderEnhancement.Reference;
import wavebrother.enderEnhancement.common.blocks.EnderPedestal;
import wavebrother.enderEnhancement.common.blocks.EnderBlock;
import wavebrother.enderEnhancement.common.tiles.EnderPedestalTileEntity;
import wavebrother.enderEnhancement.common.util.EnderTier;

@EventBusSubscriber(bus = Bus.MOD, modid = Reference.MOD_ID)
public class ModBlocks {
	
	public static EnderBlock dullEnderBlock;
	public static EnderBlock enderBlock;
	public static EnderBlock empoweredEnderBlock;
	public static EnderBlock extremeEnderBlock;
	
	public static EnderPedestal enderPedestal;

	public static void init(){
		dullEnderBlock = new EnderBlock(EnderTier.DULL, Reference.Blocks.DULLENDERBLOCK.getRegistryName());
		enderBlock = new EnderBlock(EnderTier.ENDER, Reference.Blocks.ENDERBLOCK.getRegistryName());
		empoweredEnderBlock = new EnderBlock(EnderTier.EMPOWERED, Reference.Blocks.EMPOWEREDENDERBLOCK.getRegistryName());
		extremeEnderBlock = new EnderBlock(EnderTier.EXTREME, Reference.Blocks.EXTREMEENDERBLOCK.getRegistryName());
		enderPedestal = new EnderPedestal("block_ender_pedestal");
	}
	
    @SubscribeEvent
    public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
    	init();
//		EnderEnhancement.LOGGER.info("REGISTER THE BLOCKS\n\n\n\n\n\n\n\nREGISTER THE BLOCKS");
//		System.out.println("REGISTER THE BLOCKS\n\n\n\n\n\n\n\nREGISTER THE BLOCKS");
        blockRegistryEvent.getRegistry().registerAll(
                dullEnderBlock,
                enderBlock,
                empoweredEnderBlock,
                extremeEnderBlock,
                enderPedestal
        );
    }

//	public static void registerRenders(){
//		registerRender(dullEnderBlock);
//		registerRender(enderBlock);
//		registerRender(empoweredEnderBlock);
//		registerRender(extremeEnderBlock);
//	}
	
//	private static void registerRender(Block block){
//		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block),  0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
//	
//	}
//    @OnlyIn(Dist.CLIENT)
//    public static void registerRenderers() {
//        FMLJavaModLoadingContext.get().getModEventBus().addListener(event -> {
//            ClientRegistry.bindTileEntitySpecialRenderer(RenderBlockTileEntity.class, new RenderBlockTER());
//        });
//    }
}
