package wavebrother.enderEnhancement.common.init;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;
import wavebrother.enderEnhancement.Reference;
import wavebrother.enderEnhancement.common.item.EnderArmor;
import wavebrother.enderEnhancement.common.item.EnderPearl;
import wavebrother.enderEnhancement.common.item.EnderStick;
import wavebrother.enderEnhancement.common.item.EndermanAgitator;
import wavebrother.enderEnhancement.common.item.ItemAccumulator;
import wavebrother.enderEnhancement.common.item.ItemEntityPorter;
import wavebrother.enderEnhancement.common.item.ItemEnderFruit;
import wavebrother.enderEnhancement.common.item.ItemEnderPorter;
import wavebrother.enderEnhancement.common.item.tool.EnderAxe;
import wavebrother.enderEnhancement.common.item.tool.EnderHoe;
import wavebrother.enderEnhancement.common.item.tool.EnderMultiTool;
import wavebrother.enderEnhancement.common.item.tool.EnderPickaxe;
import wavebrother.enderEnhancement.common.item.tool.EnderShovel;
import wavebrother.enderEnhancement.common.item.tool.EnderSword;
import wavebrother.enderEnhancement.common.util.EnderTier;

@EventBusSubscriber(bus = Bus.MOD, modid = Reference.MOD_ID)

public class ModItems {

	// Pearls
	public static Item dullEnderPearl;
	@ObjectHolder("minecraft:ender_pearl")
	public static Item enderPearl;
	public static Item empoweredEnderPearl;
	public static Item extremeEnderPearl;

	// Sticks
	public static Item dullEnderStick;
	public static Item enderStick;
	public static Item empoweredEnderStick;
	public static Item extremeEnderStick;

	// Axes
	public static Item dullEnderAxe;
	public static Item enderAxe;
	public static Item empoweredEnderAxe;
	public static Item extremeEnderAxe;

	// Hoes
	public static Item dullEnderHoe;
	public static Item enderHoe;
	public static Item empoweredEnderHoe;
	public static Item extremeEnderHoe;

	// Pickaxes
	public static Item dullEnderPickaxe;
	public static Item enderPickaxe;
	public static Item empoweredEnderPickaxe;
	public static Item extremeEnderPickaxe;

	// Shovels
	public static Item dullEnderShovel;
	public static Item enderShovel;
	public static Item empoweredEnderShovel;
	public static Item extremeEnderShovel;

	// Swords
	public static Item dullEnderSword;
	public static Item enderSword;
	public static Item empoweredEnderSword;
	public static Item extremeEnderSword;

	// Helmets
	public static Item dullEnderHelmet;
	public static Item enderHelmet;
	public static Item empoweredEnderHelmet;
	public static Item extremeEnderHelmet;

	// Chestplates
	public static Item dullEnderChestplate;
	public static Item enderChestplate;
	public static Item empoweredEnderChestplate;
	public static Item extremeEnderChestplate;

	// Leggings
	public static Item dullEnderLeggings;
	public static Item enderLeggings;
	public static Item empoweredEnderLeggings;
	public static Item extremeEnderLeggings;

	// Boots
	public static Item dullEnderBoots;
	public static Item enderBoots;
	public static Item empoweredEnderBoots;
	public static Item extremeEnderBoots;

	// Multi Tools
	public static Item dullEnderTool;
	public static Item enderTool;
	public static Item empoweredEnderTool;
	public static Item extremeEnderTool;

	// Porters
	public static Item dullEnderPorter;
	public static Item enderPorter;
	public static Item empoweredEnderPorter;
	public static Item extremeEnderPorter;

	// Agitators
	public static Item dullEnderAgitator;
	public static Item enderAgitator;
	public static Item empoweredEnderAgitator;
	public static Item extremeEnderAgitator;

	// Accumulators
	public static Item dullAccumulator;
	public static Item enderAccumulator;
	public static Item empoweredAccumulator;
	public static Item extremeAccumulator;

//	// Endergy Collectors
//	public static Item dullEndergyCollector;
//	public static Item endergyCollector;
//	public static Item empoweredEndergyCollector;
//	public static Item extremeEndergyCollector;

	// Food
	public static Item enderFruit;
	public static Item dullBlockPorter;

