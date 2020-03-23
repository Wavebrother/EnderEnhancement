package wavebrother.enderEnhancement.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import wavebrother.enderEnhancement.common.item.EndermanAgitator;
import wavebrother.enderEnhancement.common.item.ItemAccumulator;
import wavebrother.enderEnhancement.common.tiles.EnderPedestalTileEntity;

@SuppressWarnings("deprecation")
@OnlyIn(Dist.CLIENT)
public class EnderPedestalTER extends TileEntityRenderer<EnderPedestalTileEntity> {

	public EnderPedestalTER(TileEntityRendererDispatcher disp) {
		super(disp);
	}

	public void func_225616_a_(EnderPedestalTileEntity pedestal, float float1, MatrixStack matrix,
			IRenderTypeBuffer buffer, int int1, int int2) {
		ItemStack itemstack = pedestal.getPedestalItem();
		if (!itemstack.isEmpty()) {
			matrix.func_227860_a_();
			matrix.func_227861_a_(0.5D, 1.0D, 0.5D);
			if (doesRotation(itemstack)) {
				matrix.func_227863_a_(
						Vector3f.field_229179_b_.func_229187_a_((float) (pedestal.getWorld().getGameTime() % 360)));
				matrix.func_227863_a_(
						Vector3f.field_229181_d_.func_229187_a_((float) (pedestal.getWorld().getGameTime() % 360)));
			}
			Minecraft.getInstance().getItemRenderer().func_229110_a_(itemstack,
					ItemCameraTransforms.TransformType.FIXED, int1, OverlayTexture.field_229196_a_, matrix, buffer);
			matrix.func_227865_b_();
		}

	}
//
//	public void render(EnderPedestalTileEntity te, double x, double y, double z, float partialTicks, int destroyStage) {
//		ItemStack itemstack = te.getPedestalItem();
//		if (itemstack != null && itemstack != ItemStack.EMPTY) {
//			GlStateManager.func_227626_N_();
//			GlStateManager.func_227688_c_((float) x + 0.5F, (float) y + 1, (float) z + 0.5F);
//			if (checkRotation(itemstack)) {
//				float rotation = (float) (te.getWorld().getGameTime() % 160);
//				GlStateManager.func_227689_c_(360f * rotation / 160f, 0, 1, 0);
//			}
//			Minecraft.getInstance().getItemRenderer().renderItem(itemstack, ItemCameraTransforms.TransformType.FIXED);
//			GlStateManager.func_227627_O_();
//		}
//
//	}

	private boolean doesRotation(ItemStack item) {
		if (item.getItem() instanceof EndermanAgitator) {
			return true;
		}
		if (item.getItem() instanceof ItemAccumulator) {
			return item.hasTag() && item.getTag().contains(ItemAccumulator.accumulatorTag)
					&& item.getTag().getBoolean(ItemAccumulator.accumulatorTag);
		}
		return false;
	}
}