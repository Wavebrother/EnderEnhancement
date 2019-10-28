package wavebrother.enderEnhancement;

import java.nio.file.Path;
import java.util.HashMap;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import wavebrother.enderEnhancement.common.util.EnderTier;

@Mod.EventBusSubscriber
public class Config {

	public static final String CATEGORY_GENERAL = "general";
	public static final String CATEGORY_ENDER_ARMOR = "ender_armor";
	public static final String CATEGORY_ENDER_ARMOR_WATER = "water";
	public static final String CATEGORY_ENDER_ARMOR_ATTACK = "attack";

	private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
	private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

	public static ForgeConfigSpec COMMON_CONFIG;
	public static ForgeConfigSpec CLIENT_CONFIG;

	public static ForgeConfigSpec.BooleanValue HARDMODE;
	public static HashMap<EnderTier, ForgeConfigSpec.IntValue> ENDER_TIER_MULTIPLIER = new HashMap<EnderTier, ForgeConfigSpec.IntValue>();

	public static ForgeConfigSpec.IntValue AGITATOR_RANGE;
	public static ForgeConfigSpec.IntValue PORTER_RANGE;
	public static ForgeConfigSpec.IntValue ACCUMULATOR_RANGE;
	public static ForgeConfigSpec.IntValue ENDERGY_MAX;
	// public static ForgeConfigSpec.BooleanValue AGITATOR_RANGE_TIER_MODIFY;

	public static ForgeConfigSpec.IntValue ENDER_ARMOR_TELEPORT_RANGE;

	public static HashMap<EnderTier, ForgeConfigSpec.IntValue> ENDER_ARMOR_WATER_MINIMUM = new HashMap<EnderTier, ForgeConfigSpec.IntValue>();
	public static HashMap<EnderTier, ForgeConfigSpec.IntValue> ENDER_ARMOR_ATTACK_MINIMUM = new HashMap<EnderTier, ForgeConfigSpec.IntValue>();

	static {

		COMMON_BUILDER.comment("General settings").push(CATEGORY_GENERAL);
//        HARDMODE = COMMON_BUILDER.comment("Enable hardmode recipes (For modpacks) ~Not yet implemented~").define("hardmode", false);
		for (EnderTier tier : EnderTier.values()) {
			ENDER_TIER_MULTIPLIER.put(tier, COMMON_BUILDER.comment("Multiplier for " + tier + " tier.")
					.defineInRange(tier.toString().toLowerCase(), tier.defaultMultiplier(), 1, 16));
		}
		AGITATOR_RANGE = COMMON_BUILDER.comment("Agitator range base (Modified by tier multiplier)")
				.defineInRange("agitator_range", 4, 1, 128);
		ACCUMULATOR_RANGE = COMMON_BUILDER.comment("Accumulator range base (Modified by tier multiplier)")
				.defineInRange("accumulator_range", 4, 1, 128);
		PORTER_RANGE = COMMON_BUILDER.comment("Ender Porter range base (Modified by tier multiplier)")
				.defineInRange("porter_range", 25, 10, 128);
		ENDERGY_MAX = COMMON_BUILDER.comment("Maximum Endergy Storage")
				.defineInRange("endergy_storage", 1000, 1, Integer.MAX_VALUE);
		COMMON_BUILDER.pop();

		COMMON_BUILDER.comment("Ender Armor Settings").push(CATEGORY_ENDER_ARMOR);
		ENDER_ARMOR_TELEPORT_RANGE = COMMON_BUILDER.comment("Range to teleport").defineInRange("teleport_range", 32, 1,
				128);
		COMMON_BUILDER.comment("Water Settings").push(CATEGORY_ENDER_ARMOR_WATER);
		for (EnderTier tier : EnderTier.values()) {
			ENDER_ARMOR_WATER_MINIMUM.put(tier, COMMON_BUILDER
					.comment("Minimum number of pieces of " + tier
							+ " armor to cause a water teleport.\nSet to 5 to never cause a water teleport.")
					.defineInRange(tier.toString().toLowerCase(), tier.armorMaterial.DEFAULT_WATER_TP_MIN, 1, 5));
		}
		COMMON_BUILDER.pop();
		COMMON_BUILDER.comment("Attack Settings").push(CATEGORY_ENDER_ARMOR_ATTACK);
		for (EnderTier tier : EnderTier.values()) {
			ENDER_ARMOR_ATTACK_MINIMUM.put(tier, COMMON_BUILDER
					.comment("Minimum number of pieces of " + tier
							+ " armor to cause a attack teleport.\nSet to 5 to never cause a attack teleport.")
					.defineInRange(tier.toString().toLowerCase(), tier.armorMaterial.DEFAULT_ATTACK_TP_MIN, 1, 5));
		}
		COMMON_BUILDER.pop();
		COMMON_BUILDER.pop();

		COMMON_CONFIG = COMMON_BUILDER.build();
		CLIENT_CONFIG = CLIENT_BUILDER.build();
	}

	public static void loadConfig(ForgeConfigSpec spec, Path path) {

		final CommentedFileConfig configData = CommentedFileConfig.builder(path).sync().autosave()
				.writingMode(WritingMode.REPLACE).build();

		configData.load();
		spec.setConfig(configData);
	}

	@SubscribeEvent
	public static void onLoad(final ModConfig.Loading configEvent) {

	}

	@SubscribeEvent
	public static void onReload(final ModConfig.ConfigReloading configEvent) {
	}

}