	public static void init() {

		// Pearls
		dullEnderPearl = new EnderPearl(EnderTier.DULL, Reference.Items.DULLENDERPEARL.getRegistryName());
		empoweredEnderPearl = new EnderPearl(EnderTier.EMPOWERED,
				Reference.Items.EMPOWEREDENDERPEARL.getRegistryName());
		extremeEnderPearl = new EnderPearl(EnderTier.EXTREME, Reference.Items.EXTREMEENDERPEARL.getRegistryName());

		// Sticks
		dullEnderStick = new EnderStick(EnderTier.DULL, Reference.Items.DULLENDERSTICK.getRegistryName());
		enderStick = new EnderStick(EnderTier.ENDER, Reference.Items.ENDERSTICK.getRegistryName());
		empoweredEnderStick = new EnderStick(EnderTier.EMPOWERED,
				Reference.Items.EMPOWEREDENDERSTICK.getRegistryName());
		extremeEnderStick = new EnderStick(EnderTier.EXTREME, Reference.Items.EXTREMEENDERSTICK.getRegistryName());

		// Axes
		dullEnderAxe = new EnderAxe(EnderTier.DULL, Reference.Items.DULLENDERAXE.getRegistryName());
		enderAxe = new EnderAxe(EnderTier.ENDER, Reference.Items.ENDERAXE.getRegistryName());
		empoweredEnderAxe = new EnderAxe(EnderTier.EMPOWERED, Reference.Items.EMPOWEREDENDERAXE.getRegistryName());
		extremeEnderAxe = new EnderAxe(EnderTier.EXTREME, Reference.Items.EXTREMEENDERAXE.getRegistryName());

		// Hoes
		dullEnderHoe = new EnderHoe(EnderTier.DULL, Reference.Items.DULLENDERHOE.getRegistryName());
		enderHoe = new EnderHoe(EnderTier.ENDER, Reference.Items.ENDERHOE.getRegistryName());
		empoweredEnderHoe = new EnderHoe(EnderTier.EMPOWERED, Reference.Items.EMPOWEREDENDERHOE.getRegistryName());
		extremeEnderHoe = new EnderHoe(EnderTier.EXTREME, Reference.Items.EXTREMEENDERHOE.getRegistryName());

		// Pickaxes
		dullEnderPickaxe = new EnderPickaxe(EnderTier.DULL, Reference.Items.DULLENDERPICKAXE.getRegistryName());
		enderPickaxe = new EnderPickaxe(EnderTier.ENDER, Reference.Items.ENDERPICKAXE.getRegistryName());
		empoweredEnderPickaxe = new EnderPickaxe(EnderTier.EMPOWERED,
				Reference.Items.EMPOWEREDENDERPICKAXE.getRegistryName());
		extremeEnderPickaxe = new EnderPickaxe(EnderTier.EXTREME,
				Reference.Items.EXTREMEENDERPICKAXE.getRegistryName());

		// Shovels
		dullEnderShovel = new EnderShovel(EnderTier.DULL, Reference.Items.DULLENDERSHOVEL.getRegistryName());
		enderShovel = new EnderShovel(EnderTier.ENDER, Reference.Items.ENDERSHOVEL.getRegistryName());
		empoweredEnderShovel = new EnderShovel(EnderTier.EMPOWERED,
				Reference.Items.EMPOWEREDENDERSHOVEL.getRegistryName());
		extremeEnderShovel = new EnderShovel(EnderTier.EXTREME, Reference.Items.EXTREMEENDERSHOVEL.getRegistryName());

		// Swords
		dullEnderSword = new EnderSword(EnderTier.DULL, Reference.Items.DULLENDERSWORD.getRegistryName());
		enderSword = new EnderSword(EnderTier.ENDER, Reference.Items.ENDERSWORD.getRegistryName());
		empoweredEnderSword = new EnderSword(EnderTier.EMPOWERED,
				Reference.Items.EMPOWEREDENDERSWORD.getRegistryName());
		extremeEnderSword = new EnderSword(EnderTier.EXTREME, Reference.Items.EXTREMEENDERSWORD.getRegistryName());

		// Helmets
		dullEnderHelmet = new EnderArmor(EnderTier.DULL, EquipmentSlotType.HEAD,
				Reference.Items.DULLENDERHELMET.getRegistryName());
		enderHelmet = new EnderArmor(EnderTier.ENDER, EquipmentSlotType.HEAD,
				Reference.Items.ENDERHELMET.getRegistryName());
		empoweredEnderHelmet = new EnderArmor(EnderTier.EMPOWERED, EquipmentSlotType.HEAD,
				Reference.Items.EMPOWEREDENDERHELMET.getRegistryName());
		extremeEnderHelmet = new EnderArmor(EnderTier.EXTREME, EquipmentSlotType.HEAD,
				Reference.Items.EXTREMEENDERHELMET.getRegistryName());

		// Chestplates
		dullEnderChestplate = new EnderArmor(EnderTier.DULL, EquipmentSlotType.CHEST,
				Reference.Items.DULLENDERCHESTPLATE.getRegistryName());
		enderChestplate = new EnderArmor(EnderTier.ENDER, EquipmentSlotType.CHEST,
				Reference.Items.ENDERCHESTPLATE.getRegistryName());
		empoweredEnderChestplate = new EnderArmor(EnderTier.EMPOWERED, EquipmentSlotType.CHEST,
				Reference.Items.EMPOWEREDENDERCHESTPLATE.getRegistryName());
		extremeEnderChestplate = new EnderArmor(EnderTier.EXTREME, EquipmentSlotType.CHEST,
				Reference.Items.EXTREMEENDERCHESTPLATE.getRegistryName());

		// Leggings
		dullEnderLeggings = new EnderArmor(EnderTier.DULL, EquipmentSlotType.LEGS,
				Reference.Items.DULLENDERLEGGINGS.getRegistryName());
		enderLeggings = new EnderArmor(EnderTier.ENDER, EquipmentSlotType.LEGS,
				Reference.Items.ENDERLEGGINGS.getRegistryName());
		empoweredEnderLeggings = new EnderArmor(EnderTier.EMPOWERED, EquipmentSlotType.LEGS,
				Reference.Items.EMPOWEREDENDERLEGGINGS.getRegistryName());
		extremeEnderLeggings = new EnderArmor(EnderTier.EXTREME, EquipmentSlotType.LEGS,
				Reference.Items.EXTREMEENDERLEGGINGS.getRegistryName());

		// Boots
		dullEnderBoots = new EnderArmor(EnderTier.DULL, EquipmentSlotType.FEET,
				Reference.Items.DULLENDERBOOTS.getRegistryName());
		enderBoots = new EnderArmor(EnderTier.ENDER, EquipmentSlotType.FEET,
				Reference.Items.ENDERBOOTS.getRegistryName());
		empoweredEnderBoots = new EnderArmor(EnderTier.EMPOWERED, EquipmentSlotType.FEET,
				Reference.Items.EMPOWEREDENDERBOOTS.getRegistryName());
		extremeEnderBoots = new EnderArmor(EnderTier.EXTREME, EquipmentSlotType.FEET,
				Reference.Items.EXTREMEENDERBOOTS.getRegistryName());

		// Multi Tools
		dullEnderTool = new EnderMultiTool(EnderTier.DULL, Reference.Items.DULLENDERTOOL.getRegistryName());
		enderTool = new EnderMultiTool(EnderTier.ENDER, Reference.Items.ENDERTOOL.getRegistryName());
		empoweredEnderTool = new EnderMultiTool(EnderTier.EMPOWERED,
				Reference.Items.EMPOWEREDENDERTOOL.getRegistryName());
		extremeEnderTool = new EnderMultiTool(EnderTier.EXTREME, Reference.Items.EXTREMEENDERTOOL.getRegistryName());

		// Porters
		dullEnderPorter = new ItemEnderPorter(EnderTier.DULL, Reference.Items.DULLENDERPORTER.getRegistryName());
		enderPorter = new ItemEnderPorter(EnderTier.ENDER, Reference.Items.ENDERPORTER.getRegistryName());
		empoweredEnderPorter = new ItemEnderPorter(EnderTier.EMPOWERED,
				Reference.Items.EMPOWEREDENDERPORTER.getRegistryName());
		extremeEnderPorter = new ItemEnderPorter(EnderTier.EXTREME,
				Reference.Items.EXTREMEENDERPORTER.getRegistryName());

		// Accumulators
		dullAccumulator = new ItemAccumulator(EnderTier.DULL, Reference.Items.DULLITEMACCUMULATOR.getRegistryName());
		enderAccumulator = new ItemAccumulator(EnderTier.ENDER, Reference.Items.ENDERITEMACCUMULATOR.getRegistryName());
		empoweredAccumulator = new ItemAccumulator(EnderTier.EMPOWERED,
				Reference.Items.EMPOWEREDITEMACCUMULATOR.getRegistryName());
		extremeAccumulator = new ItemAccumulator(EnderTier.EXTREME,
				Reference.Items.EXTREMEITEMACCUMULATOR.getRegistryName());

		// Agitators
		dullEnderAgitator = new EndermanAgitator(EnderTier.DULL, Reference.Items.DULLENDERAGITATOR.getRegistryName());
		enderAgitator = new EndermanAgitator(EnderTier.ENDER, Reference.Items.ENDERAGITATOR.getRegistryName());
		empoweredEnderAgitator = new EndermanAgitator(EnderTier.EMPOWERED,
				Reference.Items.EMPOWEREDENDERAGITATOR.getRegistryName());
		extremeEnderAgitator = new EndermanAgitator(EnderTier.EXTREME,
				Reference.Items.EXTREMEENDERAGITATOR.getRegistryName());

//		// EndergyCollectors
//		dullEndergyCollector = new EndergyCollector(EnderTier.DULL, Reference.Items.DULLENDERGYCOLLECTOR.getRegistryName());
//		endergyCollector = new EndergyCollector(EnderTier.ENDER, Reference.Items.ENDERGYCOLLECTOR.getRegistryName());
//		empoweredEndergyCollector = new EndergyCollector(EnderTier.EMPOWERED,
//				Reference.Items.EMPOWEREDENDERGYCOLLECTOR.getRegistryName());
//		extremeEndergyCollector = new EndergyCollector(EnderTier.EXTREME, Reference.Items.EXTREMEENDERGYCOLLECTOR.getRegistryName());

		// Food
		enderFruit = new ItemEnderFruit();
		dullBlockPorter = new ItemEntityPorter(EnderTier.DULL, "item_entity_porter");

	}

