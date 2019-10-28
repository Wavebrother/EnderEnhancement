package wavebrother.enderEnhancement.datagen;

import java.util.function.Consumer;

import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import wavebrother.enderEnhancement.Config;
import wavebrother.enderEnhancement.common.init.ModBlocks;
import wavebrother.enderEnhancement.common.init.ModItems;

public class Recipes extends RecipeProvider {

	public Recipes(DataGenerator generatorIn) {
		super(generatorIn);
	}

	@Override
	protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
		// Conversions
		ShapelessRecipeBuilder.shapelessRecipe(Items.ENDER_PEARL).addIngredient(ModItems.dullEnderPearl, 9)
				.addCriterion("has_dull_ender_pearl", InventoryChangeTrigger.Instance.forItems(ModItems.dullEnderPearl))
				.build(consumer);
		ShapelessRecipeBuilder.shapelessRecipe(ModItems.dullEnderPearl, 9).addIngredient(Items.ENDER_PEARL)
				.addCriterion("has_ender_pearl", InventoryChangeTrigger.Instance.forItems(Items.ENDER_PEARL))
				.build(consumer);
		ShapelessRecipeBuilder.shapelessRecipe(ModItems.empoweredEnderPearl).addIngredient(Items.ENDER_PEARL, 9)
				.addCriterion("has_ender_pearl", InventoryChangeTrigger.Instance.forItems(Items.ENDER_PEARL))
				.build(consumer);
		ShapelessRecipeBuilder.shapelessRecipe(Items.ENDER_PEARL, 9).addIngredient(ModItems.empoweredEnderPearl)
				.addCriterion("has_empowered_ender_pearl",
						InventoryChangeTrigger.Instance.forItems(ModItems.empoweredEnderPearl))
				.build(consumer);
		ShapelessRecipeBuilder.shapelessRecipe(ModItems.extremeEnderPearl)
				.addIngredient(ModItems.empoweredEnderPearl, 9).addCriterion("has_empowered_ender_pearl",
						InventoryChangeTrigger.Instance.forItems(ModItems.empoweredEnderPearl))
				.build(consumer);
		ShapelessRecipeBuilder.shapelessRecipe(ModItems.empoweredEnderPearl, 9)
				.addIngredient(ModItems.extremeEnderPearl).addCriterion("has_extreme_ender_pearl",
						InventoryChangeTrigger.Instance.forItems(ModItems.extremeEnderPearl))
				.build(consumer);
		ShapelessRecipeBuilder.shapelessRecipe(ModItems.dullEnderPearl, 4).addIngredient(ModBlocks.dullEnderBlock)
				.addCriterion("has_dull_ender_block",
						InventoryChangeTrigger.Instance.forItems(ModBlocks.dullEnderBlock))
				.build(consumer);
		ShapelessRecipeBuilder.shapelessRecipe(Items.ENDER_PEARL, 4).addIngredient(ModBlocks.enderBlock)
				.addCriterion("has_ender_block", InventoryChangeTrigger.Instance.forItems(ModBlocks.enderBlock))
				.build(consumer);
		ShapelessRecipeBuilder.shapelessRecipe(ModItems.empoweredEnderPearl, 4)
				.addIngredient(ModBlocks.empoweredEnderBlock).addCriterion("has_empowered_ender_block",
						InventoryChangeTrigger.Instance.forItems(ModBlocks.empoweredEnderBlock))
				.build(consumer);
		ShapelessRecipeBuilder.shapelessRecipe(ModItems.extremeEnderPearl, 4).addIngredient(ModBlocks.extremeEnderBlock)
				.addCriterion("has_extreme_ender_block",
						InventoryChangeTrigger.Instance.forItems(ModBlocks.extremeEnderBlock))
				.build(consumer);
		// Blocks
		ShapedRecipeBuilder.shapedRecipe(ModBlocks.dullEnderBlock).patternLine("pp").patternLine("pp")
				.addCriterion("has_dull_ender_pearl", InventoryChangeTrigger.Instance.forItems(ModItems.dullEnderPearl))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModBlocks.enderBlock).patternLine("pp").patternLine("pp")
				.key('p', Items.ENDER_PEARL)
				.addCriterion("has_ender_pearl", InventoryChangeTrigger.Instance.forItems(Items.ENDER_PEARL))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModBlocks.empoweredEnderBlock).patternLine("pp").patternLine("pp")
				.key('p', ModItems.empoweredEnderPearl).addCriterion("has_empowered_ender_pearl",
						InventoryChangeTrigger.Instance.forItems(ModItems.empoweredEnderPearl))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModBlocks.extremeEnderBlock).patternLine("pp").patternLine("pp")
				.key('p', ModItems.extremeEnderPearl).addCriterion("has_empowered_ender_pearl",
						InventoryChangeTrigger.Instance.forItems(ModItems.empoweredEnderPearl))
				.build(consumer);
		// Sticks
		ShapedRecipeBuilder.shapedRecipe(ModItems.dullEnderStick).patternLine("p").patternLine("p")
				.key('p', ModItems.dullEnderPearl)
				.addCriterion("has_dull_ender_pearl", InventoryChangeTrigger.Instance.forItems(ModItems.dullEnderPearl))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.enderStick).patternLine("p").patternLine("p")
				.key('p', Items.ENDER_PEARL)
				.addCriterion("has_ender_pearl", InventoryChangeTrigger.Instance.forItems(Items.ENDER_PEARL))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.empoweredEnderStick).patternLine("p").patternLine("p")
				.key('p', ModItems.empoweredEnderPearl).addCriterion("has_empowered_ender_pearl",
						InventoryChangeTrigger.Instance.forItems(ModItems.empoweredEnderPearl))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.extremeEnderStick).patternLine("p").patternLine("p")
				.key('p', ModItems.extremeEnderPearl).addCriterion("has_empowered_ender_pearl",
						InventoryChangeTrigger.Instance.forItems(ModItems.empoweredEnderPearl))
				.build(consumer);
		// Misc
		ShapedRecipeBuilder.shapedRecipe(ModItems.extremeEnderPorter).patternLine("  p").patternLine(" s ").patternLine("s  ")
				.key('p', ModItems.extremeEnderPearl).key('s', ModItems.extremeEnderStick)
				.addCriterion("has_extreme_ender_stick",
						InventoryChangeTrigger.Instance.forItems(ModItems.extremeEnderStick))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.enderFruit).patternLine("mmm").patternLine("bxb").patternLine(" b ")
				.key('m', ModItems.empoweredEnderPearl).key('x', ModItems.extremeEnderPearl)
				.key('b', ModBlocks.enderBlock).addCriterion("has_extreme_ender_pearl",
						InventoryChangeTrigger.Instance.forItems(ModItems.extremeEnderPearl))
				.build(consumer);
		// Tools and Armor
		if (Config.HARDMODE.get()) {
			dullCrafting(ModBlocks.dullEnderBlock, consumer);
			enderCrafting(ModBlocks.enderBlock, consumer);
			empoweredCrafting(ModBlocks.empoweredEnderBlock, consumer);
			extremeCrafting(ModBlocks.extremeEnderBlock, consumer);
		} else {
			dullCrafting(ModItems.dullEnderPearl, consumer);
			enderCrafting(Items.ENDER_PEARL, consumer);
			empoweredCrafting(ModItems.empoweredEnderPearl, consumer);
			extremeCrafting(ModItems.extremeEnderPearl, consumer);
		}

	}

	private static void dullCrafting(IItemProvider item, Consumer<IFinishedRecipe> consumer) {
		ShapedRecipeBuilder.shapedRecipe(ModItems.dullEnderAxe).patternLine("ii").patternLine("is").patternLine(" s")
				.key('i', item).key('s', ModItems.dullEnderStick)
				.addCriterion("has_dull_ender_stick", InventoryChangeTrigger.Instance.forItems(ModItems.dullEnderStick))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.dullEnderHoe).patternLine("ii").patternLine(" s").patternLine(" s")
				.key('i', item).key('s', ModItems.dullEnderStick)
				.addCriterion("has_dull_ender_stick", InventoryChangeTrigger.Instance.forItems(ModItems.dullEnderStick))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.dullEnderPickaxe).patternLine("iii").patternLine(" s ")
				.patternLine(" s ").key('i', item).key('s', ModItems.dullEnderStick)
				.addCriterion("has_dull_ender_stick", InventoryChangeTrigger.Instance.forItems(ModItems.dullEnderStick))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.dullEnderShovel).patternLine("i").patternLine("s").patternLine("s")
				.key('i', item).key('s', ModItems.dullEnderStick)
				.addCriterion("has_dull_ender_stick", InventoryChangeTrigger.Instance.forItems(ModItems.dullEnderStick))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.dullEnderSword).patternLine("i").patternLine("i").patternLine("s")
				.key('i', item).key('s', ModItems.dullEnderStick)
				.addCriterion("has_dull_ender_stick", InventoryChangeTrigger.Instance.forItems(ModItems.dullEnderStick))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.dullEnderHelmet).patternLine("iii").patternLine("i i").key('i', item)
				.addCriterion("has_dull_ender", InventoryChangeTrigger.Instance.forItems(item)).build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.dullEnderChestplate).patternLine("i i").patternLine("iii")
				.patternLine("iii").key('i', item)
				.addCriterion("has_dull_ender", InventoryChangeTrigger.Instance.forItems(item)).build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.dullEnderLeggings).patternLine("iii").patternLine("i i")
				.patternLine("i i").key('i', item)
				.addCriterion("has_dull_ender", InventoryChangeTrigger.Instance.forItems(item)).build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.dullEnderBoots).patternLine("i i").patternLine("i i").key('i', item)
				.addCriterion("has_dull_ender", InventoryChangeTrigger.Instance.forItems(item)).build(consumer);
