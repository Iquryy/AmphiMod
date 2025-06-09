package amphitheremod.mixin.common;

import com.github.alexthe666.iceandfire.entity.EntityAmphithere;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Mixin(EntityAmphithere.class)
public abstract class AmphiVariants {
    // Weights
    @Unique private static final int[] WEIGHTS_SPAWN = {80, 5, 5, 9}; //Normal, added, gem added, gem
    @Unique private static final int[] WEIGHTS_CHILD = {60, 10, 10, 20}; //Normal, added, gem added, gem
    @Unique private static final int WEIGHTS_TOTAL_SPAWN = Arrays.stream(WEIGHTS_SPAWN).sum();
    @Unique private static final int WEIGHTS_TOTAL_CHILD = Arrays.stream(WEIGHTS_CHILD).sum();

    // Variant ids
    @Unique private static final List<List<Integer>> VARIANT_IDS = Arrays.asList(
            Arrays.asList(0, 1, 2, 3, 4, 5),                        //Normal
            Arrays.asList(7, 8, 9),                                 //Added
            Arrays.asList(10, 11),                                  //Gem added
            Arrays.asList(12, 13, 14, 15, 16, 17, 18, 19, 20)       //Gem
    );

    @Unique private static final int RAINBOW = 6;

    @WrapOperation(method = "onInitialSpawn", at = @At(value = "INVOKE", target = "Lcom/github/alexthe666/iceandfire/entity/EntityAmphithere;setVariant(I)V", remap = false))
    public void amphimod_spawnWithOtherVariants(EntityAmphithere amphithere, int variant, Operation<Void> original) {
        original.call(amphithere, amphimod$rollVariant(amphithere.getRNG(), false));
    }

    @WrapOperation(method = "createChild", at = @At(value = "INVOKE", target = "Lcom/github/alexthe666/iceandfire/entity/EntityAmphithere;setVariant(I)V", remap = false))
    public void amphiMod_createChildWithOtherVariants(EntityAmphithere amphithere, int variant, Operation<Void> original) {
        original.call(amphithere, amphimod$rollVariant(amphithere.getRNG(), true));
    }

    @Unique private static int amphimod$rollVariant(Random rand, boolean isFromBreed) {
        if (rand.nextInt(100) == 0 && isFromBreed) return RAINBOW;

        int randRoll = rand.nextInt(isFromBreed ? WEIGHTS_TOTAL_CHILD : WEIGHTS_TOTAL_SPAWN);
        int[] weights = isFromBreed ? WEIGHTS_CHILD : WEIGHTS_SPAWN;

        //Weighted roll
        for (int i = 0; i < weights.length; i++) {
            randRoll -= weights[i];
            if (randRoll < 0) return amphimod$getRandomEntryForAmphiType(rand, i);
        }

        //This should never be called if the weighted roll works correctly
        return amphimod$getRandomEntryForAmphiType(rand, VARIANT_IDS.size()-1);
    }

    @Unique private static int amphimod$getRandomEntryForAmphiType(Random rand, int type) {
        List<Integer> group = VARIANT_IDS.get(type);
        return group.get(rand.nextInt(group.size()));
    }
}