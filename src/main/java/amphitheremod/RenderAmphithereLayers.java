package amphitheremod;

import com.github.alexthe666.iceandfire.entity.EntityAmphithere;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

import static amphitheremod.ResourceLocation.*;

public class RenderAmphithereLayers extends RenderLiving<EntityAmphithere> {
    public RenderAmphithereLayers(RenderManager renderManager, ModelBase model) {
        super(renderManager, model, 1.0F);
        this.addLayer(new LayerAmphithereEyes(this));
        this.addLayer(new LayerGender(this));
        this.addLayer(new LayerMouth(this));
    }

    @Nullable @Override protected ResourceLocation getEntityTexture(EntityAmphithere amphithere) {
        return null;
    }

    @SideOnly(Side.CLIENT)
    public static class LayerAmphithereEyes implements LayerRenderer<EntityAmphithere> {
        static List<Integer> yellowEyes = Arrays.asList(0, 1, 2, 3, 4, 5, 6);
        static List<Integer> pinkEyes = Arrays.asList(8, 14, 16);
        static List<Integer> limeEyes = Arrays.asList(9, 10, 12, 13);
        static List<Integer> lightBlueEyes = Arrays.asList(11, 19, 20);
        static List<Integer> magentaEyes = Arrays.asList(18);
        static List<Integer> orangeEyes = Arrays.asList(15);
        static List<Integer> purpleEyes = Arrays.asList(17);
        private final RenderLiving<EntityAmphithere> render;

        public LayerAmphithereEyes(RenderLiving<EntityAmphithere> renderIn) {
            this.render = renderIn;
        }

        @Override public void doRenderLayer(EntityAmphithere amphithere, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
            if (amphithere.isBlinking() || amphithere.isInvisible()) {
                return;
            }
            String customName = amphithere.getCustomNameTag();
            if (customName.equals("s") || customName.equals("w")) {
                return;
            }

            int variantNum = amphithere.getVariant();
            ResourceLocation eyeTexture = EMPTY;
            if (yellowEyes.contains(variantNum)) {
                eyeTexture = YELLOW_EYES;
            } else if (pinkEyes.contains(variantNum)) {
                eyeTexture = PINK_EYES;
            } else if (limeEyes.contains(variantNum)) {
                eyeTexture = LIME_EYES;
            } else if (lightBlueEyes.contains(variantNum)) {
                eyeTexture = LIGHT_BLUE_EYES;
            } else if (magentaEyes.contains(variantNum)) {
                eyeTexture = MAGENTA_EYES;
            } else if (orangeEyes.contains(variantNum)) {
                eyeTexture = ORANGE_EYES;
            } else if (purpleEyes.contains(variantNum)) {
                eyeTexture = PURPLE_EYES;
            }
            if (eyeTexture == EMPTY) {
                return;
            }

            this.render.bindTexture(eyeTexture);
            GlStateManager.pushMatrix();
            boolean isChild = amphithere.isChild();
            if (isChild)
                GlStateManager.translate(0.0F, -0.2F, 0.0F);
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
            boolean wasDepthMaskEnabled = GL11.glGetBoolean(GL11.GL_DEPTH_WRITEMASK);
            GlStateManager.depthMask(!amphithere.isInvisible());
            float lastBrightnessX = OpenGlHelper.lastBrightnessX;
            float lastBrightnessY = OpenGlHelper.lastBrightnessY;
            int fullBright = 0xF000F0;
            int j_light = fullBright % 65536;
            int k_light = fullBright / 65536;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) j_light, (float) k_light);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
            this.render.getMainModel().render(amphithere, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            Minecraft.getMinecraft().entityRenderer.setupFogColor(false);
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lastBrightnessX, lastBrightnessY);
            GlStateManager.depthMask(wasDepthMaskEnabled);
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }

        @Override public boolean shouldCombineTextures() {
            return false;
        }
    }

    @SideOnly(Side.CLIENT)
    public static class LayerGender extends AbstractAmphithereLayer {
        public LayerGender(RenderLiving<EntityAmphithere> rendererIn) {
            super(rendererIn);
        }

        @Override protected ResourceLocation getTextureToBind(EntityAmphithere amphithere) {
            if (!(amphithere instanceof IAmphithereData)) {
                return EMPTY;
            }
            IAmphithereData data = (IAmphithereData) amphithere;
            ResourceLocation r;
            if (amphithere.getVariant() == 20 || amphithere.getVariant() == 21) {
                r = EMPTY;
            } else {
                r = !data.getGender() ? MALE : FEMALE;
            }
            String customName = amphithere.getCustomNameTag();
            if (customName.equals("s") || customName.equals("w")) {
                r = EMPTY;
            }
            return r;
        }
    }

    @SideOnly(Side.CLIENT)
    public static class LayerMouth extends AbstractAmphithereLayer {

        public LayerMouth(RenderLiving<EntityAmphithere> rendererIn) {
            super(rendererIn);
        }

        @Override protected ResourceLocation getTextureToBind(EntityAmphithere amphithere) {
            ResourceLocation r;
            if (amphithere.getVariant() == 19 || amphithere.getVariant() == 20) {
                r = EMPTY;
            } else {
                r = MOUTH;
            }
            String customName = amphithere.getCustomNameTag();
            if (customName.equals("s") || customName.equals("w")) {
                r = EMPTY;
            }
            return r;
        }
    }

    @SideOnly(Side.CLIENT)
    public abstract static class AbstractAmphithereLayer implements LayerRenderer<EntityAmphithere> {
        protected final RenderLiving<EntityAmphithere> renderer;

        public AbstractAmphithereLayer(RenderLiving<EntityAmphithere> rendererIn) {
            this.renderer = rendererIn;
        }

        protected abstract ResourceLocation getTextureToBind(EntityAmphithere amphithere);

        @Override public void doRenderLayer(EntityAmphithere amphithere, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
            if (amphithere.isInvisible())
                return;
            ResourceLocation texture = this.getTextureToBind(amphithere);
            if (texture == null || texture == EMPTY)
                return;
            this.renderer.bindTexture(texture);
            GlStateManager.pushMatrix();
            boolean isChild = amphithere.isChild();
            if (isChild)
                GlStateManager.translate(0.0F, -0.2F, 0.0F);
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
}