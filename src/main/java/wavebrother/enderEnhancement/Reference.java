package wavebrother.enderEnhancement;

public class Reference {

	public static final String MOD_ID = "enderenhancement";
	public static final String NAME = "Ender Enhancement";
	public static final String VERSION = "0.0.0.1";
	public static final String ACCEPTED_VERSIONS = "[1.14.4]";

	public static final String CLIENT_PROXY_CLASS = "wavebrother.enderEnhancement.proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = "wavebrother.enderEnhancement.proxy.CommonProxy";

	public static enum Items {

		// Basic Crafting Ingredients
		DULLENDERPEARL("item_dull_ender_pearl"), EMPOWEREDENDERPEARL("item_empowered_ender_pearl"),
		EXTREMEENDERPEARL("item_extreme_ender_pearl"),

		// Sticks
		DULLENDERSTICK("item_dull_ender_stick"), ENDERSTICK("item_ender_stick"),
		EMPOWEREDENDERSTICK("item_empowered_ender_stick"), EXTREMEENDERSTICK("item_extreme_ender_stick"),

		// Axes
		DULLENDERAXE("item_dull_ender_axe"), ENDERAXE("item_ender_axe"), EMPOWEREDENDERAXE("item_empowered_ender_axe"),
		EXTREMEENDERAXE("item_extreme_ender_axe"),

		// Hoes
		DULLENDERHOE("item_dull_ender_hoe"), ENDERHOE("item_ender_hoe"), EMPOWEREDENDERHOE("item_empowered_ender_hoe"),
		EXTREMEENDERHOE("item_extreme_ender_hoe"),

		// Pickaxes
		DULLENDERPICKAXE("item_dull_ender_pickaxe"), ENDERPICKAXE("item_ender_pickaxe"),
		EMPOWEREDENDERPICKAXE("item_empowered_ender_pickaxe"), EXTREMEENDERPICKAXE("item_extreme_ender_pickaxe"),

		// Shovels
		DULLENDERSHOVEL("item_dull_ender_shovel"), ENDERSHOVEL("item_ender_shovel"),
		EMPOWEREDENDERSHOVEL("item_empowered_ender_shovel"), EXTREMEENDERSHOVEL("item_extreme_ender_shovel"),

		// Swords
		DULLENDERSWORD("item_dull_ender_sword"), ENDERSWORD("item_ender_sword"),
		EMPOWEREDENDERSWORD("item_empowered_ender_sword"), EXTREMEENDERSWORD("item_extreme_ender_sword"),

		// Helmets
		DULLENDERHELMET("item_dull_ender_helmet"), ENDERHELMET("item_ender_helmet"),
		EMPOWEREDENDERHELMET("item_empowered_ender_helmet"), EXTREMEENDERHELMET("item_extreme_ender_helmet"),

		// Chestplates
		DULLENDERCHESTPLATE("item_dull_ender_chestplate"), ENDERCHESTPLATE("item_ender_chestplate"),
		EMPOWEREDENDERCHESTPLATE("item_empowered_ender_chestplate"),
		EXTREMEENDERCHESTPLATE("item_extreme_ender_chestplate"),

		// Leggings
		DULLENDERLEGGINGS("item_dull_ender_leggings"), ENDERLEGGINGS("item_ender_leggings"),
		EMPOWEREDENDERLEGGINGS("item_empowered_ender_leggings"), EXTREMEENDERLEGGINGS("item_extreme_ender_leggings"),

		// Boots
		DULLENDERBOOTS("item_dull_ender_boots"), ENDERBOOTS("item_ender_boots"),
		EMPOWEREDENDERBOOTS("item_empowered_ender_boots"), EXTREMEENDERBOOTS("item_extreme_ender_boots"),

		// Multi Tools
		DULLENDERTOOL("item_dull_ender_tool"), ENDERTOOL("item_ender_tool"),
		EMPOWEREDENDERTOOL("item_empowered_ender_tool"), EXTREMEENDERTOOL("item_extreme_ender_tool"),

		// Porters
		DULLENDERPORTER("item_dull_ender_porter"), ENDERPORTER("item_ender_pearler"),
		EMPOWEREDENDERPORTER("item_empowered_ender_porter"), EXTREMEENDERPORTER("item_extreme_ender_porter"),

		// Agitators
		DULLENDERAGITATOR("item_dull_enderman_agitator"), ENDERAGITATOR("item_enderman_agitator"),
		EMPOWEREDENDERAGITATOR("item_empowered_enderman_agitator"),
		EXTREMEENDERAGITATOR("item_extreme_enderman_agitator"),

		// Accumulators
		DULLITEMACCUMULATOR("item_dull_item_accumulator"), ENDERITEMACCUMULATOR("item_ender_item_accumulator"),
		EMPOWEREDITEMACCUMULATOR("item_empowered_item_accumulator"),
		EXTREMEITEMACCUMULATOR("item_extreme_item_accumulator"),

		// Endergy Collectors
		DULLENDERGYCOLLECTOR("item_dull_endergy_collector"), ENDERGYCOLLECTOR("item_endergy_collector"),
		EMPOWEREDENDERGYCOLLECTOR("item_empowered_endergy_collector"), EXTREMEENDERGYCOLLECTOR("item_extreme_endergy_collector"),

		// Food
		ENDERFRUIT("item_ender_fruit");

		private String registryName;

		Items(String registryName) {
			this.registryName = registryName;
		}

		public String getRegistryName() {
			return registryName;
		}
	}

	public static enum Blocks {
		DULLENDERBLOCK("block_dull_ender_block"), ENDERBLOCK("block_ender_block"),
		EMPOWEREDENDERBLOCK("block_empowered_ender_block"), EXTREMEENDERBLOCK("block_extreme_ender_block"),
		DULLENDERORE("block_dull_ender_ore"), ENDERORE("block_ender_ore"),
		EMPOWEREDENDERORE("block_empowered_ender_ore"), EXTREMEENDERORE("block_extreme_ender_ore"),
		ENDERPEDESTAL("block_ender_pedestal");

		private String registryName;

		Blocks(String registryName) {
			this.registryName = registryName;
		}

		public String getRegistryName() {
			return registryName;
		}
	}

	public static enum Entities {
		ENDERPEDESTAL("entity_ender_pedestal");

		private String registryName;

		Entities(String registryName) {
			this.registryName = registryName;
		}

		public String getRegistryName() {
			return registryName;
		}
	}

	public static enum TileEntities {
		ENDERPEDESTAL("tileentity_ender_pedestal");

		private String registryName;

		TileEntities(String registryName) {
			this.registryName = registryName;
		}

		public String getRegistryName() {
			return registryName;
		}
	}

	public static enum Capabilities {
		ENDERGYCAPABILITY("capability_endergy");

		private String registryName;

		Capabilities(String registryName) {
			this.registryName = registryName;
		}

		public String getRegistryName() {
			return registryName;
		}
	}
}
