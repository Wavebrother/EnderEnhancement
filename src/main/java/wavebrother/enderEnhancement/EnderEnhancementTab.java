package wavebrother.enderEnhancement;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import wavebrother.enderEnhancement.common.init.ModItems;

public class EnderEnhancementTab extends ItemGroup{

	public EnderEnhancementTab() {
		super(Reference.MOD_ID);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(ModItems.empoweredEnderPearl);
	}

}
