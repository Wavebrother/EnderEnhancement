package wavebrother.enderEnhancement.common.tiles;

import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.crash.ReportedException;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import wavebrother.enderEnhancement.common.blocks.EnderPedestal;
import wavebrother.enderEnhancement.common.containers.EnderPedestalContainer;
import wavebrother.enderEnhancement.common.init.ModTileEntities;

public class EnderPedestalTileEntity extends TileEntity
		implements IInventory, INamedContainerProvider, ITickableTileEntity {

	public EnderPedestalTileEntity() {
		super(ModTileEntities.enderPedestal);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Container createMenu(int id, PlayerInventory player, PlayerEntity p_createMenu_3_) {
		return new EnderPedestalContainer(ContainerType.GENERIC_9X3, id, player, new Inventory(27), 3);
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent(getType().getRegistryName().toString());
	}

	private NonNullList<ItemStack> inventory = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);

	private ItemStack pedestalItem = ItemStack.EMPTY;
	private PlayerEntity itemOwner;

	@Override
	public void tick() {

	}

	public PlayerEntity getItemOwner() {
		return this.itemOwner;
	}

	public void setItemOwner(PlayerEntity itemOwnerIn) {
		this.itemOwner = itemOwnerIn;
		this.markDirty();
	}

	public ItemStack getPedestalItem() {
		return this.pedestalItem;
	}

	public void setPedestalItem(ItemStack pedestalItem) {
		this.pedestalItem = pedestalItem;
		this.markDirty();
	}

	public void clearPedestal() {
		pedestalItem = ItemStack.EMPTY;
	}

	public void clearInventory() {
		inventory.clear();
	}

	@Override
	public void clear() {
		clearPedestal();
		clearInventory();
	}

	@Override
	public int getSizeInventory() {
		return 27;
	}

	@Override
	public boolean isUsableByPlayer(PlayerEntity player) {
		if (!hasWorld()) {
		} else if (getWorld().getBlockState(getPos()).has(EnderPedestal.HAS_ACCUMULATOR)
				|| getWorld().getBlockState(getPos()).has(EnderPedestal.HAS_AGITATOR))
			return false;
		return player == itemOwner;
	}

	/**
	 * Returns the first item stack that is empty.
	 */
	public int getFirstEmptyStack() {
		for (int i = 0; i < this.inventory.size(); ++i) {
			if (this.inventory.get(i).isEmpty()) {
				return i;
			}
		}

		return -1;
	}

	private int storePartialItemStack(ItemStack itemStackIn) {
		int i = this.storeItemStack(itemStackIn);
		if (i == -1) {
			i = this.getFirstEmptyStack();
		}

		return i == -1 ? itemStackIn.getCount() : this.addResource(i, itemStackIn);
	}

	public int storeItemStack(ItemStack itemStackIn) {
		for (int i = 0; i < this.inventory.size(); ++i) {
			if (this.canMergeStacks(this.inventory.get(i), itemStackIn)) {
				return i;
			}
		}

		return -1;
	}

	private boolean canMergeStacks(ItemStack stack1, ItemStack stack2) {
		return !stack1.isEmpty() && this.stackEqualExact(stack1, stack2) && stack1.isStackable()
				&& stack1.getCount() < stack1.getMaxStackSize() && stack1.getCount() < this.getInventoryStackLimit();
	}

	private boolean stackEqualExact(ItemStack stack1, ItemStack stack2) {
		return stack1.getItem() == stack2.getItem() && ItemStack.areItemStackTagsEqual(stack1, stack2);
	}

	private int addResource(int p_191973_1_, ItemStack p_191973_2_) {
		@SuppressWarnings("unused")
		Item item = p_191973_2_.getItem();
		int i = p_191973_2_.getCount();
		ItemStack itemstack = this.getStackInSlot(p_191973_1_);
		if (itemstack.isEmpty()) {
			itemstack = p_191973_2_.copy(); // Forge: Replace Item clone above to preserve item capabilities when
											// picking the item up.
			itemstack.setCount(0);
			if (p_191973_2_.hasTag()) {
				itemstack.setTag(p_191973_2_.getTag().copy());
			}

			this.setInventorySlotContents(p_191973_1_, itemstack);
		}

		int j = i;
		if (i > itemstack.getMaxStackSize() - itemstack.getCount()) {
			j = itemstack.getMaxStackSize() - itemstack.getCount();
		}

		if (j > this.getInventoryStackLimit() - itemstack.getCount()) {
			j = this.getInventoryStackLimit() - itemstack.getCount();
		}

		if (j == 0) {
			return i;
		} else {
			i = i - j;
			itemstack.grow(j);
			itemstack.setAnimationsToGo(5);
			return i;
		}
	}

	public boolean addItemStackToInventory(ItemStack itemStackIn) {
		return this.add(-1, itemStackIn);
	}

	public boolean add(int p_191971_1_, ItemStack p_191971_2_) {
		if (p_191971_2_.isEmpty()) {
			return false;
		} else {
			try {
				if (p_191971_2_.isDamaged()) {
					if (p_191971_1_ == -1) {
						p_191971_1_ = this.getFirstEmptyStack();
					}

					if (p_191971_1_ >= 0) {
						this.inventory.set(p_191971_1_, p_191971_2_.copy());
						this.inventory.get(p_191971_1_).setAnimationsToGo(5);
						p_191971_2_.setCount(0);
						return true;
					} else {
						return false;
					}
				} else {
					int i;
					while (true) {
						i = p_191971_2_.getCount();
						if (p_191971_1_ == -1) {
							p_191971_2_.setCount(this.storePartialItemStack(p_191971_2_));
						} else {
							p_191971_2_.setCount(this.addResource(p_191971_1_, p_191971_2_));
						}

						if (p_191971_2_.isEmpty() || p_191971_2_.getCount() >= i) {
							break;
						}
					}
					return p_191971_2_.getCount() < i;
				}
			} catch (Throwable throwable) {
				CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Adding item to inventory");
				CrashReportCategory crashreportcategory = crashreport.makeCategory("Item being added");
				crashreportcategory.addDetail("Registry Name",
						() -> String.valueOf(p_191971_2_.getItem().getRegistryName()));
				crashreportcategory.addDetail("Item Class", () -> p_191971_2_.getItem().getClass().getName());
				crashreportcategory.addDetail("Item ID", Item.getIdFromItem(p_191971_2_.getItem()));
				crashreportcategory.addDetail("Item data", p_191971_2_.getDamage());
				crashreportcategory.addDetail("Item name", () -> {
					return p_191971_2_.getDisplayName().getString();
				});
				throw new ReportedException(crashreport);
			}
		}
	}

	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		CompoundNBT pedestalItemnbt = new CompoundNBT();
		if (!pedestalItem.isEmpty()) {
			pedestalItem.write(pedestalItemnbt);
		}
		if (itemOwner != null)
			compound.putUniqueId("ItemOwner", getItemOwner().getUniqueID());

		compound.put("PedestalItem", pedestalItemnbt);
		ListNBT inventoryListNBT = new ListNBT();

		for (ItemStack itemstack1 : this.inventory) {
			CompoundNBT compoundnbt1 = new CompoundNBT();
			if (!itemstack1.isEmpty()) {
				itemstack1.write(compoundnbt1);
			}

			inventoryListNBT.add(compoundnbt1);
		}

		compound.put("Inventory", inventoryListNBT);
		return compound;
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void read(CompoundNBT compound) {
		super.read(compound);
		if (compound.contains("PedestalItem")) {
			CompoundNBT pedestalItemNBT = compound.getCompound("ArmorItems");
			setPedestalItem(ItemStack.read(pedestalItemNBT));
		}
		if (compound.contains("ItemOwner")) {
			setItemOwner(world.getPlayerByUuid(compound.getUniqueId("ItemOwner")));
		}

		if (compound.contains("Inventory", 9)) {
			ListNBT listnbt1 = compound.getList("HandItems", 10);

			for (int j = 0; j < this.inventory.size(); ++j) {
				this.inventory.set(j, ItemStack.read(listnbt1.getCompound(j)));
			}
		}
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.inventory) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}

		return true;

	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return inventory.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		ItemStack itemstack = ItemStackHelper.getAndSplit(this.getItems(), index, count);
		if (!itemstack.isEmpty()) {
			this.markDirty();
		}

		return itemstack;
	}

	protected NonNullList<ItemStack> getItems() {
		return this.inventory;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(this.getItems(), index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		this.getItems().set(index, stack);
		if (stack.getCount() > this.getInventoryStackLimit()) {
			stack.setCount(this.getInventoryStackLimit());
		}

		this.markDirty();
	}

}
