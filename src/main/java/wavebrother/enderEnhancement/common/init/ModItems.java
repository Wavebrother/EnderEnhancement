package wavebrother.enderEnhancement.common.init;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import wavebrother.enderEnhancement.Reference;
import wavebrother.enderEnhancement.common.item.EnderArmor;
import wavebrother.enderEnhancement.common.item.EnderFruit;
import wavebrother.enderEnhancement.common.item.EnderPearl;
import wavebrother.enderEnhancement.common.item.EnderPorter;
import wavebrother.enderEnhancement.common.item.EnderStick;
import wavebrother.enderEnhancement.common.item.EndermanAgitator;
import wavebrother.enderEnhancement.common.item.EntityPorter;
import wavebrother.enderEnhancement.common.item.IEnderItem;
import wavebrother.enderEnhancement.common.item.ItemAccumulator;
import wavebrother.enderEnhancement.common.item.tool.EnderAxe;
import wavebrother.enderEnhancement.common.item.tool.EnderHoe;
import wavebrother.enderEnhancement.common.item.tool.EnderMultiTool;
import wavebrother.enderEnhancement.common.item.tool.EnderPickaxe;
import wavebrother.enderEnhancement.common.item.tool.EnderShovel;
import wavebrother.enderEnhancement.common.item.tool.EnderSword;
import wavebrother.enderEnhancement.common.util.EnderTier;

@EventBusSubscriber

public class ModItems {

	// Pearls
	public static EnderPearl dullEnderPearl;
	@ObjectHolder("minecraft:ender_pearl")
	public static Item enderPearl;
	public static EnderPearl empoweredEnderPearl;
	public static EnderPearl extremeEnderPearl;

	// Sticks
	public static EnderStick dullEnderStick;
	public static EnderStick enderStick;
	public static EnderStick empoweredEnderStick;
	public static EnderStick extremeEnderStick;

	// Axes
	public static EnderAxe dullEnderAxe;
	public static EnderAxe enderAxe;
	public static EnderAxe empoweredEnderAxe;
	public static EnderAxe extremeEnderAxe;

	// Hoes
	public static EnderHoe dullEnderHoe;
	public static EnderHoe enderHoe;
	public static EnderHoe empoweredEnderHoe;
	public static EnderHoe extremeEnderHoe;

	// Pickaxes
	public static EnderPickaxe dullEnderPickaxe;
	public static EnderPickaxe enderPickaxe;
	public static EnderPickaxe empoweredEnderPickaxe;
	public static EnderPickaxe extremeEnderPickaxe;

	// Shovels
	public static EnderShovel dullEnderShovel;
	public static EnderShovel enderShovel;
	public static EnderShovel empoweredEnderShovel;
	public static EnderShovel extremeEnderShovel;

	// Swords
	public static EnderSword dullEnderSword;
	public static EnderSword enderSword;
	public static EnderSword empoweredEnderSword;
	public static EnderSword extremeEnderSword;

	// Helmets
	public static EnderArmor dullEnderHelmet;
	public static EnderArmor enderHelmet;
	public static EnderArmor empoweredEnderHelmet;
	public static EnderArmor extremeEnderHelmet;

	// Chestplates
	public static EnderArmor dullEnderChestplate;
	public static EnderArmor enderChestplate;
	public static EnderArmor empoweredEnderChestplate;
	public static EnderArmor extremeEnderChestplate;

	// Leggings
	public static EnderArmor dullEnderLeggings;
	public static EnderArmor enderLeggings;
	public static EnderArmor empoweredEnderLeggings;
	public static EnderArmor extremeEnderLeggings;

	// Boots
	public static EnderArmor dullEnderBoots;
	public static EnderArmor enderBoots;
	public static EnderArmor empoweredEnderBoots;
	public static EnderArmor extremeEnderBoots;

	// Multi Tools
	public static EnderMultiTool dullEnderTool;
	public static EnderMultiTool enderTool;
	public static EnderMultiTool empoweredEnderTool;
	public static EnderMultiTool extremeEnderTool;

	// Porters
	public static EnderPorter dullEnderPorter;
	public static EnderPorter enderPorter;
	public static EnderPorter empoweredEnderPorter;
	public static EnderPorter extremeEnderPorter;

	// Agitators
	public static EndermanAgitator dullEnderAgitator;
	public static EndermanAgitator enderAgitator;
	public static EndermanAgitator empoweredEnderAgitator;
	public static EndermanAgitator extremeEnderAgitator;

