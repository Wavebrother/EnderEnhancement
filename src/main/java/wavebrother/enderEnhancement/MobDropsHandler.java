package wavebrother.enderEnhancement;

import java.util.Random;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import wavebrother.enderEnhancement.common.item.tool.EnderSword;
import wavebrother.enderEnhancement.common.util.EnderTier;

@EventBusSubscriber(bus = Bus.FORGE)
public class MobDropsHandler {

	private static final Random random = new Random();

	@SubscribeEvent
	public void onMobDrops(LivingDropsEvent event) {
		if (event.getEntity() instanceof EndermanEntity
				&& event.getEntityLiving().getPersistentData().contains(EnderSword.hitTag)) {
			EnderTier tier = EnderTier
					.valueOf(event.getEntityLiving().getPersistentData().getString(EnderSword.hitTag));
			if (random.nextDouble() < 1/* / tier.multiplier() */) {
				ItemStack stack = new ItemStack(tier.getEnderPearl());
				ItemEntity drop = new ItemEntity(event.getEntity().world, event.getEntity().posX,
						event.getEntity().posY, event.getEntity().posZ, stack);

				event.getDrops().add(drop);
			}
		}
	}
}
