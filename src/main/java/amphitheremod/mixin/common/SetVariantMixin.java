package amphitheremod.mixin.common;

import com.github.alexthe666.iceandfire.entity.EntityAmphithere;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import java.util.Random;

@Mixin(EntityAmphithere.class)
public abstract class SetVariantMixin {
    // ON SPAWN (ADULT)
    @Unique private static final int NORMAL_CHANCE = 80;
    @Unique private static final int ADDED = 5;
    @Unique private static final int GEM_ADDED = 5;
    @Unique private static final int GEM = 9;
    // ON SPAWN (BABY)
    @Unique private static final int NORMAL_CHANCE_BABY = 60;
    @Unique private static final int ADDED_BABY = 10;
    @Unique private static final int GEM_ADDED_BABY = 10;
    @Unique private static final int GEM_BABY = 20;
    // Variant ranges
    @Unique private static final int[] NORMAL_VARIANTS = {0, 1, 2, 3, 4, 5};
    @Unique private static final int[] ADDED_VARIANTS = {7, 8, 9};
    @Unique private static final int[] GEM_ADDED_VARIANTS = {10, 11};
    @Unique private static final int[] GEM_VARIANTS = {12, 13, 14, 15, 16, 17, 18, 19, 20};
    @Unique private static final int RAINBOW = 6;

    @Redirect(method = "onInitialSpawn", at = @At(value = "INVOKE", target = "Lcom/github/alexthe666/iceandfire/entity/EntityAmphithere;setVariant(I)V"))
    void adult(EntityAmphithere amphithere, int variantArgument) {
        amphithere.setVariant(rollVariant(amphithere.getRNG(), NORMAL_CHANCE, ADDED, GEM_ADDED, GEM, amphithere));
    }

    @Redirect(method = "createChild", at = @At(value = "INVOKE", target = "Lcom/github/alexthe666/iceandfire/entity/EntityAmphithere;setVariant(I)V", remap = false))
    void baby(EntityAmphithere amphithere, int variantArgument) {
        amphithere.setVariant(rollVariant(amphithere.getRNG(), NORMAL_CHANCE_BABY, ADDED_BABY, GEM_ADDED_BABY, GEM_BABY, amphithere));
    }

    @Unique
    int rollVariant(Random randomInstance, int normalChance, int added, int gemAdded, int gem, EntityAmphithere amphi) {
        if (randomInstance.nextInt(100) == 0 && amphi.isChild()) {
            return RAINBOW;
        }

        int selectedVariant;
        int totalChance = normalChance + added + gemAdded + gem;
        int roll = randomInstance.nextInt(totalChance);
        if (roll < normalChance)
            selectedVariant = NORMAL_VARIANTS[randomInstance.nextInt(NORMAL_VARIANTS.length)];
        else if (roll < normalChance + added)
            selectedVariant = ADDED_VARIANTS[randomInstance.nextInt(ADDED_VARIANTS.length)];
        else if (roll < normalChance + added + gemAdded)
            selectedVariant = GEM_ADDED_VARIANTS[randomInstance.nextInt(GEM_ADDED_VARIANTS.length)];
        else
            selectedVariant = GEM_VARIANTS[randomInstance.nextInt(GEM_VARIANTS.length)];

        return selectedVariant;
    }
}