//		ShapelessRecipeBuilder.shapelessRecipe(ModItems.dullEnderTool).addIngredient(ModItems.dullEnderAxe)
//				.addIngredient(ModItems.dullEnderShovel).addIngredient(ModItems.dullEnderPickaxe).addCriterion("\\", InventoryChangeTrigger.Instance.forItems(Items.)).build(consumer);
	}

	private static void enderCrafting(IItemProvider item, Consumer<IFinishedRecipe> consumer) {
		ShapedRecipeBuilder.shapedRecipe(ModItems.enderAxe).patternLine("ii").patternLine("is").patternLine(" s")
				.key('i', item).key('s', ModItems.enderStick)
				.addCriterion("has_dull_ender_stick", InventoryChangeTrigger.Instance.forItems(ModItems.enderStick))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.enderHoe).patternLine("ii").patternLine(" s").patternLine(" s")
				.key('i', item).key('s', ModItems.enderStick)
				.addCriterion("has_dull_ender_stick", InventoryChangeTrigger.Instance.forItems(ModItems.enderStick))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.enderPickaxe).patternLine("iii").patternLine(" s ").patternLine(" s ")
				.key('i', item).key('s', ModItems.enderStick)
				.addCriterion("has_dull_ender_stick", InventoryChangeTrigger.Instance.forItems(ModItems.enderStick))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.enderShovel).patternLine("i").patternLine("s").patternLine("s")
				.key('i', item).key('s', ModItems.enderStick)
				.addCriterion("has_dull_ender_stick", InventoryChangeTrigger.Instance.forItems(ModItems.enderStick))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.enderSword).patternLine("i").patternLine("i").patternLine("s")
				.key('i', item).key('s', ModItems.enderStick)
				.addCriterion("has_dull_ender_stick", InventoryChangeTrigger.Instance.forItems(ModItems.enderStick))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.enderHelmet).patternLine("iii").patternLine("i i").key('i', item)
				.addCriterion("has_dull_ender", InventoryChangeTrigger.Instance.forItems(item)).build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.enderChestplate).patternLine("i i").patternLine("iii")
				.patternLine("iii").key('i', item)
				.addCriterion("has_dull_ender", InventoryChangeTrigger.Instance.forItems(item)).build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.enderLeggings).patternLine("iii").patternLine("i i")
				.patternLine("i i").key('i', item)
				.addCriterion("has_dull_ender", InventoryChangeTrigger.Instance.forItems(item)).build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.enderBoots).patternLine("i i").patternLine("i i").key('i', item)
				.addCriterion("has_dull_ender", InventoryChangeTrigger.Instance.forItems(item)).build(consumer);
