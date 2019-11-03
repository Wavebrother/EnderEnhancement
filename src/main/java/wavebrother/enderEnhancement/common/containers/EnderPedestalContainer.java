package wavebrother.enderEnhancement.common.containers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class EnderPedestalContainer extends Container
{
    private final IInventory inventory;

    public static EnderPedestalContainer createIronContainer(int windowId, PlayerInventory playerInventory)
    {
        return new EnderPedestalContainer(ContainerType.GENERIC_9X3, windowId, playerInventory, new Inventory(27));
    }

    public static EnderPedestalContainer createIronContainer(int windowId, PlayerInventory playerInventory, IInventory inventory)
    {
        return new EnderPedestalContainer(ContainerType.GENERIC_9X3, windowId, playerInventory, inventory);
    }

    public EnderPedestalContainer(ContainerType<?> containerType, int windowId, PlayerInventory playerInventory, IInventory inventory)
    {
        super(containerType, windowId);
        assertInventorySize(inventory, 27);

        this.inventory = inventory;

        inventory.openInventory(playerInventory.player);

            for (int chestRow = 0; chestRow < 3; chestRow++)
            {
                for (int chestCol = 0; chestCol < 9; chestCol++)
                {
                    this.addSlot(new Slot(inventory, chestCol + chestRow * 9, 12 + chestCol * 18, 18 + chestRow * 18));
                }
            }

        int leftCol = (184 - 162) / 2 + 1;

        for (int playerInvRow = 0; playerInvRow < 3; playerInvRow++)
        {
            for (int playerInvCol = 0; playerInvCol < 9; playerInvCol++)
            {
                this.addSlot(new Slot(playerInventory, playerInvCol + playerInvRow * 9 + 9, leftCol + playerInvCol * 18, 222 - (4 - playerInvRow) * 18 - 10));
            }

        }

        for (int hotbarSlot = 0; hotbarSlot < 9; hotbarSlot++)
        {
            this.addSlot(new Slot(playerInventory, hotbarSlot, leftCol + hotbarSlot * 18, 222 - 24));
        }
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn)
    {
        return this.inventory.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index)
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
    public void onContainerClosed(PlayerEntity playerIn)
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
	public boolean canInteractWith(PlayerEntity playerIn) {
		return this.lowerChestInventory.isUsableByPlayer(playerIn);
	}

	*//**
	 * Handle when the stack in slot {@code index} is shift-clicked. Normally this
	 * moves the stack between the player inventory and the other inventory(s).
	 *//*
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
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
	public void onContainerClosed(PlayerEntity playerIn) {
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