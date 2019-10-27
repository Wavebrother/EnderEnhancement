package wavebrother.enderEnhancement.common.init;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.registries.ForgeRegistry;
import wavebrother.enderEnhancement.Config;

public class ModRecipes {
	
//	public static void init(){
//		
//		//Pearls
//		ForgeRegistry.addShapelessRecipe(new ItemStack(Items.ENDER_PEARL), new ItemStack(ModBlocks.dullEnderBlock, 9));
//		ForgeRegistry.addShapelessRecipe(new ItemStack(ModItems.empoweredEnderPearl), new ItemStack(Items.ENDER_PEARL, 9));
//		ForgeRegistry.addShapelessRecipe(new ItemStack(ModItems.extremeEnderPearl), new ItemStack(ModItems.empoweredEnderPearl, 9));
//		
//		ForgeRegistry.addShapelessRecipe(new ItemStack(ModItems.dullEnderPearl, 9), ModBlocks.dullEnderBlock);
//		ForgeRegistry.addShapelessRecipe(new ItemStack(Items.ENDER_PEARL, 4), ModBlocks.enderBlock);
//		ForgeRegistry.addShapelessRecipe(new ItemStack(ModItems.empoweredEnderPearl, 4), ModBlocks.empoweredEnderBlock);
//		ForgeRegistry.addShapelessRecipe(new ItemStack(ModItems.extremeEnderPearl, 4), ModBlocks.extremeEnderBlock);
//		
//		//Blocks
//		ForgeRegistry.addShapelessRecipe(new ItemStack(ModBlocks.dullEnderBlock), new ItemStack(ModItems.dullEnderPearl, 9));
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModBlocks.enderBlock), "PP", "PP", 'P', Items.ENDER_PEARL);
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModBlocks.empoweredEnderBlock), "PP", "PP", 'P', ModItems.empoweredEnderPearl);
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModBlocks.extremeEnderBlock), "PP", "PP", 'P', ModItems.extremeEnderPearl);
//
//		ForgeRegistry.addShapelessRecipe(new ItemStack(ModBlocks.empoweredEnderBlock), new ItemStack(ModBlocks.enderBlock, 9));
//		ForgeRegistry.addShapelessRecipe(new ItemStack(ModBlocks.extremeEnderBlock), new ItemStack(ModBlocks.empoweredEnderBlock, 9));
//		
//		//Sticks
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.dullEnderStick, 4), "P", "P", 'P', ModItems.dullEnderPearl);
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.enderStick, 4), "P", "P", 'P', ModBlocks.enderBlock);
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.empoweredEnderStick, 4), "P", "P", 'P', ModItems.empoweredEnderPearl);
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.extremeEnderStick, 4), "P", "P", 'P', ModItems.extremeEnderPearl);
//		
//		//Misc Tools
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.enderPearler), "  P", " S ", "S  ", 'P', ModItems.extremeEnderPearl, 'S', ModItems.extremeEnderStick);
//		
//		//Food
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.enderFruit, 4), "MMM", "BXB", " B ", 'M', ModItems.empoweredEnderPearl, 'X', ModItems.extremeEnderPearl, 'B', ModBlocks.enderBlock);
//		
//		if(Config.hardcoreMode == false){
//			dullCrafting(new ItemStack(ModItems.dullEnderPearl));
//			enderCrafting(new ItemStack(Items.ENDER_PEARL));
//			empoweredCrafting(new ItemStack(ModItems.empoweredEnderPearl));
//			extremeCrafting(new ItemStack(ModItems.extremeEnderPearl));
//		}
//		else{
//			dullCrafting(new ItemStack(ModBlocks.dullEnderBlock));
//			enderCrafting(new ItemStack(ModBlocks.enderBlock));
//			empoweredCrafting(new ItemStack(ModBlocks.empoweredEnderBlock));
//			extremeCrafting(new ItemStack(ModBlocks.extremeEnderBlock));			
//		}
//	}
//	
//	private static void dullCrafting(ItemStack item){
//		//Axe
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.dullEnderAxe), "II", "IS", " S", 'I', item, 'S', ModItems.dullEnderStick);
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.dullEnderAxe), "II", "SI", "S ", 'I', item, 'S', ModItems.dullEnderStick);
//		
//		//Hoe
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.dullEnderHoe), "II", " S", " S", 'I', item, 'S', ModItems.dullEnderStick);
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.dullEnderHoe), "II", "S ", "S ", 'I', item, 'S', ModItems.dullEnderStick);
//	
//		//Pickaxe
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.dullEnderPickaxe), "III", " S ", " S ", 'I', item, 'S', ModItems.dullEnderStick);
//
//		//Shovel
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.dullEnderShovel), "I", "S", "S", 'I', item, 'S', ModItems.dullEnderStick);
//
//		//Sword
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.dullEnderSword), "I", "I", "S", 'I', item, 'S', ModItems.dullEnderStick);
//
//		//Armor
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.dullEnderHelmet), "III", "I I", 'I', item);
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.dullEnderChestplate), "I I", "III", "III", 'I', item);
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.dullEnderLeggings), "III", "I I", "I I", 'I', item);
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.dullEnderBoots), "I I", "I I", 'I', item);
//	}
//	
//	private static void enderCrafting(ItemStack item){
//		//Axe
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.enderAxe), "II", "IS", " S", 'I', item, 'S', ModItems.enderStick);
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.enderAxe), "II", "SI", "S ", 'I', item, 'S', ModItems.enderStick);
//		
//		//Hoe
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.enderHoe), "II", " S", " S", 'I', item, 'S', ModItems.enderStick);
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.enderHoe), "II", "S ", "S ", 'I', item, 'S', ModItems.enderStick);
//	
//		//Pickaxe
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.enderPickaxe), "III", " S ", " S ", 'I', item, 'S', ModItems.enderStick);
//
//		//Shovel
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.enderShovel), "I", "S", "S", 'I', item, 'S', ModItems.enderStick);
//
//		//Sword
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.enderSword), "I", "I", "S", 'I', item, 'S', ModItems.enderStick);
//
//		//Armor
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.enderHelmet), "III", "I I", 'I', item);
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.enderChestplate), "I I", "III", "III", 'I', item);
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.enderLeggings), "III", "I I", "I I", 'I', item);
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.enderBoots), "I I", "I I", 'I', item);
//	}
//	
//	private static void empoweredCrafting(ItemStack item){
//		//Axe
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.empoweredEnderAxe), "II", "IS", " S", 'I', item, 'S', ModItems.empoweredEnderStick);
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.empoweredEnderAxe), "II", "SI", "S ", 'I', item, 'S', ModItems.empoweredEnderStick);
//		
//		//Hoe
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.empoweredEnderHoe), "II", " S", " S", 'I', item, 'S', ModItems.empoweredEnderStick);
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.empoweredEnderHoe), "II", "S ", "S ", 'I', item, 'S', ModItems.empoweredEnderStick);
//	
//		//Pickaxe
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.empoweredEnderPickaxe), "III", " S ", " S ", 'I', item, 'S', ModItems.empoweredEnderStick);
//
//		//Shovel
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.empoweredEnderShovel), "I", "S", "S", 'I', item, 'S', ModItems.empoweredEnderStick);
//
//		//Sword
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.empoweredEnderSword), "I", "I", "S", 'I', item, 'S', ModItems.empoweredEnderStick);
//
//		//Armor
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.empoweredEnderHelmet), "III", "I I", 'I', item);
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.empoweredEnderChestplate), "I I", "III", "III", 'I', item);
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.empoweredEnderLeggings), "III", "I I", "I I", 'I', item);
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.empoweredEnderBoots), "I I", "I I", 'I', item);
//	}
//	
//	private static void extremeCrafting(ItemStack item){
//		//Axe
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.extremeEnderAxe), "II", "IS", " S", 'I', item, 'S', ModItems.extremeEnderStick);
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.extremeEnderAxe), "II", "SI", "S ", 'I', item, 'S', ModItems.extremeEnderStick);
//		
//		//Hoe
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.extremeEnderHoe), "II", " S", " S", 'I', item, 'S', ModItems.extremeEnderStick);
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.extremeEnderHoe), "II", "S ", "S ", 'I', item, 'S', ModItems.extremeEnderStick);
//	
//		//Pickaxe
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.extremeEnderPickaxe), "III", " S ", " S ", 'I', item, 'S', ModItems.extremeEnderStick);
//
//		//Shovel
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.extremeEnderShovel), "I", "S", "S", 'I', item, 'S', ModItems.extremeEnderStick);
//
//		//Sword
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.extremeEnderSword), "I", "I", "S", 'I', item, 'S', ModItems.extremeEnderStick);
//
//		//Armor
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.extremeEnderHelmet), "III", "I I", 'I', item);
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.extremeEnderChestplate), "I I", "III", "III", 'I', item);
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.extremeEnderLeggings), "III", "I I", "I I", 'I', item);
//		ForgeRegistry.addShapedRecipe(new ItemStack(ModItems.extremeEnderBoots), "I I", "I I", 'I', item);
//	}

}
