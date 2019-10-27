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
import wavebrother.enderEnhancement.common.item.ItemEnderFruit;
import wavebrother.enderEnhancement.common.item.ItemEnderPearler;
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

	// Misc Tools
	public static Item extremeEnderTool;
	public static Item enderPearler;

	// Agitators
	public static Item dullEnderAgitator;
	public static Item enderAgitator;
	public static Item empoweredEnderAgitator;
	public static Item extremeEnderAgitator;

	// Food
	public static Item enderFruit;

	public static void init() {

		// Pearls
		dullEnderPearl = new EnderPearl(EnderTier.DULL,
				Reference.EnderEnhancementItems.DULLENDERPEARL.getRegistryName());
		empoweredEnderPearl = new EnderPearl(EnderTier.EMPOWERED,
				Reference.EnderEnhancementItems.EMPOWEREDENDERPEARL.getRegistryName());
		extremeEnderPearl = new EnderPearl(EnderTier.EXTREME,
				Reference.EnderEnhancementItems.EXTREMEENDERPEARL.getRegistryName());

		// Sticks
		dullEnderStick = new EnderStick(EnderTier.DULL,
				Reference.EnderEnhancementItems.DULLENDERSTICK.getRegistryName());
		enderStick = new EnderStick(EnderTier.ENDER, Reference.EnderEnhancementItems.ENDERSTICK.getRegistryName());
		empoweredEnderStick = new EnderStick(EnderTier.EMPOWERED,
				Reference.EnderEnhancementItems.EMPOWEREDENDERSTICK.getRegistryName());
		extremeEnderStick = new EnderStick(EnderTier.EXTREME,
				Reference.EnderEnhancementItems.EXTREMEENDERSTICK.getRegistryName());

		// Axes
		dullEnderAxe = new EnderAxe(EnderTier.DULL, Reference.EnderEnhancementItems.DULLENDERAXE.getRegistryName());
		enderAxe = new EnderAxe(EnderTier.ENDER, Reference.EnderEnhancementItems.ENDERAXE.getRegistryName());
		empoweredEnderAxe = new EnderAxe(EnderTier.EMPOWERED,
				Reference.EnderEnhancementItems.EMPOWEREDENDERAXE.getRegistryName());
		extremeEnderAxe = new EnderAxe(EnderTier.EXTREME,
				Reference.EnderEnhancementItems.EXTREMEENDERAXE.getRegistryName());

		// Hoes
		dullEnderHoe = new EnderHoe(EnderTier.DULL, Reference.EnderEnhancementItems.DULLENDERHOE.getRegistryName());
		enderHoe = new EnderHoe(EnderTier.ENDER, Reference.EnderEnhancementItems.ENDERHOE.getRegistryName());
		empoweredEnderHoe = new EnderHoe(EnderTier.EMPOWERED,
				Reference.EnderEnhancementItems.EMPOWEREDENDERHOE.getRegistryName());
		extremeEnderHoe = new EnderHoe(EnderTier.EXTREME,
				Reference.EnderEnhancementItems.EXTREMEENDERHOE.getRegistryName());

		// Pickaxes
		dullEnderPickaxe = new EnderPickaxe(EnderTier.DULL,
				Reference.EnderEnhancementItems.DULLENDERPICKAXE.getRegistryName());
		enderPickaxe = new EnderPickaxe(EnderTier.ENDER,
				Reference.EnderEnhancementItems.ENDERPICKAXE.getRegistryName());
		empoweredEnderPickaxe = new EnderPickaxe(EnderTier.EMPOWERED,
				Reference.EnderEnhancementItems.EMPOWEREDENDERPICKAXE.getRegistryName());
		extremeEnderPickaxe = new EnderPickaxe(EnderTier.EXTREME,
				Reference.EnderEnhancementItems.EXTREMEENDERPICKAXE.getRegistryName());

		// Shovels
		dullEnderShovel = new EnderShovel(EnderTier.DULL,
				Reference.EnderEnhancementItems.DULLENDERSHOVEL.getRegistryName());
		enderShovel = new EnderShovel(EnderTier.ENDER, Reference.EnderEnhancementItems.ENDERSHOVEL.getRegistryName());
		empoweredEnderShovel = new EnderShovel(EnderTier.EMPOWERED,
				Reference.EnderEnhancementItems.EMPOWEREDENDERSHOVEL.getRegistryName());
		extremeEnderShovel = new EnderShovel(EnderTier.EXTREME,
				Reference.EnderEnhancementItems.EXTREMEENDERSHOVEL.getRegistryName());

		// Swords
		dullEnderSword = new EnderSword(EnderTier.DULL,
				Reference.EnderEnhancementItems.DULLENDERSWORD.getRegistryName());
		enderSword = new EnderSword(EnderTier.ENDER, Reference.EnderEnhancementItems.ENDERSWORD.getRegistryName());
		empoweredEnderSword = new EnderSword(EnderTier.EMPOWERED,
				Reference.EnderEnhancementItems.EMPOWEREDENDERSWORD.getRegistryName());
		extremeEnderSword = new EnderSword(EnderTier.EXTREME,
				Reference.EnderEnhancementItems.EXTREMEENDERSWORD.getRegistryName());

		// Helmets
		dullEnderHelmet = new EnderArmor(EnderTier.DULL, EquipmentSlotType.HEAD,
				Reference.EnderEnhancementItems.DULLENDERHELMET.getRegistryName());
		enderHelmet = new EnderArmor(EnderTier.ENDER, EquipmentSlotType.HEAD,
				Reference.EnderEnhancementItems.ENDERHELMET.getRegistryName());
		empoweredEnderHelmet = new EnderArmor(EnderTier.EMPOWERED, EquipmentSlotType.HEAD,
				Reference.EnderEnhancementItems.EMPOWEREDENDERHELMET.getRegistryName());
		extremeEnderHelmet = new EnderArmor(EnderTier.EXTREME, EquipmentSlotType.HEAD,
				Reference.EnderEnhancementItems.EXTREMEENDERHELMET.getRegistryName());

		// Chestplates
		dullEnderChestplate = new EnderArmor(EnderTier.DULL, EquipmentSlotType.CHEST,
				Reference.EnderEnhancementItems.DULLENDERCHESTPLATE.getRegistryName());
		enderChestplate = new EnderArmor(EnderTier.ENDER, EquipmentSlotType.CHEST,
				Reference.EnderEnhancementItems.ENDERCHESTPLATE.getRegistryName());
		empoweredEnderChestplate = new EnderArmor(EnderTier.EMPOWERED, EquipmentSlotType.CHEST,
				Reference.EnderEnhancementItems.EMPOWEREDENDERCHESTPLATE.getRegistryName());
		extremeEnderChestplate = new EnderArmor(EnderTier.EXTREME, EquipmentSlotType.CHEST,
				Reference.EnderEnhancementItems.EXTREMEENDERCHESTPLATE.getRegistryName());

		// Leggings
		dullEnderLeggings = new EnderArmor(EnderTier.DULL, EquipmentSlotType.LEGS,
				Reference.EnderEnhancementItems.DULLENDERLEGGINGS.getRegistryName());
		enderLeggings = new EnderArmor(EnderTier.ENDER, EquipmentSlotType.LEGS,
				Reference.EnderEnhancementItems.ENDERLEGGINGS.getRegistryName());
		empoweredEnderLeggings = new EnderArmor(EnderTier.EMPOWERED, EquipmentSlotType.LEGS,
				Reference.EnderEnhancementItems.EMPOWEREDENDERLEGGINGS.getRegistryName());
		extremeEnderLeggings = new EnderArmor(EnderTier.EXTREME, EquipmentSlotType.LEGS,
				Reference.EnderEnhancementItems.EXTREMEENDERLEGGINGS.getRegistryName());

		// Boots
		dullEnderBoots = new EnderArmor(EnderTier.DULL, EquipmentSlotType.FEET,
				Reference.EnderEnhancementItems.DULLENDERBOOTS.getRegistryName());
		enderBoots = new EnderArmor(EnderTier.ENDER, EquipmentSlotType.FEET,
				Reference.EnderEnhancementItems.ENDERBOOTS.getRegistryName());
		empoweredEnderBoots = new EnderArmor(EnderTier.EMPOWERED, EquipmentSlotType.FEET,
				Reference.EnderEnhancementItems.EMPOWEREDENDERBOOTS.getRegistryName());
		extremeEnderBoots = new EnderArmor(EnderTier.EXTREME, EquipmentSlotType.FEET,
				Reference.EnderEnhancementItems.EXTREMEENDERBOOTS.getRegistryName());

		// Misc Tools
		extremeEnderTool = new EnderMultiTool(EnderTier.EXTREME,
				Reference.EnderEnhancementItems.EXTREMEENDERTOOL.getRegistryName());
		enderPearler = new ItemEnderPearler();

		// Agitators
		dullEnderAgitator = new EndermanAgitator(EnderTier.DULL,
				Reference.EnderEnhancementItems.DULLENDERAGITATOR.getRegistryName());
		enderAgitator = new EndermanAgitator(EnderTier.ENDER,
				Reference.EnderEnhancementItems.ENDERAGITATOR.getRegistryName());
		empoweredEnderAgitator = new EndermanAgitator(EnderTier.EMPOWERED,
				Reference.EnderEnhancementItems.EMPOWEREDENDERAGITATOR.getRegistryName());
		extremeEnderAgitator = new EndermanAgitator(EnderTier.EXTREME,
				Reference.EnderEnhancementItems.EXTREMEENDERAGITATOR.getRegistryName());

		// Food
		enderFruit = new ItemEnderFruit();

	}

	@SubscribeEvent
	public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {

		init();
//		EnderEnhancement.LOGGER.info("REGISTER THE ITEMS\n\n\n\n\n\n\n\nREGISTER THE ITEMS");
//		System.out.println("REGISTER THE ITEMS\n\n\n\n\n\n\n\nREGISTER THE ITEMS");
		// Pearls
		event.getRegistry().registerAll(dullEnderPearl, empoweredEnderPearl, extremeEnderPearl,

				// Sticks
				dullEnderStick, enderStick, empoweredEnderStick, extremeEnderStick,

				// Axes
				dullEnderAxe, enderAxe, empoweredEnderAxe, extremeEnderAxe,

				// Hoes
				dullEnderHoe, enderHoe, empoweredEnderHoe, extremeEnderHoe,

				// Pickaxes
				dullEnderPickaxe, enderPickaxe, empoweredEnderPickaxe, extremeEnderPickaxe,

				// Shovels
				dullEnderShovel, enderShovel, empoweredEnderShovel, extremeEnderShovel,

				// Swords
				dullEnderSword, enderSword, empoweredEnderSword, extremeEnderSword,

				// Helmets
				dullEnderHelmet, enderHelmet, empoweredEnderHelmet, extremeEnderHelmet,

				// Chestplates
				dullEnderChestplate, enderChestplate, empoweredEnderChestplate, extremeEnderChestplate,

				// Leggings
				dullEnderLeggings, enderLeggings, empoweredEnderLeggings, extremeEnderLeggings,

				// Boots
				dullEnderBoots, enderBoots, empoweredEnderBoots, extremeEnderBoots,

				// Misc Tools
				extremeEnderTool, enderPearler,

				// Agitators
				dullEnderAgitator, enderAgitator, empoweredEnderAgitator, extremeEnderAgitator,

				// Food
				enderFruit,

				// Blocks
				ModBlocks.dullEnderBlock.blockItem, ModBlocks.enderBlock.blockItem,
				ModBlocks.empoweredEnderBlock.blockItem, ModBlocks.extremeEnderBlock.blockItem,
				ModBlocks.enderPedestal.blockItem);

	}