	@SubscribeEvent
	public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {

		init();
		// Pearls
		event.getRegistry().registerAll(
				// Dull
				dullEnderPearl, dullEnderStick, dullEnderAxe, dullEnderHoe, dullEnderPickaxe, dullEnderShovel,
				dullEnderSword, dullEnderHelmet, dullEnderChestplate, dullEnderLeggings, dullEnderBoots,
				dullEnderAgitator, dullAccumulator,
//				dullEndergyCollector,
				// Ender
				enderStick, enderAxe, enderHoe, enderPickaxe, enderShovel, enderSword, enderHelmet, enderChestplate,
				enderLeggings, enderBoots, enderAgitator, enderAccumulator,
//				endergyCollector,
				// Empowered
				empoweredEnderPearl, empoweredEnderStick, empoweredEnderAxe, empoweredEnderHoe, empoweredEnderPickaxe,
				empoweredEnderShovel, empoweredEnderSword, empoweredEnderHelmet, empoweredEnderChestplate,
				empoweredEnderLeggings, empoweredEnderBoots, empoweredEnderAgitator, empoweredAccumulator,
//				empoweredEndergyCollector,
				// Extreme
				extremeEnderPearl, extremeEnderStick, extremeEnderAxe, extremeEnderHoe, extremeEnderPickaxe,
				extremeEnderShovel, extremeEnderSword, extremeEnderHelmet, extremeEnderChestplate, extremeEnderLeggings,
				extremeEnderBoots, extremeEnderAgitator, extremeAccumulator,
//				extremeEndergyCollector,

				// Multi Tools
				dullEnderTool, enderTool, empoweredEnderTool, extremeEnderTool,

				// Pearlers
				dullEnderPorter, enderPorter, empoweredEnderPorter, extremeEnderPorter,

				// Food
				enderFruit, dullBlockPorter,

				// Blocks
				ModBlocks.dullEnderBlock.blockItem, ModBlocks.enderBlock.blockItem,
				ModBlocks.empoweredEnderBlock.blockItem, ModBlocks.extremeEnderBlock.blockItem,
				ModBlocks.enderPedestal.blockItem);
		// event.getRegistry().registerAll(dullEnderPearl, empoweredEnderPearl,
		// extremeEnderPearl,
//
//				// Sticks
//				dullEnderStick, enderStick, empoweredEnderStick, extremeEnderStick,
//
//				// Axes
//				dullEnderAxe, enderAxe, empoweredEnderAxe, extremeEnderAxe,
//
//				// Hoes
//				dullEnderHoe, enderHoe, empoweredEnderHoe, extremeEnderHoe,
//
//				// Pickaxes
//				dullEnderPickaxe, enderPickaxe, empoweredEnderPickaxe, extremeEnderPickaxe,
//
//				// Shovels
//				dullEnderShovel, enderShovel, empoweredEnderShovel, extremeEnderShovel,
//
//				// Swords
//				dullEnderSword, enderSword, empoweredEnderSword, extremeEnderSword,
//
//				// Helmets
//				dullEnderHelmet, enderHelmet, empoweredEnderHelmet, extremeEnderHelmet,
//
//				// Chestplates
//				dullEnderChestplate, enderChestplate, empoweredEnderChestplate, extremeEnderChestplate,
//
//				// Leggings
//				dullEnderLeggings, enderLeggings, empoweredEnderLeggings, extremeEnderLeggings,
//
//				// Boots
//				dullEnderBoots, enderBoots, empoweredEnderBoots, extremeEnderBoots,
//
//				// Misc Tools
//				extremeEnderTool, extremeEnderPorter,
//
//				// Agitators
//				dullEnderAgitator, enderAgitator, empoweredEnderAgitator, extremeEnderAgitator,
//
//				// Accumulators
//				dullAccumulator, enderAccumulator, empoweredAccumulator, extremeAccumulator,
//
//				// Food
//				enderFruit,
//
//				// Blocks
//				ModBlocks.dullEnderBlock.blockItem, ModBlocks.enderBlock.blockItem,
//				ModBlocks.empoweredEnderBlock.blockItem, ModBlocks.extremeEnderBlock.blockItem,
//				ModBlocks.enderPedestal.blockItem);
	}

}
