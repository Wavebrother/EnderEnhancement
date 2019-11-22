package wavebrother.enderEnhancement.common.item;

import net.minecraftforge.common.extensions.IForgeItem;
import wavebrother.enderEnhancement.common.util.EnderTier;

public interface IEnderItem extends IForgeItem {

	public EnderTier getEnderTier();
}