//
//	public static void registerRenders() {
//
//		// Pearls
//		registerRender(dullEnderPearl);
//		registerRender(empoweredEnderPearl);
//		registerRender(extremeEnderPearl);
//
//		// Sticks
//		registerRender(dullEnderStick);
//		registerRender(enderStick);
//		registerRender(empoweredEnderStick);
//		registerRender(extremeEnderStick);
//
//		// Axes
//		registerRender(dullEnderAxe);
//		registerRender(enderAxe);
//		registerRender(empoweredEnderAxe);
//		registerRender(extremeEnderAxe);
//
//		// Hoes
//		registerRender(dullEnderHoe);
//		registerRender(enderHoe);
//		registerRender(empoweredEnderHoe);
//		registerRender(extremeEnderHoe);
//
//		// Pickaxes
//		registerRender(dullEnderPickaxe);
//		registerRender(enderPickaxe);
//		registerRender(empoweredEnderPickaxe);
//		registerRender(extremeEnderPickaxe);
//
//		// Shovels
//		registerRender(dullEnderShovel);
//		registerRender(enderShovel);
//		registerRender(empoweredEnderShovel);
//		registerRender(extremeEnderShovel);
//
//		// Swords
//		registerRender(dullEnderSword);
//		registerRender(enderSword);
//		registerRender(empoweredEnderSword);
//		registerRender(extremeEnderSword);
//
//		// Helmets
//		registerRender(dullEnderHelmet);
//		registerRender(enderHelmet);
//		registerRender(empoweredEnderHelmet);
//		registerRender(extremeEnderHelmet);
//
//		// Chestplates
//		registerRender(dullEnderChestplate);
//		registerRender(enderChestplate);
//		registerRender(empoweredEnderChestplate);
//		registerRender(extremeEnderChestplate);
//
//		// Leggings
//		registerRender(dullEnderLeggings);
//		registerRender(enderLeggings);
//		registerRender(empoweredEnderLeggings);
//		registerRender(extremeEnderLeggings);
//
//		// Boots
//		registerRender(dullEnderBoots);
//		registerRender(enderBoots);
//		registerRender(empoweredEnderBoots);
//		registerRender(extremeEnderBoots);
//
//		// Food
//		registerRender(enderPearler);
//
//		// Food
//		registerRender(enderFruit);
//
//	}
//
//	private static void registerRender(Item item) {
//		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0,
//				new ModelResourceLocation(item.getRegistryName(), "inventory"));
//	}

}
