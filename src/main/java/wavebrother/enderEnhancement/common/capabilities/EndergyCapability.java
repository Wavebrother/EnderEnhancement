package wavebrother.enderEnhancement.common.capabilities;

import java.util.concurrent.Callable;

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class EndergyCapability {
	@CapabilityInject(EndergyCapability.class)
	public static Capability<EndergyCapability> ENDERGY = null;
	protected int energy;
	protected int capacity;

	public EndergyCapability(int capacity) {
		this(capacity, 0);
	}

	public EndergyCapability(int capacity, int energy) {
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

	public static void register() {
		CapabilityManager.INSTANCE.register(EndergyCapability.class, new IStorage<EndergyCapability>() {
			@Override
			public INBT writeNBT(Capability<EndergyCapability> capability, EndergyCapability instance, Direction side) {
				return IntNBT.func_229692_a_(instance.getEnergyStored());
			}

			@Override
			public void readNBT(Capability<EndergyCapability> capability, EndergyCapability instance, Direction side,
					INBT nbt) {
				instance.energy = ((IntNBT) nbt).getInt();
			}
		}, new Callable<EndergyCapability>() {

			@Override
			public EndergyCapability call() throws Exception {
				return new EndergyCapability(1000);
			}
		});
	}
}