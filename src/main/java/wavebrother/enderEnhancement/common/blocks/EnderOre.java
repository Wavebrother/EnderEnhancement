package wavebrother.enderEnhancement.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import wavebrother.enderEnhancement.EnderEnhancement;
import wavebrother.enderEnhancement.common.util.EnderTier;

public class EnderOre extends OreBlock {

	EnderTier tier;
	public final EnderOreItem blockItem;

	public EnderOre(EnderTier tier, String name) {
		super(Block.Properties.create(Material.ROCK, MaterialColor.YELLOW_TERRACOTTA).hardnessAndResistance(3.0F,
				3.0F));
		setRegistryName(name);
		this.tier = tier;
		this.blockItem = new EnderOreItem();
	}

	public void setupOregen(FillerBlockType filler) {
		int frequency = 0;
		switch (tier) {
		case DULL:
			frequency = 4;
			break;
		case ENDER:
			frequency = 5;
			break;
		case EMPOWERED:
			frequency = 3;
			break;
		case EXTREME:
			frequency = 2;
			break;
		default:
			break;
		}
		Biomes.THE_END.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION,
				Biome.createDecoratedFeature(Feature.ORE,
						new OreFeatureConfig(filler, this.getDefaultState(), frequency), Placement.COUNT_RANGE,
						new CountRangeConfig(frequency * 2, 10, 20, 128)));
	}

	@Override
	protected int getExperience(Random random) {
		switch (tier) {
		case DULL:
			return MathHelper.nextInt(random, 1, 2);
		case ENDER:
			return MathHelper.nextInt(random, 2, 4);
		case EMPOWERED:
			return MathHelper.nextInt(random, 3, 6);
		case EXTREME:
			return MathHelper.nextInt(random, 4, 8);
		default:
			return 0;
		}
	}

	public class EnderOreItem extends BlockItem {

		protected EnderOreItem() {
			super(EnderOre.this, new Item.Properties().group(EnderEnhancement.CREATIVE_TAB));
			setRegistryName(EnderOre.this.getRegistryName());
		}

	}
}
