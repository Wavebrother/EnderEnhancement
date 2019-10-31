package wavebrother.enderEnhancement.common.util;

import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.ConstantRange;
import net.minecraft.world.storage.loot.ILootConditionConsumer;
import net.minecraft.world.storage.loot.ItemLootEntry;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.conditions.SurvivesExplosion;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import wavebrother.enderEnhancement.Reference;

@EventBusSubscriber(modid = Reference.MOD_ID)
public class LootTables {

	@SubscribeEvent
	public static void modifyVanilla(LootTableLoadEvent event) {

	}

	public static void registerLootTables() {
	}

	private static final Set<Item> blockList = Stream.of(Blocks.DRAGON_EGG, Blocks.BEACON, Blocks.CONDUIT,
			Blocks.SKELETON_SKULL, Blocks.WITHER_SKELETON_SKULL, Blocks.PLAYER_HEAD, Blocks.ZOMBIE_HEAD,
			Blocks.CREEPER_HEAD, Blocks.DRAGON_HEAD, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX,
			Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX,
			Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX,
			Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX,
			Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX)
			.map(IItemProvider::asItem).collect(ImmutableSet.toImmutableSet());
	private final Map<ResourceLocation, LootTable.Builder> lootTableMap = Maps.newHashMap();

	private static <T> T randomFunction(IItemProvider blockItem, ILootConditionConsumer<T> lootPool) {
		return (T) (!blockList.contains(blockItem.asItem())
				? lootPool.acceptCondition(SurvivesExplosion.builder())
				: lootPool.cast());
	}

	private static LootTable.Builder func_218546_a(IItemProvider blockItem) {
		return LootTable.builder().addLootPool(randomFunction(blockItem,
				LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(blockItem))));
	}

	public void register(Block block, IItemProvider blockItem) {
		this.registerLootTable(block, func_218546_a(blockItem));
	}

	public void register(Block block) {
		this.register(block, block);
	}

	private void registerLootTable(Block block, LootTable.Builder lootTableBuilder) {
		this.lootTableMap.put(block.getLootTable(), lootTableBuilder);
	}

}
