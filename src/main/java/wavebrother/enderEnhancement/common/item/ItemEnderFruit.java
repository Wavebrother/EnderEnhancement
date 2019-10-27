package wavebrother.enderEnhancement.common.item;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import wavebrother.enderEnhancement.EnderEnhancement;
import wavebrother.enderEnhancement.Reference;

public class ItemEnderFruit extends Item {

	public static int hunger = 16;
	public static float saturation = 1.0F;

	public ItemEnderFruit() {
		super(new Properties().group(EnderEnhancement.CREATIVE_TAB).food(createFruit()));
		setRegistryName(Reference.Items.ENDERFRUIT.getRegistryName());
	}

	private static Food createFruit() {
		Food.Builder foodBuilder = new Food.Builder().hunger(hunger).saturation(saturation)
				.effect(new EffectInstance(Effects.REGENERATION, 600), 1)
				.effect(new EffectInstance(Effects.SPEED, 600), 1)
				.effect(new EffectInstance(Effects.NIGHT_VISION, 1200), 1)
				.effect(new EffectInstance(Effects.STRENGTH, 600), 1);
		return foodBuilder.build();
	}
	// protected void onFoodEaten(ItemStack stack, World World, EntityPlayer Player)
	// {
	// if (!World.isRemote)
	// {
	// Player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 600, 0));
	// Player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 600, 0));
	// Player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 1200, 0));
	// Player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 600, 0));
	// Player.addPotionEffect(new PotionEffect(Potion.field_76443_y.id, 3600, 0));
	// Player.addPotionEffect(new PotionEffect(Potion.field_76444_x.id, 600, 0));
	// Player.addPotionEffect(new PotionEffect(Potion.field_76434_w.id, 600, 0));
	// Player.addPotionEffect(new PotionEffect(Potion.field_76434_w.id, 600, 0));

	// }
	// }

}