//		ShapelessRecipeBuilder.shapelessRecipe(ModItems.enderTool).addIngredient(ModItems.enderAxe)
//				.addIngredient(ModItems.enderShovel).addIngredient(ModItems.enderPickaxe).addCriterion("\\", InventoryChangeTrigger.Instance.forItems(Items.)).build(consumer);
	}

	private static void empoweredCrafting(IItemProvider item, Consumer<IFinishedRecipe> consumer) {
		ShapedRecipeBuilder.shapedRecipe(ModItems.empoweredEnderAxe).patternLine("ii").patternLine("is")
				.patternLine(" s").key('i', item).key('s', ModItems.empoweredEnderStick)
				.addCriterion("has_empowered_ender_stick",
						InventoryChangeTrigger.Instance.forItems(ModItems.empoweredEnderStick))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.empoweredEnderHoe).patternLine("ii").patternLine(" s")
				.patternLine(" s").key('i', item).key('s', ModItems.empoweredEnderStick)
				.addCriterion("has_empowered_ender_stick",
						InventoryChangeTrigger.Instance.forItems(ModItems.empoweredEnderStick))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.empoweredEnderPickaxe).patternLine("iii").patternLine(" s ")
				.patternLine(" s ").key('i', item).key('s', ModItems.empoweredEnderStick)
				.addCriterion("has_empowered_ender_stick",
						InventoryChangeTrigger.Instance.forItems(ModItems.empoweredEnderStick))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.empoweredEnderShovel).patternLine("i").patternLine("s")
				.patternLine("s").key('i', item).key('s', ModItems.empoweredEnderStick)
				.addCriterion("has_empowered_ender_stick",
						InventoryChangeTrigger.Instance.forItems(ModItems.empoweredEnderStick))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.empoweredEnderSword).patternLine("i").patternLine("i")
				.patternLine("s").key('i', item).key('s', ModItems.empoweredEnderStick)
				.addCriterion("has_empowered_ender_stick",
						InventoryChangeTrigger.Instance.forItems(ModItems.empoweredEnderStick))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.empoweredEnderHelmet).patternLine("iii").patternLine("i i")
				.key('i', item).addCriterion("has_empowered_ender", InventoryChangeTrigger.Instance.forItems(item))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.empoweredEnderChestplate).patternLine("i i").patternLine("iii")
				.patternLine("iii").key('i', item)
				.addCriterion("has_empowered_ender", InventoryChangeTrigger.Instance.forItems(item)).build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.empoweredEnderLeggings).patternLine("iii").patternLine("i i")
				.patternLine("i i").key('i', item)
				.addCriterion("has_empowered_ender", InventoryChangeTrigger.Instance.forItems(item)).build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.empoweredEnderBoots).patternLine("i i").patternLine("i i")
				.key('i', item).addCriterion("has_empowered_ender", InventoryChangeTrigger.Instance.forItems(item))
				.build(consumer);
