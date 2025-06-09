package amphitheremod.mixin.common;

import amphitheremod.config.ConfigHandler;
import com.github.alexthe666.iceandfire.entity.EntityAmphithere;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EntityAmphithere.class)
public abstract class CocoaBeanFeeding {
    @WrapOperation(
            method = "processInteract",
            at = @At(value = "INVOKE", target = "Lcom/github/alexthe666/iceandfire/entity/EntityAmphithere;heal(F)V")
    )
    private void amphimod_varyCocoaBeanHealAmount(EntityAmphithere amphithere, float v, Operation<Void> original) {
        float currentHp = amphithere.getHealth();
        float maxHp = amphithere.getMaxHealth();
        original.call(amphithere, amphimod$calcHeal(maxHp, currentHp));
    }

    @Unique private static float amphimod$calcHeal(float maxHp, float currentHp) {
        if (maxHp <= 0.0F) {
            return 0.0F;
        } else {
            currentHp = MathHelper.clamp(currentHp, 0.0F, maxHp);
            float missingHp = maxHp - currentHp;
            float heal = ConfigHandler.healPerMaxHealth * maxHp + ConfigHandler.healPerMissingHealth * missingHp;
            return MathHelper.clamp(heal, 0.0F, missingHp);
        }
    }
}
    