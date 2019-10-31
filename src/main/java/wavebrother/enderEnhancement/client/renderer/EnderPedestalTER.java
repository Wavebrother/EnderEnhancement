package wavebrother.enderEnhancement.client.renderer;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import wavebrother.enderEnhancement.common.item.EndermanAgitator;
import wavebrother.enderEnhancement.common.item.ItemAccumulator;
import wavebrother.enderEnhancement.common.tiles.EnderPedestalTileEntity;

@SuppressWarnings("deprecation")
public class EnderPedestalTER extends TileEntityRenderer<EnderPedestalTileEntity> {
	public EnderPedestalTER() {
	}

	@Override
	public void render(EnderPedestalTileEntity te, double x, double y, double z, float partialTicks, int destroyStage) {
		ItemStack itemstack = te.getPedestalItem();
		if (itemstack != null && itemstack != ItemStack.EMPTY) {
			GlStateManager.pushMatrix();
			GlStateManager.translatef((float) x + 0.5F, (float) y + 1, (float) z + 0.5F);
			if (!(itemstack.hasTag() && itemstack.getTag().contains(EndermanAgitator.agitatorTag)
					&& itemstack.getTag().getBoolean(EndermanAgitator.agitatorTag))
					|| (itemstack.hasTag() && itemstack.getTag().contains(ItemAccumulator.accumulatorTag)
							&& itemstack.getTag().getBoolean(ItemAccumulator.accumulatorTag))) {
				float rotation = (float) (getWorld().getGameTime() % 160);
				GlStateManager.rotatef(360f * rotation / 160f, 0, 1, 0);
			}
			Minecraft.getInstance().getItemRenderer().renderItem(itemstack, ItemCameraTransforms.TransformType.FIXED);
			GlStateManager.popMatrix();
		}

	}
}