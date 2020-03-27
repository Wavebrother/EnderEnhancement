package wavebrother.enderEnhancement.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import wavebrother.enderEnhancement.EnderEnhancement;
import wavebrother.enderEnhancement.Reference;

public class EnderFruit extends ItemFood {

	public static int hunger = 16;
	public static float saturation = 1.0F;

	public EnderFruit() {
		super(hunger, saturation, false);
		setCreativeTab(EnderEnhancement.CREATIVE_TAB);
		setRegistryName(Reference.Items.ENDERFRUIT.getRegistryName());
		setUnlocalizedName(Reference.Items.ENDERFRUIT.getRegistryName());
	}

	protected void onFoodEaten(ItemStack stack, World World, EntityPlayer Player) {
		if (!World.isRemote) {
			Player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 600, 0));
			Player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 600, 0));
			Player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 1200, 0));

		}
	}

}
