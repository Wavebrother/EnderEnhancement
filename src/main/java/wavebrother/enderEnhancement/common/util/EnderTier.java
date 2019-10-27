package wavebrother.enderEnhancement.common.util;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import wavebrother.enderEnhancement.Config;
import wavebrother.enderEnhancement.Reference;
import wavebrother.enderEnhancement.common.init.ModItems;

public enum EnderTier {
	DULL, ENDER, EMPOWERED, EXTREME;
	public final EnderToolTier toolTier;
	public final EnderArmorMaterial armorMaterial;

	EnderTier() {
		toolTier = new EnderToolTier();
		armorMaterial = new EnderArmorMaterial();
	}

	@Override
	public String toString() {
		return super.toString().substring(0, 1) + super.toString().substring(1).toLowerCase();
	}

	public int defaultMultiplier() {
		switch (this) {
		case DULL:
			return 1;
		case ENDER:
			return 2;
		case EMPOWERED:
			return 3;
		case EXTREME:
			return 4;
		}
		return 1;
	}

	public int multiplier() {
		return Config.ENDER_TIER_MULTIPLIER.get(this).get();
	}

	public Item getEnderPearl() {
		switch (this) {
		case DULL:
			return ModItems.dullEnderPearl;
		case ENDER:
			return ModItems.enderPearl;
		case EMPOWERED:
			return ModItems.empoweredEnderPearl;
		case EXTREME:
			return ModItems.extremeEnderPearl;
		default:
			return null;
		}
	}

	public class EnderToolTier implements IItemTier {

		protected EnderToolTier() {

		}

		public EnderTier enderTier = EnderTier.this;

		public int getMaxUses() {
			switch (EnderTier.this) {
			case DULL:
				return 150;
			case ENDER:
				return 250;
			case EMPOWERED:
				return 500;
			case EXTREME:
				return 1000;
			}
			return 0;
		}

		public float getEfficiency() {
			switch (EnderTier.this) {
			case DULL:
				return 4;
			case ENDER:
				return 8;
			case EMPOWERED:
				return 12;
			case EXTREME:
				return 16;
			}
			return 0;
		}

		public float getAttackDamage() {
			switch (EnderTier.this) {
			case DULL:
				return 1;
			case ENDER:
				return 3;
			case EMPOWERED:
				return 5;
			case EXTREME:
				return 7;
			}
			return 0;
		}

		public int getHarvestLevel() {
			switch (EnderTier.this) {
			case DULL:
				return 1;
			case ENDER:
				return 2;
			case EMPOWERED:
				return 3;
			case EXTREME:
				return 4;
			}
			return 0;
		}

		public int getEnchantability() {
			switch (EnderTier.this) {
			case DULL:
				return 10;
			case ENDER:
				return 17;
			case EMPOWERED:
				return 24;
			case EXTREME:
				return 31;
			}
			return 0;
		}

		public Ingredient getRepairMaterial() {
			switch (EnderTier.this) {
			case DULL:
				return Ingredient.fromItems(ModItems.dullEnderPearl);
			case ENDER:
				return Ingredient.fromItems(ModItems.enderPearl);
			case EMPOWERED:
				return Ingredient.fromItems(ModItems.empoweredEnderPearl);
			case EXTREME:
				return Ingredient.fromItems(ModItems.extremeEnderPearl);
			}
			return Ingredient.fromItems();
		}
	}

	public class EnderArmorMaterial implements IArmorMaterial {
//		DULL(, , 8, , 0.0F, () -> {
//			return Ingredient.fromItems(Items.LEATHER);
//		}), ENDER(, , , 16, , 0.0F, () -> {
//			return Ingredient.fromItems(Items.IRON_INGOT);
//		}),
//		EMPOWERED(, , , 25, , 0.0F, () -> {
//			return Ingredient.fromItems(Items.IRON_INGOT);
//		}),
//		EXTREME(, , , 35, , 2.0F, () -> {
//			return Ingredient.fromItems(Items.DIAMOND);
//		});

