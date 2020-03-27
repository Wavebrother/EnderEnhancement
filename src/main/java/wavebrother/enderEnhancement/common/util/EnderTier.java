package wavebrother.enderEnhancement.common.util;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.util.EnumHelper;
import wavebrother.enderEnhancement.Configuration;
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

	public int defaultAttackTpMin() {
		switch (this) {
		case DULL:
			return 4;
		case ENDER:
			return 3;
		case EMPOWERED:
			return 2;
		case EXTREME:
			return 1;
		}
		return 1;
	}

	public int defaultWaterTpMin() {
		switch (this) {
		case DULL:
			return 2;
		case ENDER:
			return 3;
		case EMPOWERED:
			return 4;
		case EXTREME:
			return 5;
		}
		return 1;
	}

	public int multiplier() {
		switch (this) {
		case DULL:
			return Configuration.Dull_Tier;
		case ENDER:
			return Configuration.Ender_Tier;
		case EMPOWERED:
			return Configuration.Empowered_Tier;
		case EXTREME:
			return Configuration.Extreme_Tier;
		default:
			return 1;
		}
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

	public class EnderToolTier {

		public ToolMaterial material() {
			return EnumHelper
					.addToolMaterial(EnderTier.this.toString(), getHarvestLevel(), getMaxUses(), getEfficiency(),
							getAttackDamage(), getEnchantability())
					.setRepairItem(new ItemStack(getRepairMaterial(), 1,
							net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE));
		}

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

		public Item getRepairMaterial() {
			switch (EnderTier.this) {
			case DULL:
				return ModItems.dullEnderPearl;
			case ENDER:
				return ModItems.enderPearl;
			case EMPOWERED:
				return ModItems.empoweredEnderPearl;
			case EXTREME:
				return ModItems.extremeEnderPearl;
			}
			return null;
		}
	}

	public class EnderArmorMaterial {
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

		public ArmorMaterial material() {
			return EnumHelper
					.addArmorMaterial(getTier().toString(), getName(), getDurability(), MAX_DAMAGE_ARRAY,
							getEnchantability(), getSoundEvent(), getToughness())
					.setRepairItem(new ItemStack(getRepairMaterial(), 1,
							net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE));
		}

		protected EnderArmorMaterial() {

		}

		private final int[] MAX_DAMAGE_ARRAY = new int[] { 13, 15, 16, 11 };

		public EnderTier getTier() {
			return EnderTier.this;
		}

		public int getDurability() {
			switch (EnderTier.this) {
			case DULL:
				return 7;
			case ENDER:
				return 15;
			case EMPOWERED:
				return 25;
			case EXTREME:
				return 40;
			}
			return 1;
		}

		public int[] getDamageReductionAmount(EntityEquipmentSlot slotIn) {
			switch (EnderTier.this) {
			case DULL:
				return new int[] { 2, 5, 3, 1 };
			case ENDER:
				return new int[] { 2, 6, 4, 2 };
			case EMPOWERED:
				return new int[] { 3, 7, 5, 2 };
			case EXTREME:
				return new int[] { 3, 8, 6, 3 };
			}
			return new int[] { 1, 1, 1, 1 };
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

		public Item getRepairMaterial() {
			switch (EnderTier.this) {
			case DULL:
				return ModItems.dullEnderPearl;
			case ENDER:
				return ModItems.enderPearl;
			case EMPOWERED:
				return ModItems.empoweredEnderPearl;
			case EXTREME:
				return ModItems.extremeEnderPearl;
			}
			return null;
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
