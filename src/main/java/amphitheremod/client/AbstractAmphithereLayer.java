package amphitheremod.client;

import com.github.alexthe666.iceandfire.entity.EntityAmphithere;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static amphitheremod.util.Refs.EMPTY;

@SideOnly(Side.CLIENT)
public abstract class AbstractAmphithereLayer implements LayerRenderer<EntityAmphithere> {
    protected final RenderLiving<EntityAmphithere> renderer;

    public AbstractAmphithereLayer(RenderLiving<EntityAmphithere> rendererIn) {
        this.renderer = rendererIn;
    }

    protected abstract ResourceLocation getTextureToBind(EntityAmphithere amphithere);

    @Override public void doRenderLayer(EntityAmphithere amphithere, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if (amphithere.isInvisible()) return;
        ResourceLocation texture = this.getTextureToBind(amphithere);
        if (texture == null || texture == EMPTY) return;
        this.renderer.bindTexture(texture);
        GlStateManager.pushMatrix();
        boolean isChild = amphithere.isChild();
        if (isChild) GlStateManager.translate(0.0F, -0.2F, 0.0F);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.renderer.getMainModel().render(amphithere, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    @Override public boolean shouldCombineTextures() {
        return false;
    }
}