package wavebrother.enderEnhancement.common.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import wavebrother.enderEnhancement.common.item.EndergyCollector;

@EventBusSubscriber(bus = Bus.FORGE)
public class EndergyHandler {
	static {
		MinecraftForge.EVENT_BUS.register(EndergyHandler.class);
	}

	private static List<ItemStack> getCollectors(PlayerEntity player) {
		PlayerInventory inv = player.inventory;
		List<ItemStack> collectors = new ArrayList<ItemStack>();
		for (ItemStack item : inv.mainInventory) {
			if (item.getItem() instanceof EndergyCollector)
				collectors.add(item);
		}
		for (ItemStack item : inv.offHandInventory) {
			if (item.getItem() instanceof EndergyCollector)
				collectors.add(item);
		}
		return collectors;
	}

	public static float getTotalEndergy(PlayerEntity player) {
		float endergyTotal = 0;
		for (ItemStack collector : getCollectors(player)) {
			if (collector.hasTag() && collector.getTag().contains(NBTKeys.endergyStored)) {
				endergyTotal += collector.getTag().getFloat(NBTKeys.endergyStored);
			}
		}
		return endergyTotal;
	}

	public static boolean takeEndergy(float endergyIn, PlayerEntity player) {
		float endergy = endergyIn;
		if (getTotalEndergy(player) < endergy)
			return false;
		for (ItemStack collector : getCollectors(player)) {
			if (endergy <= 0)
				break;
			if (collector.hasTag() && collector.getTag().contains(NBTKeys.endergyStored)) {
				if (collector.getTag().getFloat(NBTKeys.endergyStored) < endergy) {
					endergy -= collector.getTag().getFloat(NBTKeys.endergyStored);
					collector.getTag().putFloat(NBTKeys.endergyStored, 0);
				} else {
					collector.getTag().putFloat(NBTKeys.endergyStored,
							collector.getTag().getFloat(NBTKeys.endergyStored) - endergy);
					endergy = 0;
				}
			}
		}
//		MinecraftForge.EVENT_BUS.post(new EndergyChangedEvent(player, endergyIn));
		return true;
	}
//
//	public static class EndergyChangedEvent extends Event {
//		private final float endergy;
//
//		EndergyChangedEvent(PlayerEntity player, float endergy) {
//			this.endergy = endergy;
//		}
//
//		public float getEndergy() {
//			return endergy;
//		}
//	}
}
