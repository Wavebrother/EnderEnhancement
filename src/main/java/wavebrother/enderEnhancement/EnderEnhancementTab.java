package wavebrother.enderEnhancement;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import wavebrother.enderEnhancement.common.init.ModItems;

public class EnderEnhancementTab extends CreativeTabs{

	public EnderEnhancementTab() {
		super(Reference.MOD_ID);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ModItems.empoweredEnderPearl);
	}

}