	// Accumulators
	public static ItemAccumulator dullAccumulator;
	public static ItemAccumulator enderAccumulator;
	public static ItemAccumulator empoweredAccumulator;
	public static ItemAccumulator extremeAccumulator;

//	// Endergy Collectors
//	public static Item dullEndergyCollector;
//	public static Item endergyCollector;
//	public static Item empoweredEndergyCollector;
//	public static Item extremeEndergyCollector;

	// Food
	public static EnderFruit enderFruit;
	public static EntityPorter dullEntityPorter;

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
		dullEnderHelmet = new EnderArmor(EnderTier.DULL, EntityEquipmentSlot.HEAD,
				Reference.Items.DULLENDERHELMET.getRegistryName());
		enderHelmet = new EnderArmor(EnderTier.ENDER, EntityEquipmentSlot.HEAD,
				Reference.Items.ENDERHELMET.getRegistryName());
		empoweredEnderHelmet = new EnderArmor(EnderTier.EMPOWERED, EntityEquipmentSlot.HEAD,
				Reference.Items.EMPOWEREDENDERHELMET.getRegistryName());
		extremeEnderHelmet = new EnderArmor(EnderTier.EXTREME, EntityEquipmentSlot.HEAD,
				Reference.Items.EXTREMEENDERHELMET.getRegistryName());

		// Chestplates
		dullEnderChestplate = new EnderArmor(EnderTier.DULL, EntityEquipmentSlot.CHEST,
				Reference.Items.DULLENDERCHESTPLATE.getRegistryName());
		enderChestplate = new EnderArmor(EnderTier.ENDER, EntityEquipmentSlot.CHEST,
				Reference.Items.ENDERCHESTPLATE.getRegistryName());
		empoweredEnderChestplate = new EnderArmor(EnderTier.EMPOWERED, EntityEquipmentSlot.CHEST,
				Reference.Items.EMPOWEREDENDERCHESTPLATE.getRegistryName());
		extremeEnderChestplate = new EnderArmor(EnderTier.EXTREME, EntityEquipmentSlot.CHEST,
				Reference.Items.EXTREMEENDERCHESTPLATE.getRegistryName());

		// Leggings
		dullEnderLeggings = new EnderArmor(EnderTier.DULL, EntityEquipmentSlot.LEGS,
				Reference.Items.DULLENDERLEGGINGS.getRegistryName());
		enderLeggings = new EnderArmor(EnderTier.ENDER, EntityEquipmentSlot.LEGS,
				Reference.Items.ENDERLEGGINGS.getRegistryName());
		empoweredEnderLeggings = new EnderArmor(EnderTier.EMPOWERED, EntityEquipmentSlot.LEGS,
				Reference.Items.EMPOWEREDENDERLEGGINGS.getRegistryName());
		extremeEnderLeggings = new EnderArmor(EnderTier.EXTREME, EntityEquipmentSlot.LEGS,
				Reference.Items.EXTREMEENDERLEGGINGS.getRegistryName());

		// Boots
		dullEnderBoots = new EnderArmor(EnderTier.DULL, EntityEquipmentSlot.FEET,
				Reference.Items.DULLENDERBOOTS.getRegistryName());
		enderBoots = new EnderArmor(EnderTier.ENDER, EntityEquipmentSlot.FEET,
				Reference.Items.ENDERBOOTS.getRegistryName());
		empoweredEnderBoots = new EnderArmor(EnderTier.EMPOWERED, EntityEquipmentSlot.FEET,
				Reference.Items.EMPOWEREDENDERBOOTS.getRegistryName());
		extremeEnderBoots = new EnderArmor(EnderTier.EXTREME, EntityEquipmentSlot.FEET,
				Reference.Items.EXTREMEENDERBOOTS.getRegistryName());

		// Multi Tools
		dullEnderTool = new EnderMultiTool(EnderTier.DULL, Reference.Items.DULLENDERTOOL.getRegistryName());
		enderTool = new EnderMultiTool(EnderTier.ENDER, Reference.Items.ENDERTOOL.getRegistryName());
		empoweredEnderTool = new EnderMultiTool(EnderTier.EMPOWERED,
				Reference.Items.EMPOWEREDENDERTOOL.getRegistryName());
		extremeEnderTool = new EnderMultiTool(EnderTier.EXTREME, Reference.Items.EXTREMEENDERTOOL.getRegistryName());

