package amphitheremod.mixin.client;

import com.github.alexthe666.iceandfire.client.render.entity.RenderAmphithere;
import com.github.alexthe666.iceandfire.entity.EntityAmphithere;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static amphitheremod.ResourceLocation.*;

@Mixin(RenderAmphithere.class)
public class RenderVariantsMixin {
    @ModifyReturnValue(method = "getEntityTexture(Lcom/github/alexthe666/iceandfire/entity/EntityAmphithere;)Lnet/minecraft/util/ResourceLocation;", at = @At("RETURN"), remap = false)
    public ResourceLocation changeTexture(ResourceLocation original, @Local(argsOnly = true) EntityAmphithere entity) {
        String name = TextFormatting.getTextWithoutFormattingCodes(entity.getName());
        if(name != null) {
            if (name.equals("s") || name.equals("S"))
                return SKELETON_AMPHIT;
            if (name.equals("w") || name.equals("W"))
                return WITHER_SKELETON_AMPHIT;
            if (name.equals("Risky"))
                return RAINBOW;
        }

        switch (entity.getVariant()) {
            case 0: // DEFAULT
                return entity.isBlinking() ? BLUE_BLINK : BLUE;
            case 1:
                return entity.isBlinking() ? GREEN_BLINK : GREEN;
            case 2:
                return entity.isBlinking() ? OLIVE_BLINK : OLIVE;
            case 3:
                return entity.isBlinking() ? RED_BLINK : RED;
            case 4:
                return entity.isBlinking() ? YELLOW_BLINK : YELLOW;
            case 5:
                return entity.isBlinking() ? PURPLE_BLINK : PURPLE;
            case 6:
                return entity.isBlinking() ? RAINBOW_BLINK : RAINBOW;
            case 7: // ADDED
                return entity.isBlinking() ? BLACK_BLINK : BLACK;
            case 8:
                return entity.isBlinking() ? WHITE_BLINK : WHITE;
            case 9:
                return entity.isBlinking() ? RADISHE_BLINK : RADISHE;
            case 10: // GEM ADDED
                return entity.isBlinking() ? GEM_BLACK_BLINK : GEM_BLACK;
            case 11:
                return entity.isBlinking() ? GEM_WHITE_BLINK : GEM_WHITE;
            case 12: // GEM
                return entity.isBlinking() ? GEM_BLUE_BLINK : GEM_BLUE;
            case 13:
                return entity.isBlinking() ? GEM_CYAN_BLINK : GEM_CYAN;
            case 14:
                return entity.isBlinking() ? GEM_GREEN_BLINK : GEM_GREEN;
            case 15:
                return entity.isBlinking() ? GEM_LIME_BLINK : GEM_LIME;
            case 16:
                return entity.isBlinking() ? GEM_OLIVE_BLINK : GEM_OLIVE;
            case 17:
                return entity.isBlinking() ? GEM_RED_BLINK : GEM_RED;
            case 18:
                return entity.isBlinking() ? GEM_YELLOW_BLINK : GEM_YELLOW;
            case 19:
                return entity.isBlinking() ? GEM_PINK_BLINK : GEM_PINK;
            case 20:
                return entity.isBlinking() ? GEM_PURPLE_BLINK : GEM_PURPLE;
            case 21://  SKELETON
                return SKELETON_AMPHIT;
            case 22:
                return WITHER_SKELETON_AMPHIT;
            default:
                return entity.isBlinking() ? GREEN_BLINK : GREEN;
        }
    }
}