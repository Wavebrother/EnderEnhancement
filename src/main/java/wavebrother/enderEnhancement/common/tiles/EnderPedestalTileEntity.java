package wavebrother.enderEnhancement.common.tiles;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.apache.logging.log4j.LogManager;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.IChestLid;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import wavebrother.enderEnhancement.common.blocks.EnderPedestal;
import wavebrother.enderEnhancement.common.containers.EnderPedestalContainer;
import wavebrother.enderEnhancement.common.init.ModTileEntities;

public class EnderPedestalTileEntity
extends LockableLootTileEntity implements IChestLid, ITickableTileEntity
{
    private NonNullList<ItemStack> chestContents;

    protected float lidAngle;

    protected float prevLidAngle;

	private ItemStack pedestalItem = ItemStack.EMPTY;
	private PlayerEntity itemOwner;
    protected int numPlayersUsing;

    private int ticksSinceSync;

    protected EnderPedestalTileEntity(TileEntityType<?> typeIn)
    {
        super(typeIn);

        this.chestContents = NonNullList.<ItemStack>withSize(27, ItemStack.EMPTY);
    }
//
//    public EnderPedestalTileEntity()
//    {
//        this(ModTileEntities.enderPedestal);
//    }

    @Override
    public int getSizeInventory()
    {
        return this.getItems().size();
    }

    @Override
    public boolean isEmpty()
    {
        for (ItemStack itemstack : this.chestContents)
        {
            if (!itemstack.isEmpty())
            {
                return false;
            }
        }

        return true;
    }

    @Override
    protected ITextComponent getDefaultName()
    {
        return new TranslationTextComponent("container.chest");
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
        if (!this.checkLootAndWrite(compound))
        {
            ItemStackHelper.saveAllItems(compound, this.chestContents);
        }
		Class<?> world = Null.class;
		if(getWorld() != null)
			world = getWorld().getClass();
		LogManager.getLogger().debug("WRITE: world: " + world + ", " + compound);
		return compound;
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void read(CompoundNBT compound) {
		super.read(compound);
		if (compound.contains("PedestalItem")) {
			CompoundNBT pedestalItemNBT = compound.getCompound("PedestalItem");
			setPedestalItem(ItemStack.read(pedestalItemNBT));
		}
		if (compound.contains("ItemOwner")) {
			setItemOwner(world.getPlayerByUuid(compound.getUniqueId("ItemOwner")));
		}

        this.chestContents = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);

        if (!this.checkLootAndRead(compound))
        {
            ItemStackHelper.loadAllItems(compound, this.chestContents);
        }
		Class<?> world = Null.class;
		if(getWorld() != null)
			world = getWorld().getClass();
		LogManager.getLogger().debug("READ: world: " + world + ", " + compound);
	}

    @Override
    public void tick()
    {
        int i = this.pos.getX();
        int j = this.pos.getY();
        int k = this.pos.getZ();
        ++this.ticksSinceSync;
        this.numPlayersUsing = getNumberOfPlayersUsing(this.world, this, this.ticksSinceSync, i, j, k, this.numPlayersUsing);
        this.prevLidAngle = this.lidAngle;
        float f = 0.1F;
        if (this.numPlayersUsing == 0 && this.lidAngle > 0.0F || this.numPlayersUsing > 0 && this.lidAngle < 1.0F)
        {
            float f1 = this.lidAngle;
            if (this.numPlayersUsing > 0)
            {
                this.lidAngle += 0.1F;
            }
            else
            {
                this.lidAngle -= 0.1F;
            }

            if (this.lidAngle > 1.0F)
            {
                this.lidAngle = 1.0F;
            }

            if (this.lidAngle < 0.0F)
            {
                this.lidAngle = 0.0F;
            }
        }

    }

    public static int getNumberOfPlayersUsing(World worldIn, LockableTileEntity lockableTileEntity, int ticksSinceSync, int x, int y, int z, int numPlayersUsing)
    {
        if (!worldIn.isRemote && numPlayersUsing != 0 && (ticksSinceSync + x + y + z) % 200 == 0)
        {
            numPlayersUsing = getNumberOfPlayersUsing(worldIn, lockableTileEntity, x, y, z);
        }

        return numPlayersUsing;
    }

    public static int getNumberOfPlayersUsing(World world, LockableTileEntity lockableTileEntity, int x, int y, int z)
    {
        int i = 0;

        for (PlayerEntity playerentity : world.getEntitiesWithinAABB(PlayerEntity.class, new AxisAlignedBB((double) ((float) x - 5.0F), (double) ((float) y - 5.0F), (double) ((float) z - 5.0F), (double) ((float) (x + 1) + 5.0F), (double) ((float) (y + 1) + 5.0F), (double) ((float) (z + 1) + 5.0F))))
        {
            if (playerentity.openContainer instanceof EnderPedestalContainer)
            {
                ++i;
            }
        }

        return i;
    }

    @Override
    public boolean receiveClientEvent(int id, int type)
    {
        if (id == 1)
        {
            this.numPlayersUsing = type;
            return true;
        }
        else
        {
            return super.receiveClientEvent(id, type);
        }
    }

    @Override
    public void openInventory(PlayerEntity player)
    {
        if (!player.isSpectator())
        {
            if (this.numPlayersUsing < 0)
            {
                this.numPlayersUsing = 0;
            }

            ++this.numPlayersUsing;
            this.onOpenOrClose();
        }
    }

    @Override
    public void closeInventory(PlayerEntity player)
    {
        if (!player.isSpectator())
        {
            --this.numPlayersUsing;
            this.onOpenOrClose();
        }
    }

    protected void onOpenOrClose()
    {
        Block block = this.getBlockState().getBlock();

        if (block instanceof EnderPedestal)
        {
            this.world.addBlockEvent(this.pos, block, 1, this.numPlayersUsing);
            this.world.notifyNeighborsOfStateChange(this.pos, block);
        }
    }

    @Override
    public NonNullList<ItemStack> getItems()
    {
        return this.chestContents;
    }

    @Override
    public void setItems(NonNullList<ItemStack> itemsIn)
    {
        this.chestContents = NonNullList.<ItemStack>withSize(27, ItemStack.EMPTY);

        for (int i = 0; i < itemsIn.size(); i++)
        {
            if (i < this.chestContents.size())
            {
                this.getItems().set(i, itemsIn.get(i));
            }
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public float getLidAngle(float partialTicks)
    {
        return MathHelper.lerp(partialTicks, this.prevLidAngle, this.lidAngle);
    }

    public static int getPlayersUsing(IBlockReader reader, BlockPos posIn)
    {
        BlockState blockstate = reader.getBlockState(posIn);
        if (blockstate.hasTileEntity())
        {
            TileEntity tileentity = reader.getTileEntity(posIn);
            if (tileentity instanceof EnderPedestalTileEntity)
            {
                return ((EnderPedestalTileEntity) tileentity).numPlayersUsing;
            }
        }

        return 0;
    }

    @Override
    protected Container createMenu(int windowId, PlayerInventory playerInventory)
    {
        return EnderPedestalContainer.createIronContainer(windowId, playerInventory, this);
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
		chestContents.clear();
	}

}
//extends LockableLootTileEntity implements IChestLid, ITickableTileEntity
//{
//    private NonNullList<ItemStack> chestContents;
//
//    protected float lidAngle;
//
//    protected float prevLidAngle;
//
//    private int ticksSinceSync;
//
////    @Override
////    public void tick()
////    {
////        int i = this.pos.getX();
////        int j = this.pos.getY();
////        int k = this.pos.getZ();
////        ++this.ticksSinceSync;
////        this.numPlayersUsing = getNumberOfPlayersUsing(this.world, this, this.ticksSinceSync, i, j, k, this.numPlayersUsing);
////        this.prevLidAngle = this.lidAngle;
////        float f = 0.1F;
////        if (this.numPlayersUsing > 0 && this.lidAngle == 0.0F)
////        {
////            this.playSound(SoundEvents.BLOCK_CHEST_OPEN);
////        }
////
////        if (this.numPlayersUsing == 0 && this.lidAngle > 0.0F || this.numPlayersUsing > 0 && this.lidAngle < 1.0F)
////        {
////            float f1 = this.lidAngle;
////            if (this.numPlayersUsing > 0)
////            {
////                this.lidAngle += 0.1F;
////            }
////            else
////            {
////                this.lidAngle -= 0.1F;
////            }
////
////            if (this.lidAngle > 1.0F)
////            {
////                this.lidAngle = 1.0F;
////            }
////
////            float f2 = 0.5F;
////            if (this.lidAngle < 0.5F && f1 >= 0.5F)
////            {
////                this.playSound(SoundEvents.BLOCK_CHEST_CLOSE);
////            }
////
////            if (this.lidAngle < 0.0F)
////            {
////                this.lidAngle = 0.0F;
////            }
////        }
////
////    }
//
//    public static int getNumberOfPlayersUsing(World worldIn, LockableTileEntity lockableTileEntity, int ticksSinceSync, int x, int y, int z, int numPlayersUsing)
//    {
//        if (!worldIn.isRemote && numPlayersUsing != 0 && (ticksSinceSync + x + y + z) % 200 == 0)
//        {
//            numPlayersUsing = getNumberOfPlayersUsing(worldIn, lockableTileEntity, x, y, z);
//        }
//
//        return numPlayersUsing;
//    }
//
//    public static int getNumberOfPlayersUsing(World world, LockableTileEntity lockableTileEntity, int x, int y, int z)
//    {
//        int i = 0;
//
//        for (PlayerEntity playerentity : world.getEntitiesWithinAABB(PlayerEntity.class, new AxisAlignedBB((double) ((float) x - 5.0F), (double) ((float) y - 5.0F), (double) ((float) z - 5.0F), (double) ((float) (x + 1) + 5.0F), (double) ((float) (y + 1) + 5.0F), (double) ((float) (z + 1) + 5.0F))))
//        {
//            if (playerentity.openContainer instanceof EnderPedestalContainer)
//            {
//                ++i;
//            }
//        }
//
//        return i;
//    }
//
//    @Override
//    @OnlyIn(Dist.CLIENT)
//    public float getLidAngle(float partialTicks)
//    {
//        return MathHelper.lerp(partialTicks, this.prevLidAngle, this.lidAngle);
//    }
//
//    public static int getPlayersUsing(IBlockReader reader, BlockPos posIn)
//    {
//        BlockState blockstate = reader.getBlockState(posIn);
//        if (blockstate.hasTileEntity())
//        {
//            TileEntity tileentity = reader.getTileEntity(posIn);
//            if (tileentity instanceof EnderPedestalTileEntity)
//            {
//                return ((EnderPedestalTileEntity) tileentity).numPlayersUsing;
//            }
//        }
//
//        return 0;
//    }
//
//    @Override
//    protected Container createMenu(int windowId, PlayerInventory playerInventory)
//    {
//        return EnderPedestalContainer.createIronContainer(windowId, playerInventory, this);
//    }
//
//// extends LockableLootTileEntity
////		implements IInventory, INamedContainerProvider, ITickableTileEntity {
//
//	private NonNullList<ItemStack> inventory = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
//
//	private ItemStack pedestalItem = ItemStack.EMPTY;
//	private PlayerEntity itemOwner;
//    protected int numPlayersUsing;
//
//	public EnderPedestalTileEntity() {
//		super(ModTileEntities.enderPedestal);
//	}
//	@Override
//	public ITextComponent getDefaultName() {
//		return new StringTextComponent(getType().getRegistryName().getPath());
//	}
//
//	@Override
//	public ITextComponent getDisplayName() {
//		return getDefaultName();
//	}
//
//	@Override
//	public void tick() {
//
//	}
//
//	public PlayerEntity getItemOwner() {
//		return this.itemOwner;
//	}
//
//	public void setItemOwner(PlayerEntity itemOwnerIn) {
//		this.itemOwner = itemOwnerIn;
//		this.markDirty();
//	}
//
//	public ItemStack getPedestalItem() {
//		return this.pedestalItem;
//	}
//
//	public void setPedestalItem(ItemStack pedestalItem) {
//		this.pedestalItem = pedestalItem;
//		this.markDirty();
//	}
//
//	public void clearPedestal() {
//		pedestalItem = ItemStack.EMPTY;
//	}
//
//	public void clearInventory() {
//		inventory.clear();
//	}
//
//	@Override
//	public void clear() {
//		clearPedestal();
//		clearInventory();
//	}
//
//	@Override
//	public int getSizeInventory() {
//		return 27;
//	}
//
//	@Override
//	public boolean isUsableByPlayer(PlayerEntity player) {
//		if (!hasWorld()) {
//		} else if (getWorld().getBlockState(getPos()).has(EnderPedestal.HAS_ACCUMULATOR)
//				|| getWorld().getBlockState(getPos()).has(EnderPedestal.HAS_AGITATOR))
//			return false;
//		return player == itemOwner;
//	}
//
//	/**
//	 * Returns the first item stack that is empty.
//	 */
//	public int getFirstEmptyStack() {
//		for (int i = 0; i < this.inventory.size(); ++i) {
//			if (this.inventory.get(i).isEmpty()) {
//				return i;
//			}
//		}
//
//		return -1;
//	}
//
//	private int storePartialItemStack(ItemStack itemStackIn) {
//		int i = this.storeItemStack(itemStackIn);
//		if (i == -1) {
//			i = this.getFirstEmptyStack();
//		}
//
//		return i == -1 ? itemStackIn.getCount() : this.addResource(i, itemStackIn);
//	}
//
//	public int storeItemStack(ItemStack itemStackIn) {
//		for (int i = 0; i < this.inventory.size(); ++i) {
//			if (this.canMergeStacks(this.inventory.get(i), itemStackIn)) {
//				return i;
//			}
//		}
//
//		return -1;
//	}
//
//	private boolean canMergeStacks(ItemStack stack1, ItemStack stack2) {
//		return !stack1.isEmpty() && this.stackEqualExact(stack1, stack2) && stack1.isStackable()
//				&& stack1.getCount() < stack1.getMaxStackSize() && stack1.getCount() < this.getInventoryStackLimit();
//	}
//
//	private boolean stackEqualExact(ItemStack stack1, ItemStack stack2) {
//		return stack1.getItem() == stack2.getItem() && ItemStack.areItemStackTagsEqual(stack1, stack2);
//	}
//
//	private int addResource(int p_191973_1_, ItemStack p_191973_2_) {
//		@SuppressWarnings("unused")
//		Item item = p_191973_2_.getItem();
//		int i = p_191973_2_.getCount();
//		ItemStack itemstack = this.getStackInSlot(p_191973_1_);
//		if (itemstack.isEmpty()) {
//			itemstack = p_191973_2_.copy(); // Forge: Replace Item clone above to preserve item capabilities when
//											// picking the item up.
//			itemstack.setCount(0);
//			if (p_191973_2_.hasTag()) {
//				itemstack.setTag(p_191973_2_.getTag().copy());
//			}
//
//			this.setInventorySlotContents(p_191973_1_, itemstack);
//		}
//
//		int j = i;
//		if (i > itemstack.getMaxStackSize() - itemstack.getCount()) {
//			j = itemstack.getMaxStackSize() - itemstack.getCount();
//		}
//
//		if (j > this.getInventoryStackLimit() - itemstack.getCount()) {
//			j = this.getInventoryStackLimit() - itemstack.getCount();
//		}
//
//		if (j == 0) {
//			return i;
//		} else {
//			i = i - j;
//			itemstack.grow(j);
//			itemstack.setAnimationsToGo(5);
//			return i;
//		}
//	}
//
//	public boolean addItemStackToInventory(ItemStack itemStackIn) {
//		return this.add(-1, itemStackIn);
//	}
//
//	public boolean add(int slot, ItemStack stack) {
//		LogManager.getLogger().debug(stack);
//		if (stack.isEmpty()) {
//			return false;
//		} else {
//			try {
//				if (stack.isDamaged()) {
//					if (slot == -1) {
//						slot = this.getFirstEmptyStack();
//					}
//
//					if (slot >= 0) {
//						this.inventory.set(slot, stack.copy());
//						this.inventory.get(slot).setAnimationsToGo(5);
//						stack.setCount(0);
//						return true;
//					} else {
//						return false;
//					}
//				} else {
//					int i;
//					while (true) {
//						i = stack.getCount();
//						if (slot == -1) {
//							stack.setCount(this.storePartialItemStack(stack));
//						} else {
//							stack.setCount(this.addResource(slot, stack));
//						}
//
//						if (stack.isEmpty() || stack.getCount() >= i) {
//							break;
//						}
//					}
//					return stack.getCount() < i;
//				}
//			} catch (Throwable throwable) {
//				CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Adding item to inventory");
//				CrashReportCategory crashreportcategory = crashreport.makeCategory("Item being added");
//				crashreportcategory.addDetail("Registry Name",
//						() -> String.valueOf(stack.getItem().getRegistryName()));
//				crashreportcategory.addDetail("Item Class", () -> stack.getItem().getClass().getName());
//				crashreportcategory.addDetail("Item ID", Item.getIdFromItem(stack.getItem()));
//				crashreportcategory.addDetail("Item data", stack.getDamage());
//				crashreportcategory.addDetail("Item name", () -> {
//					return stack.getDisplayName().getString();
//				});
//				throw new ReportedException(crashreport);
//			}
//		}
//	}
//
//	public CompoundNBT write(CompoundNBT compound) {
//		super.write(compound);
//		CompoundNBT pedestalItemnbt = new CompoundNBT();
//		if (!pedestalItem.isEmpty()) {
//			pedestalItem.write(pedestalItemnbt);
//		}
//		if (itemOwner != null)
//			compound.putUniqueId("ItemOwner", getItemOwner().getUniqueID());
//
//		compound.put("PedestalItem", pedestalItemnbt);
//        if (!this.checkLootAndWrite(compound))
//        {
//            ItemStackHelper.saveAllItems(compound, this.inventory);
//        }
//		Class<?> world = Null.class;
//		if(getWorld() != null)
//			world = getWorld().getClass();
//		LogManager.getLogger().debug("WRITE: world: " + world + ", " + compound);
//		return compound;
//	}
//
//	/**
//	 * (abstract) Protected helper method to read subclass entity data from NBT.
//	 */
//	public void read(CompoundNBT compound) {
//		super.read(compound);
//		if (compound.contains("PedestalItem")) {
//			CompoundNBT pedestalItemNBT = compound.getCompound("ArmorItems");
//			setPedestalItem(ItemStack.read(pedestalItemNBT));
//		}
//		if (compound.contains("ItemOwner")) {
//			setItemOwner(world.getPlayerByUuid(compound.getUniqueId("ItemOwner")));
//		}
//
//        this.inventory = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
//
//        if (!this.checkLootAndRead(compound))
//        {
//            ItemStackHelper.loadAllItems(compound, this.inventory);
//        }
//		Class<?> world = Null.class;
//		if(getWorld() != null)
//			world = getWorld().getClass();
//		LogManager.getLogger().debug("READ: world: " + world + ", " + compound);
//	}
//
//	@Override
//	public boolean isEmpty() {
//		for (ItemStack itemstack : this.inventory) {
//			if (!itemstack.isEmpty()) {
//				return false;
//			}
//		}
//
//		return true;
//
//	}
//
//	@Override
//	public ItemStack getStackInSlot(int index) {
//		return inventory.get(index);
//	}
//
//	@Override
//	public ItemStack decrStackSize(int index, int count) {
//		ItemStack itemstack = ItemStackHelper.getAndSplit(this.getItems(), index, count);
//		if (!itemstack.isEmpty()) {
//			this.markDirty();
//		}
//
//		return itemstack;
//	}
//
//    @Override
//    public NonNullList<ItemStack> getItems()
//    {
//        return this.inventory;
//    }
//
//    @Override
//    public void setItems(NonNullList<ItemStack> itemsIn)
//    {
//        this.inventory = NonNullList.<ItemStack>withSize(27, ItemStack.EMPTY);
//
//        for (int i = 0; i < itemsIn.size(); i++)
//        {
//            if (i < this.inventory.size())
//            {
//                this.getItems().set(i, itemsIn.get(i));
//            }
//        }
//    }
//
//	@Override
//	public ItemStack removeStackFromSlot(int index) {
//		return ItemStackHelper.getAndRemove(this.getItems(), index);
//	}
//
//	@Override
//	public void setInventorySlotContents(int index, ItemStack stack) {
//		this.getItems().set(index, stack);
//		if (stack.getCount() > this.getInventoryStackLimit()) {
//			stack.setCount(this.getInventoryStackLimit());
//		}
//
//		this.markDirty();
//	}
//
//    @Override
//    public boolean receiveClientEvent(int id, int type)
//    {
//        if (id == 1)
//        {
//            this.numPlayersUsing = type;
//            return true;
//        }
//        else
//        {
//            return super.receiveClientEvent(id, type);
//        }
//    }
//
//    @Override
//    public void openInventory(PlayerEntity player)
//    {
//        if (!player.isSpectator())
//        {
//            if (this.numPlayersUsing < 0)
//            {
//                this.numPlayersUsing = 0;
//            }
//
//            ++this.numPlayersUsing;
//            this.onOpenOrClose();
//        }
//    }
//
//    @Override
//    public void closeInventory(PlayerEntity player)
//    {
//        if (!player.isSpectator())
//        {
//            --this.numPlayersUsing;
//            this.onOpenOrClose();
//        }
//    }
//
//    protected void onOpenOrClose()
//    {
//        Block block = this.getBlockState().getBlock();
//
//        if (block instanceof EnderPedestal)
//        {
//            this.world.addBlockEvent(this.pos, block, 1, this.numPlayersUsing);
//            this.world.notifyNeighborsOfStateChange(this.pos, block);
//        }
//    }
//}
