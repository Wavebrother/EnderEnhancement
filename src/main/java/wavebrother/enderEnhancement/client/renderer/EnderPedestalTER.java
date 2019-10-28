package wavebrother.enderEnhancement.client.renderer;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import wavebrother.enderEnhancement.EnderEnhancement;
import wavebrother.enderEnhancement.common.tiles.EnderPedestalTileEntity;

public class EnderPedestalTER extends TileEntityRenderer<EnderPedestalTileEntity> {
    public EnderPedestalTER() {
    }

    @Override
    public void render(EnderPedestalTileEntity te, double x, double y, double z, float partialTicks, int destroyStage) {
        ItemStack stack = te.getAgitator();
        if (! stack.isEmpty()) {
            GlStateManager.pushLightingAttributes();
            GlStateManager.pushMatrix();

            // Translate to the location of our tile entity
            GlStateManager.translated(x, y, z);
            GlStateManager.disableRescaleNormal();
            // Render our item
            renderItem(te);
            GlStateManager.popMatrix();
            GlStateManager.popAttributes();
        }

    }

    private void renderItem(EnderPedestalTileEntity te) {
        ItemStack stack = te.getAgitator();
        EnderEnhancement.getLogger().debug(stack);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.enableLighting();
        GlStateManager.pushMatrix();
        // Translate to the center of the block and .9 points higher
        GlStateManager.translated(.5, 2.5, .5);
        GlStateManager.scalef(.4f, .4f, .4f);
        float rotation = (float) (getWorld().getGameTime() % 80);
        GlStateManager.rotatef(360f * rotation / 80f, 0, 1, 0);
        Minecraft.getInstance().getItemRenderer().renderItem(stack, ItemCameraTransforms.TransformType.NONE);

        GlStateManager.popMatrix();
    }
}