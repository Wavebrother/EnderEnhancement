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
				Reference.Items.DULLENDERPEARL.getRegistryName());
		empoweredEnderPearl = new EnderPearl(EnderTier.EMPOWERED,
				Reference.Items.EMPOWEREDENDERPEARL.getRegistryName());
		extremeEnderPearl = new EnderPearl(EnderTier.EXTREME,
				Reference.Items.EXTREMEENDERPEARL.getRegistryName());

		// Sticks
		dullEnderStick = new EnderStick(EnderTier.DULL,
				Reference.Items.DULLENDERSTICK.getRegistryName());
		enderStick = new EnderStick(EnderTier.ENDER, Reference.Items.ENDERSTICK.getRegistryName());
		empoweredEnderStick = new EnderStick(EnderTier.EMPOWERED,
				Reference.Items.EMPOWEREDENDERSTICK.getRegistryName());
		extremeEnderStick = new EnderStick(EnderTier.EXTREME,
				Reference.Items.EXTREMEENDERSTICK.getRegistryName());

		// Axes
		dullEnderAxe = new EnderAxe(EnderTier.DULL, Reference.Items.DULLENDERAXE.getRegistryName());
		enderAxe = new EnderAxe(EnderTier.ENDER, Reference.Items.ENDERAXE.getRegistryName());
		empoweredEnderAxe = new EnderAxe(EnderTier.EMPOWERED,
				Reference.Items.EMPOWEREDENDERAXE.getRegistryName());
		extremeEnderAxe = new EnderAxe(EnderTier.EXTREME,
				Reference.Items.EXTREMEENDERAXE.getRegistryName());

		// Hoes
		dullEnderHoe = new EnderHoe(EnderTier.DULL, Reference.Items.DULLENDERHOE.getRegistryName());
		enderHoe = new EnderHoe(EnderTier.ENDER, Reference.Items.ENDERHOE.getRegistryName());
		empoweredEnderHoe = new EnderHoe(EnderTier.EMPOWERED,
				Reference.Items.EMPOWEREDENDERHOE.getRegistryName());
		extremeEnderHoe = new EnderHoe(EnderTier.EXTREME,
				Reference.Items.EXTREMEENDERHOE.getRegistryName());

		// Pickaxes
		dullEnderPickaxe = new EnderPickaxe(EnderTier.DULL,
				Reference.Items.DULLENDERPICKAXE.getRegistryName());
		enderPickaxe = new EnderPickaxe(EnderTier.ENDER,
				Reference.Items.ENDERPICKAXE.getRegistryName());
		empoweredEnderPickaxe = new EnderPickaxe(EnderTier.EMPOWERED,
				Reference.Items.EMPOWEREDENDERPICKAXE.getRegistryName());
		extremeEnderPickaxe = new EnderPickaxe(EnderTier.EXTREME,
				Reference.Items.EXTREMEENDERPICKAXE.getRegistryName());

		// Shovels
		dullEnderShovel = new EnderShovel(EnderTier.DULL,
				Reference.Items.DULLENDERSHOVEL.getRegistryName());
		enderShovel = new EnderShovel(EnderTier.ENDER, Reference.Items.ENDERSHOVEL.getRegistryName());
		empoweredEnderShovel = new EnderShovel(EnderTier.EMPOWERED,
				Reference.Items.EMPOWEREDENDERSHOVEL.getRegistryName());
		extremeEnderShovel = new EnderShovel(EnderTier.EXTREME,
				Reference.Items.EXTREMEENDERSHOVEL.getRegistryName());

		// Swords
		dullEnderSword = new EnderSword(EnderTier.DULL,
				Reference.Items.DULLENDERSWORD.getRegistryName());
		enderSword = new EnderSword(EnderTier.ENDER, Reference.Items.ENDERSWORD.getRegistryName());
		empoweredEnderSword = new EnderSword(EnderTier.EMPOWERED,
				Reference.Items.EMPOWEREDENDERSWORD.getRegistryName());
		extremeEnderSword = new EnderSword(EnderTier.EXTREME,
				Reference.Items.EXTREMEENDERSWORD.getRegistryName());

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

		// Misc Tools
		extremeEnderTool = new EnderMultiTool(EnderTier.EXTREME,
				Reference.Items.EXTREMEENDERTOOL.getRegistryName());
		enderPearler = new ItemEnderPearler();

		// Agitators
		dullEnderAgitator = new EndermanAgitator(EnderTier.DULL,
				Reference.Items.DULLENDERAGITATOR.getRegistryName());
		enderAgitator = new EndermanAgitator(EnderTier.ENDER,
				Reference.Items.ENDERAGITATOR.getRegistryName());
		empoweredEnderAgitator = new EndermanAgitator(EnderTier.EMPOWERED,
				Reference.Items.EMPOWEREDENDERAGITATOR.getRegistryName());
		extremeEnderAgitator = new EndermanAgitator(EnderTier.EXTREME,
				Reference.Items.EXTREMEENDERAGITATOR.getRegistryName());

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
