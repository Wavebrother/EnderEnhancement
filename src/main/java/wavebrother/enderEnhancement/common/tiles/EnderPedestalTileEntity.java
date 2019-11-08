package wavebrother.enderEnhancement.common.tiles;

import java.util.List;

import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.crash.ReportedException;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import wavebrother.enderEnhancement.common.blocks.EnderPedestal;
import wavebrother.enderEnhancement.common.init.ModTileEntities;
import wavebrother.enderEnhancement.common.item.EndermanAgitator;
import wavebrother.enderEnhancement.common.item.EndermanAgitator.DummyAgitator;
import wavebrother.enderEnhancement.common.item.ItemAccumulator;
import wavebrother.enderEnhancement.common.util.EnderTier;

@EventBusSubscriber(bus = Bus.FORGE)
public class EnderPedestalTileEntity extends TileEntity implements ITickableTileEntity, IInventory {
	private NonNullList<ItemStack> inventory;
	private ItemStack pedestalItem = ItemStack.EMPTY;
	private PlayerEntity itemOwner;

	protected EnderPedestalTileEntity(TileEntityType<?> typeIn) {
		super(typeIn);

		this.inventory = NonNullList.<ItemStack>withSize(27, ItemStack.EMPTY);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public EnderPedestalTileEntity() {
		this(ModTileEntities.enderPedestal);
	}

	@Override
	public void tick() {
		if (getWorld().isRemote)
			return;
		if (getPedestalItem().getItem() instanceof ItemAccumulator) {
			((ItemAccumulator) getPedestalItem().getItem()).collectItems(getPedestalItem(), getWorld(), null, this);
		} else if (getPedestalItem().getItem() instanceof EndermanAgitator) {
			if (!(getPedestalItem().hasTag() && getPedestalItem().getTag().getBoolean(EndermanAgitator.agitatorTag))) {
				EnderTier tier = ((EndermanAgitator) getPedestalItem().getItem()).getEnderTier();
				List<PlayerEntity> players = getWorld().getEntitiesWithinAABB(PlayerEntity.class, new AxisAlignedBB(
						pos.getX() - EndermanAgitator.getRange(tier), pos.getY() - EndermanAgitator.getRange(tier),
						pos.getZ() - EndermanAgitator.getRange(tier), pos.getX() + EndermanAgitator.getRange(tier),
						pos.getY() + EndermanAgitator.getRange(tier), pos.getZ() + EndermanAgitator.getRange(tier)),
						EntityPredicates.NOT_SPECTATING);
				for (PlayerEntity player : players) {
					player.getCooldownTracker().setCooldown(DummyAgitator.INSTANCE, 2);
					player.getPersistentData().putString(EndermanAgitator.agitatorTag, tier.name());
				}
			} else if (itemOwner != null) {
				EnderTier tier = ((EndermanAgitator) getPedestalItem().getItem()).getEnderTier();
				List<EndermanEntity> endermen = getWorld().getEntitiesWithinAABB(EndermanEntity.class,
						new AxisAlignedBB(pos.getX() - EndermanAgitator.getRange(tier),
								pos.getY() - EndermanAgitator.getRange(tier),
								pos.getZ() - EndermanAgitator.getRange(tier),
								pos.getX() + EndermanAgitator.getRange(tier),
								pos.getY() + EndermanAgitator.getRange(tier),
								pos.getZ() + EndermanAgitator.getRange(tier)),
						EntityPredicates.NOT_SPECTATING);
				for (EndermanEntity enderman : endermen) {
					// enderman.getNavigator().tryMoveToXYZ(pos.getX(), pos.getY(), pos.getX(),
					// enderman.getAIMoveSpeed());
					enderman.setAttackTarget(itemOwner);
				}
			}
		}

	}

	private PlayerEntity getItemOwner() {
		return this.itemOwner;
	}

	public void setItemOwner(PlayerEntity itemOwnerIn) {
		this.itemOwner = itemOwnerIn;
		this.markDirty();
	}

	public ItemStack getPedestalItem() {
		return this.pedestalItem;
	}

	public void setPedestalItem(ItemStack pedestalItem, boolean ping) {
		if (!ping) {
			this.pedestalItem = pedestalItem;
			this.markDirty();
		}
		if (hasWorld() && !getWorld().isRemote)
			MinecraftForge.EVENT_BUS.post(new EnderPedestalUpdateEvent(this.pedestalItem, pos, false));
	}

	public void clearPedestal() {
		setPedestalItem(ItemStack.EMPTY, false);
	}

	private void clearInventory() {
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
	private int getFirstEmptyStack() {
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

	private int storeItemStack(ItemStack itemStackIn) {
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

	private boolean add(int slot, ItemStack stack) {
		if (stack.isEmpty()) {
			return false;
		} else {
			try {
				if (stack.isDamaged()) {
					if (slot == -1) {
						slot = this.getFirstEmptyStack();
					}

					if (slot >= 0) {
						this.inventory.set(slot, stack.copy());
						this.inventory.get(slot).setAnimationsToGo(5);
						stack.setCount(0);
						return true;
					} else {
						return false;
					}
				} else {
					int i;
					while (true) {
						i = stack.getCount();
						if (slot == -1) {
							stack.setCount(this.storePartialItemStack(stack));
						} else {
							stack.setCount(this.addResource(slot, stack));
						}

						if (stack.isEmpty() || stack.getCount() >= i) {
							break;
						}
					}
					return stack.getCount() < i;
				}
			} catch (Throwable throwable) {
				CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Adding item to inventory");
				CrashReportCategory crashreportcategory = crashreport.makeCategory("Item being added");
				crashreportcategory.addDetail("Registry Name", () -> String.valueOf(stack.getItem().getRegistryName()));
				crashreportcategory.addDetail("Item Class", () -> stack.getItem().getClass().getName());
				crashreportcategory.addDetail("Item ID", Item.getIdFromItem(stack.getItem()));
				crashreportcategory.addDetail("Item data", stack.getDamage());
				crashreportcategory.addDetail("Item name", () -> {
					return stack.getDisplayName().getString();
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
		ItemStackHelper.saveAllItems(compound, this.inventory);
		return compound;
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void read(CompoundNBT compound) {
		super.read(compound);
		if (compound.contains("PedestalItem")) {
			CompoundNBT pedestalItemNBT = compound.getCompound("PedestalItem");
			setPedestalItem(ItemStack.read(pedestalItemNBT), false);
		} else if (hasWorld() && getWorld().isRemote) {
			MinecraftForge.EVENT_BUS.post(new EnderPedestalUpdateEvent(ItemStack.EMPTY, pos, true));
		}
		if (compound.contains("ItemOwner")) {
			setItemOwner(world.getPlayerByUuid(compound.getUniqueId("ItemOwner")));
		}

		this.inventory = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);

		ItemStackHelper.loadAllItems(compound, this.inventory);
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

	public NonNullList<ItemStack> getItems() {
		return this.inventory;
	}

	public void setItems(NonNullList<ItemStack> itemsIn) {
		this.inventory = NonNullList.<ItemStack>withSize(27, ItemStack.EMPTY);

		for (int i = 0; i < itemsIn.size(); i++) {
			if (i < this.inventory.size()) {
				this.getItems().set(i, itemsIn.get(i));
			}
		}
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		this.getItems().set(index, stack);
		if (stack.getCount() > this.getInventoryStackLimit()) {
			stack.setCount(this.getInventoryStackLimit());
		}

		this.markDirty();
	}

	@SubscribeEvent
	public void onPedestalChanged(EnderPedestalUpdateEvent event) {
		if ((event.ping || (hasWorld() && getWorld().isRemote)) && event.position.getX() == pos.getX()
				&& event.position.getY() == pos.getY() && event.position.getZ() == pos.getZ()) {
			setPedestalItem(event.pedestalItem, event.ping);
		}
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(inventory, index);
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack item) {
		return false;
	}

	public static class EnderPedestalUpdateEvent extends Event {
		public ItemStack pedestalItem;
		public BlockPos position;
		public boolean ping;

		public EnderPedestalUpdateEvent(ItemStack item, BlockPos pos, boolean ping) {
			this.position = pos;
			this.pedestalItem = item;
			this.ping = ping;
		}
	}
}
