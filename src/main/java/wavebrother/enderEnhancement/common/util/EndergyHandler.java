package wavebrother.enderEnhancement.common.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import wavebrother.enderEnhancement.common.item.*;
import wavebrother.enderEnhancement.common.item.tool.*;

@EventBusSubscriber(bus = Bus.FORGE)
public class EndergyHandler {
	static {
		MinecraftForge.EVENT_BUS.register(EndergyHandler.class);
	}
//
//	@SubscribeEvent
//	public static void onPlayerDeath(PlayerEvent.Clone event) {
//		if (event.isWasDeath() && event.getOriginal().getPersistentData().contains(NBTKeys.endergyStored)) {
//			event.getPlayer().getPersistentData().putInt(NBTKeys.endergyStored,
//					event.getOriginal().getPersistentData().getInt(NBTKeys.endergyStored));
//		}
//	}
//
//	@SubscribeEvent
//	public static void onPlayerTick(PlayerTickEvent event) {
//		float endergyGenerated = 0;
//		if (event.side == LogicalSide.SERVER && event.phase == Phase.START) {
//			for (ItemStack item : event.player.inventory.mainInventory) {
//				if (checkItem(item.getItem()))
//					endergyGenerated += ((IEnderItem) item.getItem()).getEnderTier().multiplier();
//				if(item.getItem() instanceof IEndergyItem)
//					((IEndergyItem) item.getItem()).getEndergyDisplay(null);
//			}
//			for (ItemStack item : event.player.inventory.offHandInventory) {
//				if (checkItem(item.getItem()))
//					endergyGenerated += ((IEnderItem) item.getItem()).getEnderTier().multiplier();
//			}
//			for (ItemStack item : event.player.inventory.armorInventory) {
//				if (checkItem(item.getItem()))
//					endergyGenerated += ((IEnderItem) item.getItem()).getEnderTier().multiplier();
//			}
//			addEndergy(event.player, endergyGenerated / 400.0f);
//		}
//	}
//
//	private static boolean checkItem(Item item) {
//		return item instanceof EnderAxe || item instanceof EnderHoe || item instanceof EnderMultiTool
//				|| item instanceof EnderPickaxe || item instanceof EnderShovel || item instanceof EnderSword
//				|| item instanceof EnderArmor || item instanceof EndermanAgitator || item instanceof ItemEnderPorter
//				|| item instanceof ItemAccumulator;
//	}
//
//	public static boolean addEndergy(PlayerEntity player, float endergyGenerated) {
//		float endergyStored = player.getPersistentData().getFloat(NBTKeys.endergyStored);
//		player.getPersistentData().putFloat(NBTKeys.endergyStored, endergyStored + endergyGenerated);
//		MinecraftForge.EVENT_BUS.post(new EndergyChangedEvent(player, endergyStored + endergyGenerated));
//		return true;
//	}
//
//	public static boolean useEndergy(PlayerEntity player, float endergyUsed) {
//		float endergyStored = player.getPersistentData().getFloat(NBTKeys.endergyStored);
//		if (endergyUsed > endergyStored)
//			return false;
//		player.getPersistentData().putFloat(NBTKeys.endergyStored, endergyStored - endergyUsed);
//		MinecraftForge.EVENT_BUS.post(new EndergyChangedEvent(player, endergyStored - endergyUsed));
//		return true;
//	}
//
	public static class EndergyChangedEvent extends Event {
		private final float endergy;

		EndergyChangedEvent(PlayerEntity player, float endergy) {
			this.endergy = endergy;
		}

		public float getEndergy() {
			return endergy;
		}
	}
}
