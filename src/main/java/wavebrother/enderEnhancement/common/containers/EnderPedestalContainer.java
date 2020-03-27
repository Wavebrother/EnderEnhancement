package wavebrother.enderEnhancement.common.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class EnderPedestalContainer extends Container
{
    private final IInventory inventory;
    private final int numRows;

    public EnderPedestalContainer(IInventory playerInventory, IInventory chestInventory, EntityPlayer player)
    {
        this.inventory = chestInventory;
        this.numRows = chestInventory.getSizeInventory() / 9;
        chestInventory.openInventory(player);
        int i = (this.numRows - 4) * 18;

        for (int j = 0; j < this.numRows; ++j)
        {
            for (int k = 0; k < 9; ++k)
            {
                this.addSlotToContainer(new Slot(chestInventory, k + j * 9, 8 + k * 18, 18 + j * 18));
            }
        }

        for (int l = 0; l < 3; ++l)
        {
            for (int j1 = 0; j1 < 9; ++j1)
            {
                this.addSlotToContainer(new Slot(playerInventory, j1 + l * 9 + 9, 8 + j1 * 18, 103 + l * 18 + i));
            }
        }

        for (int i1 = 0; i1 < 9; ++i1)
        {
            this.addSlotToContainer(new Slot(playerInventory, i1, 8 + i1 * 18, 161 + i));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return this.inventory.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < 27)
            {
                if (!this.mergeItemStack(itemstack1, 27, this.inventorySlots.size(), true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, 27, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn)
    {
        super.onContainerClosed(playerIn);
        this.inventory.closeInventory(playerIn);
    }
}

/* extends Container {
	private final IInventory lowerChestInventory;
	private final int numRows;

	public EnderPedestalContainer(ContainerType<?> type, int id, PlayerInventory playerInventoryIn,
			IInventory p_i50092_4_, int rows) {
		super(type, id);
		assertInventorySize(p_i50092_4_, rows * 9);
		this.lowerChestInventory = p_i50092_4_;
		this.numRows = rows;
		p_i50092_4_.openInventory(playerInventoryIn.player);
		int i = (this.numRows - 4) * 18;

		for (int j = 0; j < this.numRows; ++j) {
			for (int k = 0; k < 9; ++k) {
				this.addSlot(new EnderPedestalSlot(p_i50092_4_, k + j * 9, 8 + k * 18, 18 + j * 18));
			}
		}

		for (int l = 0; l < 3; ++l) {
			for (int j1 = 0; j1 < 9; ++j1) {
				this.addSlot(new EnderPedestalSlot(playerInventoryIn, j1 + l * 9 + 9, 8 + j1 * 18, 103 + l * 18 + i));
			}
		}

		for (int i1 = 0; i1 < 9; ++i1) {
			this.addSlot(new EnderPedestalSlot(playerInventoryIn, i1, 8 + i1 * 18, 161 + i));
		}

	}

	*//**
	 * Determines whether supplied player can use this container
	 *//*
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.lowerChestInventory.isUsableByPlayer(playerIn);
	}

	*//**
	 * Handle when the stack in slot {@code index} is shift-clicked. Normally this
	 * moves the stack between the player inventory and the other inventory(s).
	 *//*
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (index < this.numRows * 9) {
				if (!this.mergeItemStack(itemstack1, this.numRows * 9, this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, this.numRows * 9, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
		}

		return itemstack;
	}

	*//**
	 * Called when the container is closed.
	 *//*
	public void onContainerClosed(EntityPlayer playerIn) {
		super.onContainerClosed(playerIn);
		this.lowerChestInventory.closeInventory(playerIn);
	}

	*//**
	 * Gets the inventory associated with this chest container.
	 * 
	 * @see #field_75155_e
	 *//*
	public IInventory getLowerChestInventory() {
		return this.lowerChestInventory;
	}

	@OnlyIn(Dist.CLIENT)
	public int getNumRows() {
		return this.numRows;
	}

	private static class EnderPedestalSlot extends Slot {

		public EnderPedestalSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
			super(inventoryIn, index, xPosition, yPosition);
		}

		@Override
		public boolean isItemValid(ItemStack stack) {
			return true;
		}

	}

}
*/