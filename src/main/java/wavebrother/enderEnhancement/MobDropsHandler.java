package wavebrother.enderEnhancement;

import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(bus = Bus.FORGE)
public class MobDropsHandler {

//	private static final Random random = new Random();
//
//	@SubscribeEvent
//	public void onMobDrops(LivingDropsEvent event) {
//		if (event.getEntity() instanceof EndermanEntity
//				&& event.getEntityLiving().getPersistentData().contains(EnderSword.hitTag)) {
//			EnderTier tier = EnderTier
//					.valueOf(event.getEntityLiving().getPersistentData().getString(EnderSword.hitTag));
//			if (random.nextDouble() < 1/* / tier.multiplier() */) {
//				ItemStack stack = new ItemStack(tier.getEnderPearl());
//				ItemEntity drop = new ItemEntity(event.getEntity().world, event.getEntity().posX,
//						event.getEntity().posY, event.getEntity().posZ, stack);
//
//				event.getDrops().add(drop);
//			}
//		}
//	}
}
