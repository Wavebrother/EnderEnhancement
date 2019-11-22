package wavebrother.enderEnhancement.common.item;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;

public interface IEndergyItem extends IEnderItem {

//	@OnlyIn(Dist.CLIENT)
//	default public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip,
//			ITooltipFlag flagIn) {
//		if (getEndergyDisplay(stack) != null)
//			tooltip.add(getEndergyDisplay(stack));
//	}

	public StringTextComponent getEndergyDisplay(ItemStack stack);

}
