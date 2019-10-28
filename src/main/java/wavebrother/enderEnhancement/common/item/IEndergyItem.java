package wavebrother.enderEnhancement.common.item;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.extensions.IForgeItem;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(bus = Bus.FORGE)
public interface IEndergyItem extends IForgeItem {

	@SubscribeEvent
	default public void onItemTooltip(ItemTooltipEvent event) {
		if (getEndergyDisplay(null) != null)
			event.getToolTip().add(getEndergyDisplay(null).getRight());
	}

	public Pair<PlayerEntity, StringTextComponent> getEndergyDisplay(PlayerEntity player);
	
}
