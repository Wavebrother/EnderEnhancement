package wavebrother.enderEnhancement.common.capabilities;

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityEndergy {
	@CapabilityInject(EndergyStorage.class)
	public static Capability<EndergyStorage> ENDERGY = null;

	public static void register() {
		CapabilityManager.INSTANCE.register(EndergyStorage.class, new IStorage<EndergyStorage>() {
			@Override
			public INBT writeNBT(Capability<EndergyStorage> capability, EndergyStorage instance, Direction side) {
				return new IntNBT(instance.getEnergyStored());
			}

			@Override
			public void readNBT(Capability<EndergyStorage> capability, EndergyStorage instance, Direction side,
					INBT nbt) {
				((EndergyStorage) instance).energy = ((IntNBT) nbt).getInt();
			}
		}, () -> new EndergyStorage(1000));
	}

	public static class EndergyStorage {
		protected int energy;
		protected int capacity;

		public EndergyStorage(int capacity) {
			this(capacity, 0);
		}

		public EndergyStorage(int capacity, int energy) {
			this.capacity = capacity;
			this.energy = Math.max(0, Math.min(capacity, energy));
		}

		public int receiveEnergy(int maxReceive, boolean simulate) {
			int energyReceived = Math.min(capacity - energy, maxReceive);
			if (!simulate)
				energy += energyReceived;
			return energyReceived;
		}

		public int extractEnergy(int maxExtract, boolean simulate) {
			int energyExtracted = Math.min(energy, maxExtract);
			if (!simulate)
				energy -= energyExtracted;
			return energyExtracted;
		}

		public int getEnergyStored() {
			return energy;
		}

		public int getMaxEnergyStored() {
			return capacity;
		}
	}
}