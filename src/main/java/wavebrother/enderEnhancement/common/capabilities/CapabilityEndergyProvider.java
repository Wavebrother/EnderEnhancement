package wavebrother.enderEnhancement.common.capabilities;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import wavebrother.enderEnhancement.common.capabilities.CapabilityEndergy.EndergyStorage;

public class CapabilityEndergyProvider implements ICapabilitySerializable<CompoundNBT> {
	private static final String endergyCapacityNBT = "endergyCapacity";
	private static final String endergyStoredNBT = "endergyStored";
	private int endergyCapacity;
	private int endergyStored;
	private final EndergyStorage storage;
	private LazyOptional<EndergyStorage> capability;

	public CapabilityEndergyProvider(int endergyCapacity, int endergyStored) {
		this.endergyCapacity = endergyCapacity;
		this.endergyStored = endergyStored;
		storage = new EndergyStorage(endergyCapacity, endergyStored);
		capability = LazyOptional.of(() -> storage);
	}

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		return cap == CapabilityEndergy.ENDERGY ? capability.cast() : LazyOptional.empty();
	}

	@Override
	public CompoundNBT serializeNBT() {
		CompoundNBT NBT = new CompoundNBT();
		NBT.putInt(endergyCapacityNBT, endergyCapacity);
		NBT.putInt(endergyStoredNBT, endergyStored);
		return NBT;
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt) {
		endergyCapacity = nbt.getInt(endergyCapacityNBT);
		endergyStored = nbt.getInt(endergyStoredNBT);
	}
}
