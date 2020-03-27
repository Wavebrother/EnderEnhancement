package wavebrother.enderEnhancement.common.item.tool;

import java.util.HashSet;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import wavebrother.enderEnhancement.EnderEnhancement;
import wavebrother.enderEnhancement.common.item.IEnderItem;
import wavebrother.enderEnhancement.common.util.EnderTier;

@EventBusSubscriber
public class EnderMultiTool extends ItemTool implements IEnderItem {

	private static final EnderToolsUtil tool = EnderToolsUtil.TOOL;

	static {
		MinecraftForge.EVENT_BUS.register(EnderMultiTool.class);
	}

	public EnderMultiTool(EnderTier material, String name) {
		super(tool.getDamage(material), tool.getSpeed(material), material.toolTier.material(), new HashSet<Block>());
		setCreativeTab(EnderEnhancement.CREATIVE_TAB);
		setRegistryName(name);
		setUnlocalizedName(name);
		this.tier = material;
		// TODO Auto-generated constructor stub
	}

	private final EnderTier tier;

	@Override
	public EnderTier getEnderTier() {
		return tier;
	}

//	@SubscribeEvent
//	public static void onHarvestBlock(HarvestDropsEvent event) {
//		if (!event.getWorld().isRemote() && event.isCancelable()) {
//			for (ItemStack drop : event.getDrops())
//				if (!event.getHarvester().addItemStackToInventory(drop)) {
//					Block.spawnAsEntity((World) event.getWorld(), event.getPos(), drop);
//				}
//			event.setCanceled(true);
//		}
//	}

	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state) {
		return efficiency;
	}

	public boolean canHarvestBlock(IBlockState blockIn) {
		@SuppressWarnings("unused")
		Block block = blockIn.getBlock();
		int i = this.getEnderTier().toolTier.getHarvestLevel();
		if (block.getHarvestTool(blockIn).equals("pickaxe")) {
			return i >= block.getHarvestLevel(blockIn);
		}
		Material material = blockIn.getMaterial();
		return material == Material.ROCK || material == Material.IRON || material == Material.ANVIL
				|| material == Material.DRAGON_EGG;
	}
}
