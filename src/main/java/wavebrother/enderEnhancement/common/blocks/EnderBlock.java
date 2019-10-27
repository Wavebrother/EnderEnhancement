package wavebrother.enderEnhancement.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import wavebrother.enderEnhancement.EnderEnhancement;
import wavebrother.enderEnhancement.common.util.EnderTier;

public class EnderBlock extends Block {

	public final EnderBlockItem blockItem;
	public EnderBlock(EnderTier tier, String name) {
		super(Block.Properties.from(Blocks.IRON_BLOCK).sound(SoundType.ANVIL));
		setRegistryName(name);
		blockItem = new EnderBlockItem();
	}
	public class EnderBlockItem extends BlockItem{

		protected EnderBlockItem() {
			super(EnderBlock.this, new Item.Properties().group(EnderEnhancement.CREATIVE_TAB));
			setRegistryName(EnderBlock.this.getRegistryName());
			// TODO Auto-generated constructor stub
		}
		
	}

}
