package wavebrother.enderEnhancement.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import wavebrother.enderEnhancement.EnderEnhancement;
import wavebrother.enderEnhancement.common.util.EnderTier;

public class EnderBlock extends Block {

	public final EnderBlockItem blockItem;

	public EnderBlock(EnderTier tier, String name) {
		super(Material.ROCK);
		setCreativeTab(EnderEnhancement.CREATIVE_TAB).setHardness(2.0F).setResistance(10.0F);
		setRegistryName(name);
		blockItem = new EnderBlockItem();
		setUnlocalizedName(name);
	}

	public class EnderBlockItem extends ItemBlock {

		protected EnderBlockItem() {
			super(EnderBlock.this);
			setRegistryName(EnderBlock.this.getRegistryName());
			setUnlocalizedName(getRegistryName().getResourcePath());
		}

	}

}
