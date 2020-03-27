package wavebrother.enderEnhancement.client.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import wavebrother.enderEnhancement.common.item.EndermanAgitator;
import wavebrother.enderEnhancement.common.item.ItemAccumulator;
import wavebrother.enderEnhancement.common.tiles.EnderPedestalTileEntity;

public class EnderPedestalTER extends TileEntitySpecialRenderer<EnderPedestalTileEntity> {
	public EnderPedestalTER() {
	}

	@Override
	public void render(EnderPedestalTileEntity te, double x, double y, double z, float partialTicks, int destroyStage,
			float alpha) {
		ItemStack itemstack = te.getPedestalItem();
		if (itemstack != null && itemstack != ItemStack.EMPTY) {
			GlStateManager.pushMatrix();
			GlStateManager.translate((float) x + 0.5F, (float) y + 1, (float) z + 0.5F);
			if (checkRotation(itemstack)) {
				float rotation = (float) (getWorld().getWorldTime() % 160);
				GlStateManager.rotate(360f * rotation / 160f, 0, 1, 0);
			}
			Minecraft.getMinecraft().getRenderItem().renderItem(itemstack, ItemCameraTransforms.TransformType.FIXED);
			GlStateManager.popMatrix();
		}

	}

	private boolean checkRotation(ItemStack item) {
		if (item.getItem() instanceof EndermanAgitator) {
			if (!item.hasTagCompound())
				return true;
			return !(item.getTagCompound().hasKey(EndermanAgitator.agitatorTag)
					&& item.getTagCompound().getBoolean(EndermanAgitator.agitatorTag));
		}
		if (item.getItem() instanceof ItemAccumulator) {
			return item.hasTagCompound() && item.getTagCompound().hasKey(ItemAccumulator.accumulatorTag)
					&& item.getTagCompound().getBoolean(ItemAccumulator.accumulatorTag);
		}
		return false;
	}
}