		// Porters
		dullEnderPorter = new EnderPorter(EnderTier.DULL, Reference.Items.DULLENDERPORTER.getRegistryName());
		enderPorter = new EnderPorter(EnderTier.ENDER, Reference.Items.ENDERPORTER.getRegistryName());
		empoweredEnderPorter = new EnderPorter(EnderTier.EMPOWERED,
				Reference.Items.EMPOWEREDENDERPORTER.getRegistryName());
		extremeEnderPorter = new EnderPorter(EnderTier.EXTREME, Reference.Items.EXTREMEENDERPORTER.getRegistryName());

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
		enderFruit = new EnderFruit();
		dullEntityPorter = new EntityPorter(EnderTier.DULL, "item_entity_porter");

	}

	@SubscribeEvent
	public static void onModelRegistry(final ModelRegistryEvent event) {
		IEnderItem.registerModel(dullEnderPearl);
		IEnderItem.registerModel(empoweredEnderPearl);
		IEnderItem.registerModel(extremeEnderPearl);

		IEnderItem.registerModel(dullEnderStick);
		IEnderItem.registerModel(enderStick);
		IEnderItem.registerModel(empoweredEnderStick);
		IEnderItem.registerModel(extremeEnderStick);

		IEnderItem.registerModel(dullEnderAxe);
		IEnderItem.registerModel(enderAxe);
		IEnderItem.registerModel(empoweredEnderAxe);
		IEnderItem.registerModel(extremeEnderAxe);

		IEnderItem.registerModel(dullEnderHoe);
		IEnderItem.registerModel(enderHoe);
		IEnderItem.registerModel(empoweredEnderHoe);
		IEnderItem.registerModel(extremeEnderHoe);

		IEnderItem.registerModel(dullEnderPickaxe);
		IEnderItem.registerModel(enderPickaxe);
		IEnderItem.registerModel(empoweredEnderPickaxe);
		IEnderItem.registerModel(extremeEnderPickaxe);

		IEnderItem.registerModel(dullEnderShovel);
		IEnderItem.registerModel(enderShovel);
		IEnderItem.registerModel(empoweredEnderShovel);
		IEnderItem.registerModel(extremeEnderShovel);

		IEnderItem.registerModel(dullEnderSword);
		IEnderItem.registerModel(enderSword);
		IEnderItem.registerModel(empoweredEnderSword);
		IEnderItem.registerModel(extremeEnderSword);

		IEnderItem.registerModel(dullEnderHelmet);
		IEnderItem.registerModel(enderHelmet);
		IEnderItem.registerModel(empoweredEnderHelmet);
		IEnderItem.registerModel(extremeEnderHelmet);

		IEnderItem.registerModel(dullEnderChestplate);
		IEnderItem.registerModel(enderChestplate);
		IEnderItem.registerModel(empoweredEnderChestplate);
		IEnderItem.registerModel(extremeEnderChestplate);

		IEnderItem.registerModel(dullEnderLeggings);
		IEnderItem.registerModel(enderLeggings);
		IEnderItem.registerModel(empoweredEnderLeggings);
		IEnderItem.registerModel(extremeEnderLeggings);

		IEnderItem.registerModel(dullEnderBoots);
		IEnderItem.registerModel(enderBoots);
		IEnderItem.registerModel(empoweredEnderBoots);
		IEnderItem.registerModel(extremeEnderBoots);

		IEnderItem.registerModel(dullEnderTool);
		IEnderItem.registerModel(enderTool);
		IEnderItem.registerModel(empoweredEnderTool);
		IEnderItem.registerModel(extremeEnderTool);

		IEnderItem.registerModel(dullEnderPorter);
		IEnderItem.registerModel(enderPorter);
		IEnderItem.registerModel(empoweredEnderPorter);
		IEnderItem.registerModel(extremeEnderPorter);

		IEnderItem.registerModel(dullAccumulator);
		IEnderItem.registerModel(enderAccumulator);
		IEnderItem.registerModel(empoweredAccumulator);
		IEnderItem.registerModel(extremeAccumulator);

		IEnderItem.registerModel(dullEnderAgitator);
		IEnderItem.registerModel(enderAgitator);
		IEnderItem.registerModel(empoweredEnderAgitator);
		IEnderItem.registerModel(extremeEnderAgitator);

		IEnderItem.registerModel(enderFruit);
		IEnderItem.registerModel(dullEntityPorter);

		IEnderItem.registerModel(ModBlocks.dullEnderBlock.blockItem);
		IEnderItem.registerModel(ModBlocks.enderBlock.blockItem);
		IEnderItem.registerModel(ModBlocks.empoweredEnderBlock.blockItem);
		IEnderItem.registerModel(ModBlocks.extremeEnderBlock.blockItem);

		IEnderItem.registerModel(ModBlocks.enderPedestal.blockItem);
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
				enderFruit, dullEntityPorter,

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