		protected EnderArmorMaterial() {
			DEFAULT_WATER_TP_MIN = DEFAULT_WATER_TP_MINS[getTier().ordinal()];
			DEFAULT_ATTACK_TP_MIN = DEFAULT_ATTACK_TP_MINS[getTier().ordinal()];

		}

		private final int[] MAX_DAMAGE_ARRAY = new int[] { 13, 15, 16, 11 };

		private final int[] DEFAULT_WATER_TP_MINS = { 2, 3, 4, 5 };
		private final int[] DEFAULT_ATTACK_TP_MINS = { 4, 3, 2, 1 };
		public final int DEFAULT_WATER_TP_MIN;
		public final int DEFAULT_ATTACK_TP_MIN;

		public EnderTier getTier() {
			return EnderTier.this;
		}

		public int getDurability(EquipmentSlotType slotIn) {
			int maxDamageFactor = 0;
			switch (EnderTier.this) {
			case DULL:
				maxDamageFactor = 7;
				break;
			case ENDER:
				maxDamageFactor = 15;
				break;
			case EMPOWERED:
				maxDamageFactor = 25;
				break;
			case EXTREME:
				maxDamageFactor = 40;
				break;
			}
			return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * maxDamageFactor;
		}

		public int getDamageReductionAmount(EquipmentSlotType slotIn) {
			int[] damageReductionAmountArray = new int[] { 13, 15, 16, 11 };
			switch (EnderTier.this) {
			case DULL:
				damageReductionAmountArray = new int[] { 2, 5, 3, 1 };
				break;
			case ENDER:
				damageReductionAmountArray = new int[] { 2, 6, 4, 2 };
				break;
			case EMPOWERED:
				damageReductionAmountArray = new int[] { 3, 7, 5, 2 };
				break;
			case EXTREME:
				damageReductionAmountArray = new int[] { 3, 8, 6, 3 };
				break;
			}
			return damageReductionAmountArray[slotIn.getIndex()];
		}

		public int getEnchantability() {
			switch (EnderTier.this) {
			case DULL:
				return 8;
			case EMPOWERED:
				return 16;
			case ENDER:
				return 25;
			case EXTREME:
				return 35;
			}
			return 0;
		}

		public SoundEvent getSoundEvent() {
			switch (EnderTier.this) {
			case DULL:
				return SoundEvents.ITEM_ARMOR_EQUIP_LEATHER;
			case ENDER:
				return SoundEvents.ITEM_ARMOR_EQUIP_IRON;
			case EMPOWERED:
				return SoundEvents.ITEM_ARMOR_EQUIP_GOLD;
			case EXTREME:
				return SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
			}
			return SoundEvents.ITEM_ARMOR_EQUIP_GENERIC;
		}

		public Ingredient getRepairMaterial() {
			switch (EnderTier.this) {
			case DULL:
				return Ingredient.fromItems(ModItems.dullEnderPearl);
			case ENDER:
				return Ingredient.fromItems(ModItems.enderPearl);
			case EMPOWERED:
				return Ingredient.fromItems(ModItems.empoweredEnderPearl);
			case EXTREME:
				return Ingredient.fromItems(ModItems.extremeEnderPearl);
			}
			return Ingredient.fromItems();
		}

		public String getName() {
			switch (EnderTier.this) {
			case DULL:
				return Reference.MOD_ID + ":dull_ender";
			case ENDER:
				return Reference.MOD_ID + ":ender";
			case EMPOWERED:
				return Reference.MOD_ID + ":empowered_ender";
			case EXTREME:
				return Reference.MOD_ID + ":extreme_ender";
			}
			return "";
		}

		public float getToughness() {
			switch (EnderTier.this) {
			case EXTREME:
				return 2;
			default:
			}
			return 0;
		}
	}
}
