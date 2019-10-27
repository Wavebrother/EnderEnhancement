package wavebrother.enderEnhancement.common.tiles;

import net.minecraft.inventory.IClearable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import wavebrother.enderEnhancement.common.init.ModTileEntities;

public class EnderPedestalTileEntity extends TileEntity implements IClearable {

	private ItemStack agitator = ItemStack.EMPTY;

	public EnderPedestalTileEntity() {
		super(ModTileEntities.enderPedestal);
		// TODO Auto-generated constructor stub
	}

	public void read(CompoundNBT compound) {
		super.read(compound);
		if (compound.contains("AgitatorItem", 10)) {
			this.setAgitator(ItemStack.read(compound.getCompound("AgitatorItem")));
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

	public void setAgitator(ItemStack agitatorIn) {
		this.agitator = agitatorIn;
		this.markDirty();
	}

	public void clear() {
		this.setAgitator(ItemStack.EMPTY);
	}
}
