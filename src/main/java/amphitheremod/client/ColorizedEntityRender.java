package amphitheremod.client;

import amphitheremod.util.EnumCustomColor;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ColorizedEntityRender {
    private static final EnumCustomColor[] COLORS = new EnumCustomColor[]{EnumCustomColor.WHITE, EnumCustomColor.RED, EnumCustomColor.ORANGE, EnumCustomColor.YELLOW, EnumCustomColor.LIME, EnumCustomColor.CYAN, EnumCustomColor.BLUE, EnumCustomColor.DEEP_BLUE, EnumCustomColor.PURPLE, EnumCustomColor.PINK, EnumCustomColor.MAGENTA, EnumCustomColor.BLACK};
    private boolean isCustomColored = false;

    @SubscribeEvent
    public void onRenderLivingPre(RenderLivingEvent.Pre<EntityLivingBase> event) {
        EntityLivingBase entity = event.getEntity();
        isCustomColored = false;
        String customName = TextFormatting.getTextWithoutFormattingCodes(entity.getName());

        if(customName != null) {
            if (customName.equals("rain")) {
                isCustomColored = true;
                applyBrightRainbowEffect(entity);
            }
        }
    }

    private void applyBrightRainbowEffect(EntityLivingBase entity) {
        int ticks = entity.ticksExisted;
        int entityId = entity.getEntityId();
        int colorIndex = (ticks / 25 + entityId) % COLORS.length;
        int nextColorIndex = (colorIndex + 1) % COLORS.length;
        float interpolationFactor = (float) (ticks % 25) / 25.0F;
        float[] color1 = EnumCustomColor.getColorRgb(COLORS[colorIndex]);
        float[] color2 = EnumCustomColor.getColorRgb(COLORS[nextColorIndex]);
        applyInterpolatedColor(color1, color2, interpolationFactor);
    }

    private void applyInterpolatedColor(float[] rgb1, float[] rgb2, float factor) {
        float r = rgb1[0] * (1.0F - factor) + rgb2[0] * factor;
        float g = rgb1[1] * (1.0F - factor) + rgb2[1] * factor;
        float b = rgb1[2] * (1.0F - factor) + rgb2[2] * factor;
        GlStateManager.pushMatrix();
        GlStateManager.color(r, g, b, 1.0F);
    }

    @SubscribeEvent
    public void onRenderLivingPost(RenderLivingEvent.Post<EntityLivingBase> event) {
        if (isCustomColored) {
            GlStateManager.popMatrix();
            isCustomColored = false;
        }
    }
}
