package wavebrother.enderEnhancement.common.tiles;

import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.types.Type;

import net.minecraft.inventory.IClearable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SharedConstants;
import net.minecraft.util.datafix.DataFixesManager;
import net.minecraft.util.datafix.TypeReferences;
import wavebrother.enderEnhancement.EnderEnhancement;
import wavebrother.enderEnhancement.common.init.ModBlocks;

public class EnderPedestalTileEntity extends TileEntity implements IClearable {

	private ItemStack agitator = ItemStack.EMPTY;

	public EnderPedestalTileEntity() {
		super(register("ender_pedestal",
				TileEntityType.Builder.create(EnderPedestalTileEntity::new, ModBlocks.enderPedestal)));
		// TODO Auto-generated constructor stub
	}

	private static <T extends TileEntity> TileEntityType<T> register(String key, TileEntityType.Builder<T> builder) {
		Type<?> type = null;

		try {
			type = DataFixesManager.getDataFixer()
					.getSchema(DataFixUtils.makeKey(SharedConstants.getVersion().getWorldVersion()))
					.getChoiceType(TypeReferences.BLOCK_ENTITY, key);
		} catch (IllegalArgumentException illegalstateexception) {
			if (SharedConstants.developmentMode) {
				throw illegalstateexception;
			}

			EnderEnhancement.LOGGER.warn("No data fixer registered for block entity {}", (Object) key);
		}

		return builder.build(type);
	}

	public void read(CompoundNBT compound) {
		super.read(compound);
		if (compound.contains("AgitatorItem", 10)) {
			this.setAgitator(ItemStack.read(compound.getCompound("RecordItem")));
		}

	}

	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		if (!this.getAgitator().isEmpty()) {
			compound.put("AgitatorItem", this.getAgitator().write(new CompoundNBT()));
		}

		return compound;
	}

	public ItemStack getAgitator() {
		return this.agitator;
	}

	public void setAgitator(ItemStack p_195535_1_) {
		this.agitator = p_195535_1_;
		this.markDirty();
	}

	public void clear() {
		this.setAgitator(ItemStack.EMPTY);
	}
}
