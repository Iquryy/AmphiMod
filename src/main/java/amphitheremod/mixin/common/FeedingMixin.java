package amphitheremod.mixin.common;

import com.github.alexthe666.iceandfire.entity.EntityAmphithere;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;


@Mixin({EntityAmphithere.class})
public abstract class FeedingMixin {
    @ModifyConstant(method = {"processInteract"}, constant = {@Constant(floatValue = 5.0F)})
    private float feeding(float constant) {
        EntityAmphithere amphithere = (EntityAmphithere) (Object) this;
        float currentHp = amphithere.getHealth();
        float maxHp = amphithere.getMaxHealth();
        return calcHeal(maxHp, currentHp);
    }

    @Unique private static float calcHeal(float maxHp, float currentHp) {
        if (maxHp <= 0.0F) {
            return 0.0F;
        } else {
            currentHp = Math.max(0.0F, Math.min(currentHp, maxHp));
            float missingHp = maxHp - currentHp;
            float heal = 0.04F * maxHp + 0.035F * missingHp;
            heal = Math.min(heal, missingHp);
            return Math.max(0.0F, heal);
        }
    }
}
    