package wavebrother.enderEnhancement.common.util;

import net.minecraft.data.loot.BlockLootTables;
import wavebrother.enderEnhancement.common.init.ModBlocks;
import wavebrother.enderEnhancement.common.init.ModItems;

public class OreLootTable extends BlockLootTables {

	@Override
	protected void addTables() {
		super.addTables();
		this.registerLootTable(ModBlocks.dullEnderOre, (block) -> {
			return BlockLootTables.droppingItemWithFortune(block, ModItems.dullEnderPearl);
		});
		this.registerLootTable(ModBlocks.enderOre, (block) -> {
			return BlockLootTables.droppingItemWithFortune(block, ModItems.enderPearl);
		});
		this.registerLootTable(ModBlocks.empoweredEnderOre, (block) -> {
			return BlockLootTables.droppingItemWithFortune(block, ModItems.empoweredEnderPearl);
		});
		this.registerLootTable(ModBlocks.extremeEnderOre, (block) -> {
			return BlockLootTables.droppingItemWithFortune(block, ModItems.extremeEnderPearl);
		});
	}
}
