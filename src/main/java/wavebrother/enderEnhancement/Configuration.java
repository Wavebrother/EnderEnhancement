package wavebrother.enderEnhancement;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RangeInt;
import wavebrother.enderEnhancement.common.util.EnderTier;

@Config(modid = Reference.MOD_ID)
public class Configuration {

	@Comment("Multiplier for Dull tier.")@RangeInt(min = 1, max = 16)
	public static int Dull_Tier = 1;
	@Comment("Multiplier for Ender tier.")@RangeInt(min = 1, max = 16)
	public static int Ender_Tier = 2;
	@Comment("Multiplier for Empowered tier.")@RangeInt(min = 1, max = 16)
	public static int Empowered_Tier = 3;
	@Comment("Multiplier for Extreme tier.")@RangeInt(min = 1, max = 16)
	public static int Extreme_Tier = 4;

	@Comment("Agitator range base (Modified by tier multiplier)")@RangeInt(min = 1, max = 128)
	public static int Agitator_Range = 4;

	@Comment("Accumulator range base (Modified by tier multiplier)")@RangeInt(min = 1, max = 128)
	public static int Accumulator_Range = 4;

	@Comment("Ender Porter range base (Modified by tier multiplier)")@RangeInt(min = 1, max = 128)
	public static int Porter_Range = 4;
	
	public static class EnderArmor{
		@Comment("Range to teleport when hit")@RangeInt(min = 1, max = 128)
		public static int Teleport_Range = 32;

		@Comment({"Minimum number of pieces of Dull armor to cause a water teleport.", "Set to 5 to never cause a water teleport."})@RangeInt(min = 1, max = 5)
		public static int Dull_Tier_Water = EnderTier.DULL.defaultWaterTpMin();
		@Comment({"Minimum number of pieces of Ender armor to cause a water teleport.", "Set to 5 to never cause a water teleport."})@RangeInt(min = 1, max = 5)
		public static int Ender_Tier_Water = EnderTier.ENDER.defaultWaterTpMin();
		@Comment({"Minimum number of pieces of Empowered armor to cause a water teleport.", "Set to 5 to never cause a water teleport."})@RangeInt(min = 1, max = 5)
		public static int Empowered_Tier_Water = EnderTier.EMPOWERED.defaultWaterTpMin();
		@Comment({"Minimum number of pieces of for Extreme armor to cause a water teleport.", "Set to 5 to never cause a water teleport."})@RangeInt(min = 1, max = 5)
		public static int Extreme_Tier_Water = EnderTier.EXTREME.defaultWaterTpMin();

		@Comment({"Minimum number of pieces of Dull armor to cause an attack teleport.", "Set to 5 to never cause a water teleport."})@RangeInt(min = 1, max = 5)
		public static int Dull_Tier_Attack = EnderTier.DULL.defaultAttackTpMin();
		@Comment({"Minimum number of pieces of Ender armor to cause an attack teleport.", "Set to 5 to never cause a water teleport."})@RangeInt(min = 1, max = 5)
		public static int Ender_Tier_Attack = EnderTier.ENDER.defaultAttackTpMin();
		@Comment({"Minimum number of pieces of Empowered armor to cause an attack teleport.", "Set to 5 to never cause a water teleport."})@RangeInt(min = 1, max = 5)
		public static int Empowered_Tier_Attack = EnderTier.EMPOWERED.defaultAttackTpMin();
		@Comment({"Minimum number of pieces of for Extreme armor to cause an attack teleport.", "Set to 5 to never cause a water teleport."})@RangeInt(min = 1, max = 5)
		public static int Extreme_Tier_Attack = EnderTier.EXTREME.defaultAttackTpMin();
		
	}
}
