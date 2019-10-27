package wavebrother.enderEnhancement.common.blocks;

import net.minecraftforge.energy.EnergyStorage;

public class EndergyCell extends EnergyStorage {

	public EndergyCell(int capacity) {
		super(capacity);
	}

	public EndergyCell(int capacity, int maxTransfer) {
		super(capacity, maxTransfer);
	}

	public EndergyCell(int capacity, int maxReceive, int maxExtract) {
		super(capacity, maxReceive, maxExtract);
	}

	public EndergyCell(int capacity, int maxReceive, int maxExtract, int energy) {
		super(capacity, maxReceive, maxExtract, energy);
	}

}