//		ShapelessRecipeBuilder.shapelessRecipe(ModItems.empoweredEnderTool).addIngredient(ModItems.empoweredEnderAxe)
//				.addIngredient(ModItems.empoweredEnderShovel).addIngredient(ModItems.empoweredEnderPickaxe).addCriterion("\\", InventoryChangeTrigger.Instance.forItems(Items.)).build(consumer);
	}

	private static void extremeCrafting(IItemProvider item, Consumer<IFinishedRecipe> consumer) {
		ShapedRecipeBuilder.shapedRecipe(ModItems.extremeEnderAxe).patternLine("ii").patternLine("is").patternLine(" s")
				.key('i', item).key('s', ModItems.extremeEnderStick).addCriterion("has_extreme_ender_stick",
						InventoryChangeTrigger.Instance.forItems(ModItems.extremeEnderStick))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.extremeEnderHoe).patternLine("ii").patternLine(" s").patternLine(" s")
				.key('i', item).key('s', ModItems.extremeEnderStick).addCriterion("has_extreme_ender_stick",
						InventoryChangeTrigger.Instance.forItems(ModItems.extremeEnderStick))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.extremeEnderPickaxe).patternLine("iii").patternLine(" s ")
				.patternLine(" s ").key('i', item).key('s', ModItems.extremeEnderStick)
				.addCriterion("has_extreme_ender_stick",
						InventoryChangeTrigger.Instance.forItems(ModItems.extremeEnderStick))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.extremeEnderShovel).patternLine("i").patternLine("s").patternLine("s")
				.key('i', item).key('s', ModItems.extremeEnderStick).addCriterion("has_extreme_ender_stick",
						InventoryChangeTrigger.Instance.forItems(ModItems.extremeEnderStick))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.extremeEnderSword).patternLine("i").patternLine("i").patternLine("s")
				.key('i', item).key('s', ModItems.extremeEnderStick).addCriterion("has_extreme_ender_stick",
						InventoryChangeTrigger.Instance.forItems(ModItems.extremeEnderStick))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.extremeEnderHelmet).patternLine("iii").patternLine("i i")
				.key('i', item).addCriterion("has_extreme_ender", InventoryChangeTrigger.Instance.forItems(item))
				.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.extremeEnderChestplate).patternLine("i i").patternLine("iii")
				.patternLine("iii").key('i', item)
				.addCriterion("has_extreme_ender", InventoryChangeTrigger.Instance.forItems(item)).build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.extremeEnderLeggings).patternLine("iii").patternLine("i i")
				.patternLine("i i").key('i', item)
				.addCriterion("has_extreme_ender", InventoryChangeTrigger.Instance.forItems(item)).build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ModItems.extremeEnderBoots).patternLine("i i").patternLine("i i")
				.key('i', item).addCriterion("has_extreme_ender", InventoryChangeTrigger.Instance.forItems(item))
				.build(consumer);
		ShapelessRecipeBuilder.shapelessRecipe(ModItems.extremeEnderTool).addIngredient(ModItems.extremeEnderAxe)
				.addIngredient(ModItems.extremeEnderShovel).addIngredient(ModItems.extremeEnderPickaxe).build(consumer);
	}
}