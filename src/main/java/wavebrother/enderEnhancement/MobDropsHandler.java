package wavebrother.enderEnhancement;

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import wavebrother.enderEnhancement.common.item.tool.EnderSword;
import wavebrother.enderEnhancement.common.util.EnderTier;

@EventBusSubscriber()
public class MobDropsHandler {

	private static final Random random = new Random();

	@SubscribeEvent
	public void onMobDrops(LivingDropsEvent event) {
		if (event.getEntity() instanceof EntityEnderman
				&& event.getEntityLiving().getEntityData().hasKey(EnderSword.hitTag)) {
			EnderTier tier = EnderTier
					.valueOf(event.getEntityLiving().getEntityData().getString(EnderSword.hitTag));
			if (random.nextDouble() < 1/* / tier.multiplier() */) {
				ItemStack stack = new ItemStack(tier.getEnderPearl());
				EntityItem drop = new EntityItem(event.getEntity().world, event.getEntity().posX,
						event.getEntity().posY, event.getEntity().posZ, stack);

				event.getDrops().add(drop);
			}
		}
	}
}
